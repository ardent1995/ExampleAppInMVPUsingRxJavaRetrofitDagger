package se.indpro.exampleappinmvpusingrxjavaretrofitdagger.topmovies;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import se.indpro.exampleappinmvpusingrxjavaretrofitdagger.http.ApiModuleForName;
import se.indpro.exampleappinmvpusingrxjavaretrofitdagger.http.MoreInfoApiService;
import se.indpro.exampleappinmvpusingrxjavaretrofitdagger.http.MovieApiService;

@Module(includes = {ApiModuleForName.class})
public class TopMoviesModule {
    @Provides
    public TopMoviesActivityMVP.Presenter provideTopMoviesActivityPresenter(TopMoviesActivityMVP.Model model){
        return new TopMoviesActivityPresenter(model);
    }

    @Provides
    public TopMoviesActivityMVP.Model provideTopMoviesActivityModel(Repository repository){
        return new TopMoviesModel(repository);
    }

    @Singleton
    @Provides
    public Repository provideRepo(MovieApiService movieApiService, MoreInfoApiService moreInfoApiService){
        return new TopMoviesRepository(movieApiService,moreInfoApiService);
    }
}
