package www.i.testapplication.db.newshow;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface ShowDao
{
    @Query("SELECT * FROM new_shows")
    Single<List<NewShow>> getAllFavouriteShows();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NewShow favoriteShow);

    @Delete
    void remove(NewShow favoriteShow);
//
//    @Query("SELECT count(*) FROM favourite_shows where id LIKE :id")
//    int isFavouriteShow(long id);
}
