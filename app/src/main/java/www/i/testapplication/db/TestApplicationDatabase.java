package www.i.testapplication.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import www.i.testapplication.db.newshow.NewShow;
import www.i.testapplication.db.newshow.ShowDao;

@Database(entities = {NewShow.class}, version = 1, exportSchema = false)
public abstract class TestApplicationDatabase extends  RoomDatabase
{
    public static final String DATABASE_NAME = "testapplication.db";

    public abstract ShowDao showDao();
}

