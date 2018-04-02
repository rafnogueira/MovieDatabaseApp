package fastshop.com.moviedatabase.RetrofitContext;


import java.util.ArrayList;
import java.util.List;

import fastshop.com.moviedatabase.Models.Filme;
import fastshop.com.moviedatabase.Models.FilmeResponse;
import fastshop.com.moviedatabase.Models.Genero;
import fastshop.com.moviedatabase.Models.TVShow;
import fastshop.com.moviedatabase.Models.TVShowResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface  MovieApiInterface
{

    @GET("movie/top_rated")
    Call<FilmeResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<FilmeResponse> getLatestMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<FilmeResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/now_playing")
    Call<FilmeResponse> getNowPlaying(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<Filme>  getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("tv/popular")
    Call<TVShowResponse> getSeriesPopular(@Query("api_key") String apiKey);

    @GET("tv/top_rated")
    Call<TVShowResponse> getSeriesRating(@Query("api_key") String apiKey);

    @GET("tv/{tv_id}")
    Call<TVShow> getShowDetails(@Path("tv_id") int tv_id, @Query("api_key") String apiKey);

    @GET("genre/movie/list")
    Call<Genero> getGenres(@Query("api_key") String apiKey);

    @GET("genre/{genre_id}/movies")
    Call<FilmeResponse>  getMoviesByGenre(@Path("genre_id") int genre_id, @Query("api_key") String apiKey);



    @GET("search/movie")
    Call<FilmeResponse>  getMoviesSearchName(@Query("api_key") String apiKey, @Query("query") String query);
}
