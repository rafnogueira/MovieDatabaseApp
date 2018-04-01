package fastshop.com.moviedatabase.Layouts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import fastshop.com.moviedatabase.MainActivity;
import fastshop.com.moviedatabase.Models.Filme;
import fastshop.com.moviedatabase.Models.TVShow;
import fastshop.com.moviedatabase.ModelsAdapter.FilmeAdapter;
import fastshop.com.moviedatabase.ModelsAdapter.TVShowAdapter;
import fastshop.com.moviedatabase.R;


public class FragmentFavoritos extends android.support.v4.app.Fragment {

    RecyclerView recyclerViewFilmes = null;
    RecyclerView recyclerViewShows = null;



    public final static ArrayList<Filme> favoritosFilmes = new ArrayList<Filme>();
    public final static ArrayList<TVShow> favoritosShows = new ArrayList<TVShow>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favoritos, null);

        //Layout para o recycle

        LinearLayoutManager layoutFilmes = new LinearLayoutManager(view.getContext());

        recyclerViewFilmes = view.findViewById(R.id.recyclerViewFavoritosFilmes);
        recyclerViewFilmes.setLayoutManager(layoutFilmes);
        recyclerViewFilmes.setAdapter(new FilmeAdapter(view.getContext(),  this.favoritosFilmes));


        LinearLayoutManager layoutSeries= new LinearLayoutManager(view.getContext());
        recyclerViewShows = view.findViewById(R.id.recyclerViewFavoritosSeries);
        recyclerViewShows.setLayoutManager(layoutSeries);
        recyclerViewShows.setAdapter(new TVShowAdapter(view.getContext(),  this.favoritosShows));



        Toast.makeText(getContext(), "Series: "+ favoritosShows.size() + "\n Filmes:" + favoritosFilmes.size() , Toast.LENGTH_SHORT).show();

        return view;
    }

    public static ArrayList<Filme> getFavoritosFilmes() {
        return favoritosFilmes;
    }

    public static ArrayList<TVShow> getFavoritosShows() {
        return favoritosShows;
    }
}
