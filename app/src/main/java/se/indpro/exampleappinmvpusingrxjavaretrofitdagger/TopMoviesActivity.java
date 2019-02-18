package se.indpro.exampleappinmvpusingrxjavaretrofitdagger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import se.indpro.exampleappinmvpusingrxjavaretrofitdagger.adapter.RecyclerViewAdapter;
import se.indpro.exampleappinmvpusingrxjavaretrofitdagger.root.App;

import android.os.Bundle;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TopMoviesActivity extends AppCompatActivity implements TopMoviesActivityMVP.View{

    private static final String TAG = "TopMoviesActivity";

    @BindView(R.id.rv_topmovies)
    RecyclerView recyclerView;

    @BindView(R.id.listActivity_rootView)
    ViewGroup rootView;

    @Inject TopMoviesActivityMVP.Presenter presenter;

    private RecyclerViewAdapter recyclerViewAdapter;
    private List<ViewModel> resultList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topmovies);

        ((App)getApplication()).getComponent().inject(this);

        ButterKnife.bind(this);

        recyclerViewAdapter = new RecyclerViewAdapter(resultList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.loadData();
    }

    @Override
    public void updateData(ViewModel viewModel) {

    }

    @Override
    public void showSnackbar(String s) {

    }
}
