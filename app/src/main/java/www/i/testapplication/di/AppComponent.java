package www.i.testapplication.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import www.i.testapplication.TestApplicationApplication;
import www.i.testapplication.network.NetworkModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuildersModule.class,
        NetworkModule.class,
        TestApplicationDbModule.class})

public interface AppComponent {
    void inject(TestApplicationApplication tvMazeApplication);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
