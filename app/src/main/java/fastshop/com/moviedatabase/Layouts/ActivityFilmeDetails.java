package fastshop.com.moviedatabase.Layouts;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import fastshop.com.moviedatabase.Models.Filme;
import fastshop.com.moviedatabase.R;
import fastshop.com.moviedatabase.RetrofitContext.MovieApiInterface;
import fastshop.com.moviedatabase.RetrofitContext.MovieApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActivityFilmeDetails extends Activity implements View.OnClickListener{


    ImageView imgViewPoster = null;
    TextView txtViewTitulo = null;
    TextView txtViewFilmeDesc = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmes_details);


        imgViewPoster = (ImageView) findViewById(R.id.imageViewFilmeDetalhePoster);
        txtViewTitulo = (TextView) findViewById(R.id.textViewFilmeDetalheTitulo);
        txtViewFilmeDesc = (TextView) findViewById(R.id.textViewFilmeDetalheDesc);

        pesquisarFilmeDetalhes(getIntent().getExtras().getInt("MovieID"));


    }

    @Override
    public void onClick(View v) {


    }

    public void setDadosInterface(Filme filme)
    {

        if(filme == null)
        {
            Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show();
        }else{
            Picasso.with(this).load("https://image.tmdb.org/t/p/w500/"+filme.getPosterPath()).into(imgViewPoster);
            txtViewTitulo.setText(filme.getTitle());
            txtViewFilmeDesc.setText(filme.getOverview());
        }

    }

    public void pesquisarFilmeDetalhes(int id)
    {
        MovieApiInterface interfaceServico = MovieApiService.getClient().create(MovieApiInterface.class);
        Call<Filme> chamadaAPI = interfaceServico.getMovieDetails(id,  MovieApiService.API_KEY);
        chamadaAPI.enqueue(new Callback<Filme>() {
            @Override
            public void onResponse(Call<Filme> call, Response<Filme> response) {
                Filme filme = response.body();
                setDadosInterface(filme);
            }

            @Override
            public void onFailure(Call<Filme> call, Throwable t) {

            }
        });

    }
}
