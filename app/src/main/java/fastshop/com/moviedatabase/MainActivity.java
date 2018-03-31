package fastshop.com.moviedatabase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;

import fastshop.com.moviedatabase.Layouts.FragmentFilmesHome;

public class MainActivity extends FragmentActivity  implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation =  findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        loadFragment(new FragmentFilmesHome());
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
                fragment = new FragmentFilmesHome();
                break;
            case R.id.navigation_filmes_categorias:
                fragment = new FragmentFilmesHome();
                break;
            case R.id.navigation_filmes_favoritos:
                fragment = new FragmentFilmesHome();
                break;
        }
        return loadFragment(fragment);
    }
}
