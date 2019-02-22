package se.indpro.exampleappinmvpusingrxjavaretrofitdagger.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import se.indpro.exampleappinmvpusingrxjavaretrofitdagger.R;
import se.indpro.exampleappinmvpusingrxjavaretrofitdagger.topmovies.ViewModel;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<ViewModel> list;

    public RecyclerViewAdapter(List<ViewModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvMovieName.setText(list.get(position).getName());
        holder.tvCountryName.setText(list.get(position).getCountry());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvMovieName;
        public TextView tvCountryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMovieName = itemView.findViewById(R.id.tv_movie_name);
            tvCountryName = itemView.findViewById(R.id.tv_country_name);
        }
    }
}
