package fastshop.com.moviedatabase.Layouts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fastshop.com.moviedatabase.Models.Filme;
import fastshop.com.moviedatabase.Models.FilmeResponse;
import fastshop.com.moviedatabase.Models.TVShow;
import fastshop.com.moviedatabase.Models.TVShowResponse;
import fastshop.com.moviedatabase.ModelsAdapter.FilmeAdapter;
import fastshop.com.moviedatabase.ModelsAdapter.TVShowAdapter;
import fastshop.com.moviedatabase.R;
import fastshop.com.moviedatabase.RetrofitContext.MovieApiInterface;
import fastshop.com.moviedatabase.RetrofitContext.MovieApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentHome extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {

        // Instanciação dos ponteiros e manipulação dos dados com casting
        View view = inflater.inflate(R.layout.fragment_home, null);


        //Criação do objeto para chamada to tipo FilmeResponse
        RecyclerView recyclerViewHomeLancamentos  = (RecyclerView) view.findViewById(R.id.recyclerViewHomeLancamentos);
        APIPopulateFilmesLancamentos(recyclerViewHomeLancamentos);

        RecyclerView recyclerViewHomeNotas = (RecyclerView) view.findViewById(R.id.recyclerViewHomeNotas);
        APIPopulateFilmesNotas(recyclerViewHomeNotas);

        RecyclerView recyclerViewHomePopulares = (RecyclerView) view.findViewById(R.id.recyclerViewHomePopulares);
        APIPopulateFilmesPopulares(recyclerViewHomePopulares);

        RecyclerView recyclerViewHomeCinema  = (RecyclerView) view.findViewById(R.id.recyclerViewHomeCinema);
        APIPopulateFilmesCinema(recyclerViewHomeCinema);


        //Seção Séries
        RecyclerView recyclerViewHomeSeriesPopulares = (RecyclerView) view.findViewById(R.id.recyclerViewHomeSeriesPopulares);
        APIPopulateSeriesPopulares(recyclerViewHomeSeriesPopulares);

        RecyclerView recyclerViewHomeSeriesNotas  = (RecyclerView) view.findViewById(R.id.recyclerViewHomeSeriesRating);
        APIPopulateSeriesRating(recyclerViewHomeSeriesNotas);

        return view;
    }

    public void APIPopulateFilmesLancamentos(final RecyclerView rvPointer) {


        MovieApiInterface interfaceServico = MovieApiService.getClient().create(MovieApiInterface.class);
        Call<FilmeResponse> chamada  = interfaceServico.getLatestMovies(MovieApiService.API_KEY, MovieApiService.API_LANGUAGE);
        chamada.enqueue(new Callback<FilmeResponse>() {
            @Override
            public void onResponse(Call<FilmeResponse> call, Response<FilmeResponse> response) {

                List<Filme> filmes =  response.body().getResults();

                LinearLayoutManager layout = new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL , false);
                rvPointer.setLayoutManager(layout);
                rvPointer.setAdapter(new FilmeAdapter(getContext(), filmes));
            }

            @Override
            public void onFailure(Call<FilmeResponse> call, Throwable t) {

            }
        });
        
    }

    public void APIPopulateFilmesNotas(final RecyclerView rvPointer) {


        MovieApiInterface interfaceServico = MovieApiService.getClient().create(MovieApiInterface.class);
        Call<FilmeResponse> chamada  = interfaceServico.getTopRatedMovies(MovieApiService.API_KEY , MovieApiService.API_LANGUAGE);
        chamada.enqueue(new Callback<FilmeResponse>() {
            @Override
            public void onResponse(Call<FilmeResponse> call, Response<FilmeResponse> response) {

                List<Filme> filmes =  response.body().getResults();

                LinearLayoutManager layout = new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL , false);

                rvPointer.setLayoutManager(layout);

                rvPointer.setAdapter(new FilmeAdapter(getContext(), filmes));
            }

            @Override
            public void onFailure(Call<FilmeResponse> call, Throwable t) {

            }
        });

    }

    public void APIPopulateFilmesPopulares(final RecyclerView rvPointer) {


        MovieApiInterface interfaceServico = MovieApiService.getClient().create(MovieApiInterface.class);
        Call<FilmeResponse> chamada  = interfaceServico.getPopularMovies(MovieApiService.API_KEY, MovieApiService.API_LANGUAGE);
        chamada.enqueue(new Callback<FilmeResponse>() {
            @Override
            public void onResponse(Call<FilmeResponse> call, Response<FilmeResponse> response) {

                List<Filme> filmes =  response.body().getResults();

                LinearLayoutManager layout = new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL , false);

                rvPointer.setLayoutManager(layout);
                rvPointer.setAdapter(new FilmeAdapter(getContext(), filmes));
            }

            @Override
            public void onFailure(Call<FilmeResponse> call, Throwable t) {

            }
        });

    }

    public void APIPopulateFilmesCinema(final RecyclerView rvPointer) {


        MovieApiInterface interfaceServico = MovieApiService.getClient().create(MovieApiInterface.class);
        Call<FilmeResponse> chamada  = interfaceServico.getNowPlaying(MovieApiService.API_KEY, MovieApiService.API_LANGUAGE);
        chamada.enqueue(new Callback<FilmeResponse>() {
            @Override
            public void onResponse(Call<FilmeResponse> call, Response<FilmeResponse> response) {

                List<Filme> filmes =  response.body().getResults();

                LinearLayoutManager layout = new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL , false);

                rvPointer.setLayoutManager(layout);

                rvPointer.setAdapter(new FilmeAdapter(getContext(), filmes));
            }

            @Override
            public void onFailure(Call<FilmeResponse> call, Throwable t) {

            }
        });

    }

    public void APIPopulateSeriesPopulares(final RecyclerView rvPointer) {


        MovieApiInterface interfaceServico = MovieApiService.getClient().create(MovieApiInterface.class);

        Call<TVShowResponse> chamada = interfaceServico.getSeriesPopular(MovieApiService.API_KEY, MovieApiService.API_LANGUAGE);
        chamada.enqueue(new Callback<TVShowResponse>() {
            @Override
            public void onResponse(Call<TVShowResponse> call, Response<TVShowResponse> response) {

                ArrayList<TVShow> tvShows =  (ArrayList<TVShow>) response.body().getResults();

                LinearLayoutManager layout = new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL , false);

                rvPointer.setLayoutManager(layout);

                rvPointer.setAdapter(new TVShowAdapter(getContext(),tvShows));

            }

            @Override
            public void onFailure(Call<TVShowResponse> call, Throwable t) {

            }
        });

    }

    public void APIPopulateSeriesRating(final RecyclerView rvPointer) {


        MovieApiInterface interfaceServico = MovieApiService.getClient().create(MovieApiInterface.class);
        Call<TVShowResponse> chamada = interfaceServico.getSeriesRating(MovieApiService.API_KEY, MovieApiService.API_LANGUAGE);
        chamada.enqueue(new Callback<TVShowResponse>() {
            @Override
            public void onResponse(Call<TVShowResponse> call, Response<TVShowResponse> response) {

                ArrayList<TVShow> tvShows =  (ArrayList<TVShow>) response.body().getResults();


                LinearLayoutManager layout = new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL , false);
//                LinearLayoutManager layout = new LinearLayoutManager(getContext());
                rvPointer.setLayoutManager(layout);

                rvPointer.setAdapter(new TVShowAdapter(getContext(),tvShows));

            }

            @Override
            public void onFailure(Call<TVShowResponse> call, Throwable t) {

            }
        });
    }

}
