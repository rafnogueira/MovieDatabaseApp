package fastshop.com.moviedatabase.ModelsAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import fastshop.com.moviedatabase.Models.Filme;
import fastshop.com.moviedatabase.R;


/**
 * Created by Rafael on 28/03/2018.
 */
// OnClick listener para cada item da lista
public class FilmeAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Filme> listFilmes;

    public FilmeAdapter(Context context, List<Filme> listFilmes) {

        this.context = context;
        this.listFilmes = listFilmes;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View tmpView = LayoutInflater.from(context).inflate(R.layout.recycle_view_filme_holder, parent, false);

        FilmeViewHolder filmeHolder = new FilmeViewHolder(tmpView);

        return filmeHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        FilmeViewHolder filmeHolder = (FilmeViewHolder) holder;

        filmeHolder.txtViewTitulo.setText(listFilmes.get(position).getTitle());
        filmeHolder.txtViewDesc.setText(listFilmes.get(position).getOverview());
        filmeHolder.MovieID = listFilmes.get(position).getId();
        // Dizer para a classe que vai gerar a View e criar os detalhes que tipo será
        filmeHolder.tipoValor = FilmeViewHolder.Tipo.FILME.ordinal();

        filmeHolder.ratingBar.setMax(4);
        //Dividingo por 2 para caber em 5 estrelas, o retorno da api é de 0~10
        float rating = ((float) listFilmes.get(position).getVoteAverage().floatValue()/2);
        filmeHolder.ratingBar.setRating(rating);


        Picasso.with(context).
                load("https://image.tmdb.org/t/p/w500/"+listFilmes.get(position).getPosterPath())
                .into(filmeHolder.imageViewPoster);
        

    }


    @Override
    public int getItemCount() {
        return listFilmes.size();
    }

}
