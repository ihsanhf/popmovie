package id.ihsan.popmovie.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import id.ihsan.popmovie.R;
import id.ihsan.popmovie.models.Movie;
import id.ihsan.popmovie.utils.Constans;
import id.ihsan.popmovie.widgets.MovieImageView;

/**
 * @author Ihsan Helmi Faisal <ihsan.helmi@ovo.id>
 * @since 2017.10.07
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieHolder> {

    private Context context;
    private List<Movie> movies = new ArrayList<>();

    public MoviesAdapter(Context context) {
        this.context = context;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = new ArrayList<>();
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    public void addMovies(List<Movie> movies) {
        if (this.movies == null) {
            this.movies = new ArrayList<>();
        }
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_movie, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        int pos = holder.getAdapterPosition();

        String urlImage = Constans.BASE_IMAGE_URL + "w342" + movies.get(pos).getPosterPath();
        Picasso.with(context).load(urlImage).into(holder.imgMovie);

        holder.txtMovie.setText(movies.get(pos).getTitle());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder {

        MovieImageView imgMovie;
        TextView txtMovie;

        MovieHolder(View itemView) {
            super(itemView);
            imgMovie = itemView.findViewById(R.id.img_movie);
            txtMovie = itemView.findViewById(R.id.txt_movie);
        }
    }
}
