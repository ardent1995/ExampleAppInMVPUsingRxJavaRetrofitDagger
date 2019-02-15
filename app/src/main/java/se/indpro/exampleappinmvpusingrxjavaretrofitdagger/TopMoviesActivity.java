package se.indpro.exampleappinmvpusingrxjavaretrofitdagger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

import android.os.Bundle;
import android.view.ViewGroup;

public class TopMoviesActivity extends AppCompatActivity implements TopMoviesActivityMVP.View{

    private static final String TAG = "TopMoviesActivity";

    @BindView(R.id.rv_topmovies)
    RecyclerView recyclerView;

    @BindView(R.id.listActivity_rootView)
    ViewGroup rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topmovies);
    }

    @Override
    public void updateData(ViewModel viewModel) {

    }

    @Override
    public void showSnackbar(String s) {

    }
}
