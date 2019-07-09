package www.i.testapplication.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import www.i.testapplication.news.ShowsViewModel;

@Module
abstract class ViewModelModule {
//    @Binds
//    @IntoMap
//    @ViewModelKey(HomeViewModel.class)
//    abstract ViewModel bindHomeViewModel(HomeViewModel homeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ShowsViewModel.class)
    abstract ViewModel bindShowsViewModel(ShowsViewModel showsViewModel);

//    @Binds
//    @IntoMap
//    @ViewModelKey(FavoriteShowsViewModel.class)
//    abstract ViewModel bindFavoriteShowsViewModel(FavoriteShowsViewModel favoriteShowsViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(TestApplicationViewModelFactory factory);
}