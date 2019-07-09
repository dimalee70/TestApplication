package www.i.testapplication.news;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class ShowsDataSource extends ItemKeyedDataSource<Integer, Show> {
    private static final String TAG = "ShowsDataSource";
    private final TestApplicationApi tvMazeApi;
    private final NewShowsRepository favoriteShowsRepository;
    private CompositeDisposable compositeDisposable;
    private int pageNumber = 1;
    private MutableLiveData<NetworkState> paginatedNetworkStateLiveData;
    private MutableLiveData<NetworkState> initialLoadStateLiveData;
    // For Retry
    private LoadParams<Integer> params;
    private LoadCallback<Show> callback;
    private Single<List<Show>> favoriteShows;

    @Inject
    public ShowsDataSource(TestApplicationApi tvMazeApi,
                           NewShowsRepository favoriteShowsRepository) {
        this.tvMazeApi = tvMazeApi;
        this.favoriteShowsRepository = favoriteShowsRepository;
        compositeDisposable = new CompositeDisposable();
        initialLoadStateLiveData = new MutableLiveData<>();
        paginatedNetworkStateLiveData = new MutableLiveData<>();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull LoadInitialCallback<Show> callback) {
        Log.d(TAG, "Fetching first page: " + pageNumber);
        initialLoadStateLiveData.postValue(NetworkState.builder()
                .status(NetworkState.Status.LOADING).build());
        Disposable showsDisposable = tvMazeApi.getShows(pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(shows -> onShowsFetched(shows.getList(), callback), this::onError);
        compositeDisposable.add(showsDisposable);
    }

    private void onError(Throwable throwable) {
        initialLoadStateLiveData.postValue(NetworkState.builder()
                .status(NetworkState.Status.ERROR)
                .message(throwable.getMessage()).build());
        Log.e(TAG, throwable.getMessage());
    }

    private void onShowsFetched(List<Show> shows, LoadInitialCallback<Show> callback) {
        initialLoadStateLiveData.postValue(NetworkState.builder()
                .status(NetworkState.Status.SUCCESS).build());
        pageNumber++;
        callback.onResult(shows);
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params,
                          @NonNull LoadCallback<Show> callback) {
        this.params = params;
        this.callback = callback;
        Log.d(TAG, "Fetching next page: " + pageNumber);
        paginatedNetworkStateLiveData.postValue(NetworkState.builder()
                .status(NetworkState.Status.LOADING).build());
        Disposable showsDisposable = tvMazeApi.getShows(params.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(shows -> onMoreShowsFetched(shows.getList(), callback), this::onPaginationError);
        compositeDisposable.add(showsDisposable);
    }

    private void onMoreShowsFetched(List<Show> shows, LoadCallback<Show> callback) {
        paginatedNetworkStateLiveData.postValue(NetworkState.builder()
                .status(NetworkState.Status.SUCCESS).build());
        pageNumber++;
        callback.onResult(shows);
    }

    private void onPaginationError(Throwable throwable) {
        paginatedNetworkStateLiveData.postValue(NetworkState.builder()
                .status(NetworkState.Status.ERROR)
                .message(throwable.getMessage()).build());
        Log.e(TAG, throwable.getMessage());
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params,
                           @NonNull LoadCallback<Show> callback) {
        // Do nothing, since data is loaded from our initial load itself
    }

    @NonNull
    @Override
    public Integer getKey(@NonNull Show item) {
        return pageNumber;
    }

    public void clear() {
        compositeDisposable.clear();
        pageNumber = 1;
    }

    public LiveData<NetworkState> getPaginatedNetworkStateLiveData() {
        return paginatedNetworkStateLiveData;
    }

    public LiveData<NetworkState> getInitialLoadStateLiveData() {
        return initialLoadStateLiveData;
    }

    public void retryPagination() {
        loadAfter(params, callback);
    }
}
