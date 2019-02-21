package se.indpro.exampleappinmvpusingrxjavaretrofitdagger.topmovies;

import android.content.SyncAdapterType;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import se.indpro.exampleappinmvpusingrxjavaretrofitdagger.http.MoreInfoApiService;
import se.indpro.exampleappinmvpusingrxjavaretrofitdagger.http.MovieApiService;
import se.indpro.exampleappinmvpusingrxjavaretrofitdagger.http.apimodel.Result;

public class TopMoviesRepository implements Repository {

    private MovieApiService movieApiService;
    private MoreInfoApiService moreInfoApiService;
    private List<String> countryList;
    private List<Result> resultList;
    private long timestamp;

    private static final long STALE_MS = 20*1000; //Data is stale after 20 seconds

    public TopMoviesRepository(MovieApiService movieApiService, MoreInfoApiService moreInfoApiService) {
        this.movieApiService = movieApiService;
        this.moreInfoApiService = moreInfoApiService;
        this.timestamp = System.currentTimeMillis();
        countryList = new ArrayList<>();
        resultList = new ArrayList<>();
    }

    public boolean isUpToDate(){
        return System.currentTimeMillis() - timestamp < STALE_MS;
    }

    @Override
    public Observable<Result> getResultsFromMemory() {
        return null;
    }

    @Override
    public Observable<Result> getResultsFromNetwork() {
        return null;
    }

    @Override
    public Observable<String> getCountriesFromMemory() {
        return null;
    }

    @Override
    public Observable<String> getCountriesFromNetwork() {
        return null;
    }

    @Override
    public Observable<String> getCountryData() {
        return null;
    }

    @Override
    public Observable<Result> getResultData() {
        return null;
    }

}
