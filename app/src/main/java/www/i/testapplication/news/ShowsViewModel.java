package www.i.testapplication.news;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import io.reactivex.Single;
import www.i.testapplication.db.newshow.NewShow;

public class ShowsViewModel extends ViewModel {
    private final ShowsDataSourceFactory showsDataSourceFactory;
    private LiveData<PagedList<Show>> shows;
    private final NewShowsRepository  newShowsRepository;
    private MutableLiveData<Boolean> isLoading;


    @Inject
    public ShowsViewModel(ShowsDataSourceFactory showsDataSourceFactory, NewShowsRepository newShowsRepository) {
        this.showsDataSourceFactory = showsDataSourceFactory;
        this.newShowsRepository = newShowsRepository;
    }

    public void onScreenCreated() {
//        Single<List<Show>> favoriteShows = newShowsRepository.getAllFavoriteShows()
//                .map(this::convertToShows);
//        DataSource dataSourceFactory = newShowsRepository.getAllFavoriteShows();
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(5)
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(5)
                .build();
        shows = new LivePagedListBuilder<>(showsDataSourceFactory, config).build();
    }

//    private List<Show> convertToShows(List<NewShow> favouriteShows) {
//        List<Show> favoriteShows = new ArrayList<>(favouriteShows.size());
//        for (NewShow favoriteShow : favouriteShows) {
//            Show show = Show.fromFavoriteShow(favoriteShow);
//            favoriteShows.add(show);
//        }
//        return favoriteShows;
//    }

    public LiveData<PagedList<Show>> getShows() {
        return shows;
    }

    public LiveData<NetworkState> initialLoadState() {
        return showsDataSourceFactory.getShowsDataSource().getInitialLoadStateLiveData();
    }

    public LiveData<NetworkState> paginatedLoadState() {
        return showsDataSourceFactory.getShowsDataSource().getPaginatedNetworkStateLiveData();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        showsDataSourceFactory.getShowsDataSource().clear();
    }

    public void retry() {
        showsDataSourceFactory.getShowsDataSource().retryPagination();
    }

    public void addToFavorite(Show show) {
        newShowsRepository.insertShowIntoFavorites(show);
    }

    public void removeFromFavorite(Show show) {
        newShowsRepository.removeShowFromFavorites(show);
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }

}