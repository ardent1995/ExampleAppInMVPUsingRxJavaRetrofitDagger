package se.indpro.exampleappinmvpusingrxjavaretrofitdagger.topmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import se.indpro.exampleappinmvpusingrxjavaretrofitdagger.R;
import se.indpro.exampleappinmvpusingrxjavaretrofitdagger.adapter.RecyclerViewAdapter;
import se.indpro.exampleappinmvpusingrxjavaretrofitdagger.root.App;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

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
    protected void onStart() {
        super.onStart();
        presenter.setView(this);
        presenter.loadData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.rxUnsubscribe();
        resultList.clear();
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateData(ViewModel viewModel) {
        resultList.add(viewModel);
        recyclerViewAdapter.notifyItemInserted(resultList.size() - 1);
        Log.d(TAG,"updateData: "+resultList.size());
    }

    @Override
    public void showSnackbar(String s) {
        Snackbar.make(rootView,s,Snackbar.LENGTH_LONG).show();
    }
}
