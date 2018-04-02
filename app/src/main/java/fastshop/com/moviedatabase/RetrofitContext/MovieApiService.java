package fastshop.com.moviedatabase.RetrofitContext;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rafael on 26/03/2018.
 */

public class MovieApiService {

    public static String BASE_URL = "http://api.themoviedb.org/3/";
    public static String API_KEY = "e34cde97c981d0883944b9b4ee6e598e";
    public static String API_KEY_V4 = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlMzRjZGU5N2M5ODFkMDg4Mzk0NGI5YjRlZTZlNTk4ZSIsInN1YiI6IjVhYjk0ZDc5YzNhMzY4NzFkZDAwNjkxZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.H2LM0wTPDVlENrr0TKqO8UATTa77Q8-5He2iAk2A3Z8";

    public static String API_LANGUAGE = "pt-BR";

    private static Retrofit retrofitPont = null;

    public static Retrofit getClient()
    {
        if(retrofitPont == null) {
            retrofitPont = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
            return  retrofitPont;

    }

}