package se.indpro.exampleappinmvpusingrxjavaretrofitdagger.http;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import se.indpro.exampleappinmvpusingrxjavaretrofitdagger.http.apimodel.TopRated;

public interface MovieApiService {

    @GET("top_rated")
    Observable<TopRated> getTopRatedMovies(@Query("page") Integer page);
}
