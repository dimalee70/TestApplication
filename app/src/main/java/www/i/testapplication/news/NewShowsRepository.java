package www.i.testapplication.news;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import www.i.testapplication.db.newshow.NewShow;
import www.i.testapplication.db.newshow.ShowDao;

@Singleton
public class NewShowsRepository {
    private final ShowDao showDao;

    @Inject
    public NewShowsRepository(ShowDao showDao) {
        this.showDao = showDao;
    }

    public Single<List<NewShow>> getAllFavoriteShows() {
        return showDao.getAllFavouriteShows();
    }

//    public boolean isFavoriteShow(long id) {
//        return showDao.isFavouriteShow(id) > 0;
//    }

    public void insertShowIntoFavorites(Show show) {
        NewShow favoriteShow = new NewShow();
        favoriteShow.setTitle(show.title());
        favoriteShow.setUrlToImage(show.urlToImage());
        favoriteShow.setUrl(show.url());
        favoriteShow.setContent(show.content());
        favoriteShow.setDescription(show.description());
        favoriteShow.setPublishedAt(show.publishedAt());
        showDao.insert(favoriteShow);
    }

    public void removeShowFromFavorites(Show show) {
        NewShow favoriteShow = new NewShow();
        favoriteShow.setTitle(show.title());
        favoriteShow.setUrlToImage(show.image().get("original"));
        favoriteShow.setUrl(show.url());
        favoriteShow.setContent(show.content());
        favoriteShow.setDescription(show.description());
        favoriteShow.setPublishedAt(show.publishedAt());
        showDao.remove(favoriteShow);
    }

    public void insertIntoFavorites(NewShow favoriteShow) {
        showDao.insert(favoriteShow);
    }

    public void removeFromFavorites(NewShow favoriteShow) {
        showDao.remove(favoriteShow);
    }
}
