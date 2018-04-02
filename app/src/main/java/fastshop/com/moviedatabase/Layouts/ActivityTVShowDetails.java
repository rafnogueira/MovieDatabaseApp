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

import fastshop.com.moviedatabase.MainActivity;
import fastshop.com.moviedatabase.Models.TVShow;
import fastshop.com.moviedatabase.R;
import fastshop.com.moviedatabase.RetrofitContext.MovieApiInterface;
import fastshop.com.moviedatabase.RetrofitContext.MovieApiService;
import fastshop.com.moviedatabase.utilidades.BlurImage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActivityTVShowDetails extends Activity implements View.OnClickListener{

    ImageView imgViewCapa = null;
    TextView txtViewTituloPoster = null;
    TextView txtViewDesc = null;
    RatingBar ratingBar = null;
    FrameLayout poster = null;
    FloatingActionButton fabAddFavoritos = null;
    FloatingActionButton fabRemoveFavoritos = null;

    //Ponteiro para o filme que está sendo tratado para ser utilizado posteriormente para adicionar aos favoritos
    TVShow tvShowPointer = null;

    private int BLUR_PRECENTAGE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tvshow_details);

        imgViewCapa = (ImageView) findViewById(R.id.imgViewTvShowDetailsPoster);
        txtViewTituloPoster = (TextView) findViewById(R.id.txtViewTvShowDetailsTituloPoster);
        txtViewDesc = (TextView) findViewById(R.id.txtViewTvShowDetailsDesc);
        ratingBar = (RatingBar) findViewById(R.id.ratingTvShowDetails);
        poster = (FrameLayout) findViewById(R.id.frameLayoutTVShowDetailsBackgroundPoster);

        fabAddFavoritos = (FloatingActionButton) findViewById(R.id.fabTVShowDetailsAddFavoritos);
        fabAddFavoritos.setOnClickListener(this);

        fabRemoveFavoritos = (FloatingActionButton) findViewById(R.id.fabTVShowDetailsRemoverFavoritos);
        fabRemoveFavoritos.setOnClickListener(this);

        pesquisarShowDetalhes(getIntent().getExtras().getInt("MovieID"));

    }

    @Override
    public void onClick(View view) {

        //Tratar eventos do botão adicionar favs
        if(view.getId() == R.id.fabTVShowDetailsAddFavoritos)
        {
            if(this.tvShowPointer != null)
            {
                //Já contem a série adicionada na lista estática ?  senão adicionar ~~
                if(FragmentFavoritos.favoritosShows.contains(this.tvShowPointer))
                {
                    Toast.makeText(this, "Já está adicionado nos favoritos ^~^ ", Toast.LENGTH_SHORT).show();
                }else{
                    FragmentFavoritos.favoritosShows.add(this.tvShowPointer);
                    Toast.makeText(this, "Show adicionado aos favoritos", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this,  "Erro ao adicionar Favorito!", Toast.LENGTH_SHORT);
            }
        }

        //Tratar eventos do botão remover favs
        if(view.getId() == R.id.fabTVShowDetailsRemoverFavoritos)
        {
            if(this.tvShowPointer != null)
            {
                //Já contem a série adicionada na lista estática ?  senão adicionar ~~
                //Por enquanto apenas limpar tudo
                    FragmentFavoritos.favoritosShows.clear();

            }else{
                Toast.makeText(this,  "Erro ao adicionar Favorito!", Toast.LENGTH_SHORT);
            }
        }

    }

    public void setDadosInterface(TVShow tvShow)
    {
        //Verificação se encontrou o filme ou série
        if(tvShow == null)
        {
            Toast.makeText(this, "Erro nenhum dado recebido", Toast.LENGTH_SHORT).show();
        }else{

            txtViewTituloPoster.setText(tvShow.getName());
            txtViewDesc.setText(tvShow.getOverview());
            ratingBar.setMax(5);
            ratingBar.setRating(tvShow.getVoteAverage().floatValue()/2);

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
            Picasso.with(this).load("https://image.tmdb.org/t/p/w500/"+tvShow.getBackdropPath()).into(targetPoster);
            Picasso.with(this).load("https://image.tmdb.org/t/p/w500/"+tvShow.getPosterPath()).into(imgViewCapa);

            tvShowPointer = tvShow;

            fabAddFavoritos.setOnClickListener(this);

        }

    }

    public void pesquisarShowDetalhes(int id)
    {
        MovieApiInterface interfaceServico = MovieApiService.getClient().create(MovieApiInterface.class);

        Call<TVShow> chamada = interfaceServico.getShowDetails(id, MovieApiService.API_KEY, MovieApiService.API_LANGUAGE);
        chamada.enqueue(new Callback<TVShow>() {
            @Override
            public void onResponse(Call<TVShow> call, Response<TVShow> response) {
                TVShow tvShow = response.body();
                setDadosInterface(tvShow);
            }

            @Override
            public void onFailure(Call<TVShow> call, Throwable t) {

            }
        });
    }

}
