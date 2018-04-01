package fastshop.com.moviedatabase.Layouts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fastshop.com.moviedatabase.Models.Filme;
import fastshop.com.moviedatabase.Models.FilmeResponse;
import fastshop.com.moviedatabase.Models.Genero;
import fastshop.com.moviedatabase.ModelsAdapter.FilmeAdapter;
import fastshop.com.moviedatabase.R;
import fastshop.com.moviedatabase.RetrofitContext.MovieApiInterface;
import fastshop.com.moviedatabase.RetrofitContext.MovieApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// sempre a app v4 para retro compatibilidade,
public class FragmentCategorias extends Fragment implements OnItemSelectedListener {

    Spinner spinerCategoriasFilmes = null;
    RecyclerView recyclerViewResultCategoria =  null;
    List<Genero> generosList = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_categorias, null);

        spinerCategoriasFilmes = (Spinner) view.findViewById(R.id.spinnerGenreListFilmes);
        spinerCategoriasFilmes.setOnItemSelectedListener(this);

        recyclerViewResultCategoria = (RecyclerView) view.findViewById(R.id.recyclerViewCategoriasResultado);

        final MovieApiInterface interfaceAPI = MovieApiService.getClient().create(MovieApiInterface.class);

        Call<Genero> chamada = interfaceAPI.getGenres(MovieApiService.API_KEY);
        chamada.enqueue(new Callback<Genero>() {
            @Override
            public void onResponse(Call<Genero> call, Response<Genero> response) {

                // Se carregar do serviçco web com sucesso os dados,  redimensionar um array para
                // o tamanho do resultado, depois de forma iterativa colocar ele em um array ordenado por index
                generosList = response.body().getListGeneros();

                ArrayList<String> Nomes =  new ArrayList<String>();
                //Criando as string dos itens do spinner 1x1 para tentar manter o index examente igual
                for(Genero ge : generosList)
                {
                    Nomes.add(ge.getName());
                }
                spinerCategoriasFilmes.setAdapter(new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, Nomes));

            }

            @Override
            public void onFailure(Call<Genero> call, Throwable t) {

                Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();

            }
        });



        return view;

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {

        int generoID= generosList.get(adapterView.getSelectedItemPosition()).getId();

        //Toast.makeText(getContext(), "Escolhido " + generoSelected.getName() + "ID:" + generoSelected.getId(), Toast.LENGTH_LONG).show();

        MovieApiInterface interfaceAPI =  MovieApiService.getClient().create(MovieApiInterface.class);
        Call<FilmeResponse> chamada = interfaceAPI.getMoviesByGenre(generoID,MovieApiService.API_KEY);
        chamada.enqueue(new Callback<FilmeResponse>() {
            @Override
            public void onResponse(Call<FilmeResponse> call, Response<FilmeResponse> response) {

                ArrayList<Filme> resultado  = (ArrayList<Filme>) response.body().getResults();
                recyclerViewResultCategoria.setAdapter(new FilmeAdapter(getContext(), resultado));
                LinearLayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

                recyclerViewResultCategoria.setLayoutManager(layout);

            }

            @Override
            public void onFailure(Call<FilmeResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}