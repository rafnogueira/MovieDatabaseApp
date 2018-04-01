package fastshop.com.moviedatabase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;


import java.util.ArrayList;

import fastshop.com.moviedatabase.Layouts.FragmentCategorias;
import fastshop.com.moviedatabase.Layouts.FragmentHome;
import fastshop.com.moviedatabase.Models.Filme;
import fastshop.com.moviedatabase.Models.TVShow;

public class MainActivity extends FragmentActivity  implements BottomNavigationView.OnNavigationItemSelectedListener{

    public static final ArrayList<Filme> favoritosFilmes = null;
    public static final ArrayList<TVShow> favoritosShows = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        BottomNavigationView navigation =  findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        loadFragment(new FragmentHome());
    }

    public boolean loadFragment(Fragment frag)
    {
        if(frag != null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,frag).commit();
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;
        switch (item.getItemId())
        {
            case R.id.navigation_filmes_home:
                fragment = new FragmentHome();
                break;
            case R.id.navigation_filmes_categorias:
                fragment = new FragmentCategorias();
                break;
            case R.id.navigation_filmes_favoritos:
                fragment = new FragmentHome();
                break;
        }
        return loadFragment(fragment);
    }
}
