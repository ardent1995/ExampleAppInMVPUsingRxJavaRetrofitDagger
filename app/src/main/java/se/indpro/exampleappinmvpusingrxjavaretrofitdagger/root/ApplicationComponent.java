package se.indpro.exampleappinmvpusingrxjavaretrofitdagger.root;

import javax.inject.Singleton;

import dagger.Component;
import se.indpro.exampleappinmvpusingrxjavaretrofitdagger.http.ApiModuleForInfo;
import se.indpro.exampleappinmvpusingrxjavaretrofitdagger.http.ApiModuleForName;
import se.indpro.exampleappinmvpusingrxjavaretrofitdagger.topmovies.TopMoviesActivity;
import se.indpro.exampleappinmvpusingrxjavaretrofitdagger.topmovies.TopMoviesModule;

@Singleton
@Component(modules = {ApplicationModule.class, ApiModuleForName.class, ApiModuleForInfo.class, TopMoviesModule.class})
public interface ApplicationComponent {

    void inject(TopMoviesActivity topMoviesActivity);

}
