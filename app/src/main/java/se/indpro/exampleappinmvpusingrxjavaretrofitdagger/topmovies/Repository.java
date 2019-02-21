package se.indpro.exampleappinmvpusingrxjavaretrofitdagger.topmovies;

import io.reactivex.Observable;
import se.indpro.exampleappinmvpusingrxjavaretrofitdagger.http.apimodel.Result;

public interface Repository {
    Observable<Result> getResultsFromMemory();

    Observable<Result> getResultsFromNetwork();

    Observable<String> getCountriesFromMemory();

    Observable<String> getCountriesFromNetwork();

    Observable<String> getCountryData();

    Observable<Result> getResultData();
}
