package se.indpro.exampleappinmvpusingrxjavaretrofitdagger.http;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import se.indpro.exampleappinmvpusingrxjavaretrofitdagger.http.apimodel.OmdbApi;

public interface MoreInfoApiService {

    @GET("/")
    Observable<OmdbApi> getCountry(@Query("t") String title);

}
