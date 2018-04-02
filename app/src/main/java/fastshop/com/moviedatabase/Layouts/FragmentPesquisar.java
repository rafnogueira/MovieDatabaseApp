package fastshop.com.moviedatabase.Layouts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

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

public class FragmentPesquisar extends Fragment implements View.OnClickListener
{

    EditText editTextPesquisar = null;
    Button btnPesquisar = null;
    RecyclerView recyclerViewResultadoPesquisa = null;

    ArrayList<Filme> resultado = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pesquisar , null);

        editTextPesquisar = view.findViewById(R.id.editTxtPesquisar);

        btnPesquisar = view.findViewById(R.id.btnPesquisar);
        btnPesquisar.setOnClickListener(this);
        recyclerViewResultadoPesquisa =  view.findViewById(R.id.recyclerViewPesquisarResultado);


//        txtInputEditPesquisar = view.findViewById(R.id.txtInputEditTxtNomePesquisar);



        return view;
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btnPesquisar)
        {
            PesquisarFilmes();
        }

    }

    public void PesquisarFilmes()
    {
        String userInput = editTextPesquisar.getText().toString();

        MovieApiInterface interfaceAPI = MovieApiService.getClient().create(MovieApiInterface.class);

        Call<FilmeResponse> chamada = interfaceAPI.getMoviesSearchName(MovieApiService.API_KEY, userInput, MovieApiService.API_LANGUAGE);

        chamada.enqueue(new Callback<FilmeResponse>() {
            @Override
            public void onResponse(Call<FilmeResponse> call, Response<FilmeResponse> response) {

                resultado = new ArrayList<Filme>();

                ArrayList<Filme> resultado  = (ArrayList<Filme>) response.body().getResults();

                LinearLayoutManager layout = new LinearLayoutManager(getContext());

                recyclerViewResultadoPesquisa.setAdapter(new FilmeAdapter(getContext(), resultado));
                recyclerViewResultadoPesquisa.setLayoutManager(layout);

            }

            @Override
            public void onFailure(Call<FilmeResponse> call, Throwable t) {

            }
        });


    }
}
