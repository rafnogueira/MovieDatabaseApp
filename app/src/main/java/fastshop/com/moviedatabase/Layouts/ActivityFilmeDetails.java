package fastshop.com.moviedatabase.Layouts;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import fastshop.com.moviedatabase.MainActivity;
import fastshop.com.moviedatabase.Models.Filme;
import fastshop.com.moviedatabase.Models.TVShow;
import fastshop.com.moviedatabase.R;
import fastshop.com.moviedatabase.RetrofitContext.MovieApiInterface;
import fastshop.com.moviedatabase.RetrofitContext.MovieApiService;
import fastshop.com.moviedatabase.utilidades.BlurImage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActivityFilmeDetails extends Activity implements View.OnClickListener{
    ImageView imgViewCapa = null;
    TextView txtViewTituloPoster = null;
    TextView txtViewDesc = null;
    RatingBar ratingBar = null;
    FrameLayout poster = null;
    FloatingActionButton fabAddFavoritos = null;
    FloatingActionButton fabRemoveFavoritos = null;

    //Ponteiro para o filme que está sendo tratado para ser utilizado posteriormente para adicionar aos favoritos
    Filme filmePointer = null;

    private int BLUR_PRECENTAGE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_filmes_details);


        imgViewCapa = (ImageView) findViewById(R.id.imgViewFilmeDetailsPoster);
        txtViewTituloPoster = (TextView) findViewById(R.id.txtViewFilmeDetailsTituloPoster);
        txtViewDesc = (TextView) findViewById(R.id.txtViewFilmeDetailsDesc);
        ratingBar = (RatingBar) findViewById(R.id.ratingFilmeDetails);
        poster = (FrameLayout) findViewById(R.id.frameLayoutFilmeDetailsBackgroundPoster);

        fabAddFavoritos = (FloatingActionButton) findViewById(R.id.fabFilmeDetailsAddFavoritos);
        fabAddFavoritos.setOnClickListener(this);

        fabRemoveFavoritos = (FloatingActionButton) findViewById(R.id.fabFilmeDetailsRemoverFavoritos);
        fabRemoveFavoritos.setOnClickListener(this);

        pesquisarFilmeDetalhes(getIntent().getExtras().getInt("MovieID"));

    }

    @Override
    public void onClick(View view) {
        //Tratar eventos do botão adicionar favs
        if(view.getId() == R.id.fabFilmeDetailsAddFavoritos)
        {
            if(this.filmePointer != null)
            {
                //Já contem a série adicionada na lista estática ?  senão adicionar ~~
                if(FragmentFavoritos.favoritosFilmes.contains(this.filmePointer))
                {
                    Toast.makeText(this, "Já está adicionado nos favoritos ^~^ ", Toast.LENGTH_SHORT).show();
                }else{
                    FragmentFavoritos.favoritosFilmes.add(this.filmePointer);
                    Toast.makeText(this, "Filme adicionado aos favoritos", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this,  "Erro ao adicionar Favorito!", Toast.LENGTH_SHORT);
            }
        }

        //Tratar eventos do botão remover favs
        if(view.getId() == R.id.fabFilmeDetailsRemoverFavoritos)
        {
            if(this.filmePointer != null)
            {
                //Já contem a série adicionada na lista estática ?  senão adicionar ~~
                if(FragmentFavoritos.favoritosFilmes.contains(this.filmePointer))
                {
                    FragmentFavoritos.favoritosFilmes.remove(this.filmePointer);
                    Toast.makeText(this, "Filme removido dos favoritos", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Já está não está nos favoritos", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this,  "Erro ao adicionar Favorito!", Toast.LENGTH_SHORT);
            }
        }

    }

    public void setDadosInterface(Filme filme)
    {
        //Verificação se encontrou o filme ou série
        if(filme == null)
        {
            Toast.makeText(this, "Erro nenhum dado recebido", Toast.LENGTH_SHORT).show();
        }else{

            txtViewTituloPoster.setText(filme.getTitle());
            txtViewDesc.setText(filme.getOverview());
            ratingBar.setMax(5);
            ratingBar.setRating(filme.getVoteAverage().floatValue()/2);

            //código que cria o efeito na imagem de poster do fundo, atrás da imagem da capa da série/tvShow
            Target targetPoster = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    poster.setBackground(BlurImage.fastblur(bitmap, 1f,
                            BLUR_PRECENTAGE));
                }
                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                    imgViewCapa.setImageResource(R.mipmap.ic_launcher);
                }
                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            };

            poster.setTag(targetPoster);
            Picasso.with(this).load("https://image.tmdb.org/t/p/w500/"+filme.getBackdropPath()).into(targetPoster);
            Picasso.with(this).load("https://image.tmdb.org/t/p/w500/"+filme.getPosterPath()).into(imgViewCapa);

            filmePointer = filme;

            fabAddFavoritos.setOnClickListener(this);

        }

    }

    public void pesquisarFilmeDetalhes(int id)
    {
        MovieApiInterface interfaceServico = MovieApiService.getClient().create(MovieApiInterface.class);

        Call<Filme> chamada = interfaceServico.getMovieDetails(id, MovieApiService.API_KEY);
        chamada.enqueue(new Callback<Filme>() {
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
