package www.i.testapplication.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import www.i.testapplication.db.TestApplicationDatabase;
import www.i.testapplication.db.newshow.ShowDao;

@Module
public class TestApplicationDbModule
{
    @Singleton
    @Provides
    public TestApplicationDatabase provideTestApplicationDatabase(Context context) {
        return Room.databaseBuilder(context,
                TestApplicationDatabase.class, TestApplicationDatabase.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Singleton
    @Provides
    public ShowDao provideShowDao(TestApplicationDatabase tvMazeDatabase) {
        return tvMazeDatabase.showDao();
    }
}
