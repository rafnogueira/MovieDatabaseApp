package fastshop.com.moviedatabase.Layouts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fastshop.com.moviedatabase.R;

// sempre a app v4 para retro compatibilidade,
public class FragmentCategorias extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_categorias, null);

        return view;

    }
}