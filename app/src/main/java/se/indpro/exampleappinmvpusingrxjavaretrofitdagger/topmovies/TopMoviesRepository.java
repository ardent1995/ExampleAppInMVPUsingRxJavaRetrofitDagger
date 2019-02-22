package se.indpro.exampleappinmvpusingrxjavaretrofitdagger.topmovies;

import android.content.SyncAdapterType;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import se.indpro.exampleappinmvpusingrxjavaretrofitdagger.http.MoreInfoApiService;
import se.indpro.exampleappinmvpusingrxjavaretrofitdagger.http.MovieApiService;
import se.indpro.exampleappinmvpusingrxjavaretrofitdagger.http.apimodel.OmdbApi;
import se.indpro.exampleappinmvpusingrxjavaretrofitdagger.http.apimodel.Result;
import se.indpro.exampleappinmvpusingrxjavaretrofitdagger.http.apimodel.TopRated;

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
        if(isUpToDate()){
            return  Observable.fromIterable(resultList);
        }else {
            timestamp = System.currentTimeMillis();
            resultList.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<Result> getResultsFromNetwork() {
        Observable<TopRated> topRatedObservable = movieApiService.getTopRatedMovies(1)
                .concatWith(movieApiService.getTopRatedMovies(2)
                .concatWith(movieApiService.getTopRatedMovies(3)));
        return topRatedObservable.concatMap(new Function<TopRated, ObservableSource<Result>>() {
            @Override
            public ObservableSource<Result> apply(TopRated topRated) throws Exception {
                return Observable.fromIterable(topRated.getResults());
            }
        }).doOnNext(new Consumer<Result>() {
            @Override
            public void accept(Result result) throws Exception {
                resultList.add(result);
            }
        });
    }

    @Override
    public Observable<String> getCountriesFromMemory() {
        if(isUpToDate()){
            return Observable.fromIterable(countryList);
        }else {
            timestamp = System.currentTimeMillis();
            countryList.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<String> getCountriesFromNetwork() {
        return getResultsFromNetwork().concatMap(new Function<Result, ObservableSource<OmdbApi>>() {
            @Override
            public ObservableSource<OmdbApi> apply(Result result) throws Exception {
                return moreInfoApiService.getCountry(result.getTitle());
            }
        }).concatMap(new Function<OmdbApi, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(OmdbApi omdbApi) throws Exception {
                return Observable.just(omdbApi.getCountry());
            }
        }).doOnNext(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                countryList.add(s);
            }
        });
    }

    @Override
    public Observable<String> getCountryData() {
        return getCountriesFromMemory().switchIfEmpty(getCountriesFromNetwork());
    }

    @Override
    public Observable<Result> getResultData() {
        return getResultsFromMemory().switchIfEmpty(getResultsFromNetwork());
    }

}
