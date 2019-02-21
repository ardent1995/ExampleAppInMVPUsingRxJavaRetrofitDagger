package se.indpro.exampleappinmvpusingrxjavaretrofitdagger.root;

import dagger.Component;
import se.indpro.exampleappinmvpusingrxjavaretrofitdagger.topmovies.TopMoviesActivity;
import se.indpro.exampleappinmvpusingrxjavaretrofitdagger.topmovies.TopMoviesModule;

@Component(modules = {ApplicationModule.class, TopMoviesModule.class})
public interface ApplicationComponent {

    void inject(TopMoviesActivity topMoviesActivity);

}
