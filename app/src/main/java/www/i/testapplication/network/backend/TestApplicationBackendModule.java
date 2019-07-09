package www.i.testapplication.network.backend;

import com.google.gson.Gson;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import www.i.testapplication.network.NetworkModule;
import www.i.testapplication.news.TestApplicationApi;

@Module
public class TestApplicationBackendModule
{
    @Provides
    @Singleton
    public TestApplicationApi provideTvMazeApi(OkHttpClient okHttpClient,
                                               @Named(NetworkModule.TVMAZE_BASE_URL) String baseUrl,
                                               RxJava2CallAdapterFactory rxJava2CallAdapterFactory,
                                               Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .client(okHttpClient)
                .build().create(TestApplicationApi.class);
    }
}
