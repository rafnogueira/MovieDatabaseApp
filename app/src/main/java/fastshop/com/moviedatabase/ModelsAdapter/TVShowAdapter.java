package fastshop.com.moviedatabase.ModelsAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import fastshop.com.moviedatabase.Models.TVShow;
import fastshop.com.moviedatabase.R;


public class TVShowAdapter extends RecyclerView.Adapter
{

    public TVShowAdapter(Context context,ArrayList<TVShow> listShows) {
        this.listShows = listShows;
        this.context = context;
    }

    ArrayList<TVShow> listShows;
    Context context;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycle_view_filme_holder, parent, false);

        // Filme Holder e tvShowHolder são iguais
        FilmeViewHolder tvShowHolder = new FilmeViewHolder(view);

        return tvShowHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        FilmeViewHolder tvShowHolder = (FilmeViewHolder) holder;

        tvShowHolder.txtViewTitulo.setText(listShows.get(position).getName());
        tvShowHolder.txtViewDesc.setText(listShows.get(position).getOverview());
        tvShowHolder.MovieID = listShows.get(position).getId();
        tvShowHolder.tipoValor =  FilmeViewHolder.Tipo.TVShow.ordinal();
        tvShowHolder.ratingBar.setNumStars(5);
        tvShowHolder.ratingBar.setRating(listShows.get(position).getVoteAverage().floatValue()/2);

        Picasso.with(context).
                load("https://image.tmdb.org/t/p/w500/"+listShows.get(position).getPosterPath())
                .into(tvShowHolder.imageViewPoster);


    }

    @Override
    public int getItemCount() {
        return  listShows.size();
    }
}
