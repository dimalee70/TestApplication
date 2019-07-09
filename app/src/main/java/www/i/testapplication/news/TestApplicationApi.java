package www.i.testapplication.news;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TestApplicationApi
{

    @GET("/v2/everything?q=android&from=2019-04-00&sortBy=publishedAt&apiKey=26eddb253e7840f988aec61f2ece2907")
    Single<Model> getShows(@Query("page") int pageNumber);
}
