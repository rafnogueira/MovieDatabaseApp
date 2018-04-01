package fastshop.com.moviedatabase.ModelsAdapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import fastshop.com.moviedatabase.Layouts.ActivityFilmeDetails;
import fastshop.com.moviedatabase.Layouts.ActivityTVShowDetails;
import fastshop.com.moviedatabase.R;

/**
 * Created by Rafael on 28/03/2018.
 */

public class FilmeViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

    public enum Tipo{
        FILME,
        TVShow
    };

    int tipoValor;
    int MovieID;
    TextView txtViewTitulo;
    TextView txtViewDesc;
    ImageView imageViewPoster;
    RatingBar ratingBar;

    public FilmeViewHolder(View itemView)
    {
        super(itemView);
        this.itemView.setOnClickListener(this);

        txtViewTitulo = (TextView) itemView.findViewById(R.id.txtViewTituloFilmeHolder);
        txtViewDesc = (TextView) itemView.findViewById(R.id.txtViewDescFilmeHolder);
        imageViewPoster = (ImageView) itemView.findViewById(R.id.imageViewPosterFilmeHolder);
        ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBarFilmeHolder);

    }

    @Override
    public void onClick(View view) {

        //Manipulação de evento incluindo animações
        Animation animationTest = AnimationUtils.loadAnimation(itemView.getContext(), android.R.anim.slide_out_right);
        itemView.startAnimation(animationTest);

        Toast.makeText(itemView.getContext(), "Abrindo detalhes do filme", Toast.LENGTH_SHORT).show();

        Intent intent;
        if(this.tipoValor == Tipo.FILME.ordinal())
        {
            intent = new Intent(view.getContext(), ActivityFilmeDetails.class);
        }else{
            intent = new Intent(view.getContext(), ActivityTVShowDetails.class);
        }

        intent.putExtra("MovieID", MovieID);
        view.getContext().startActivity(intent);

    }
}