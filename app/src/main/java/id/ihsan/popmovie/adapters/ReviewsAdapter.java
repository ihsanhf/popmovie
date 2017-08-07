package id.ihsan.popmovie.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.ihsan.popmovie.R;
import id.ihsan.popmovie.models.Review;

/**
 * Created by Ihsan Helmi Faisal
 * on 8/7/2017.
 */

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewHolder> {

    private List<Review> reviews = new ArrayList<>();

    public void setReviews(List<Review> reviews) {
        this.reviews = new ArrayList<>();;
        this.reviews.addAll(reviews);
        notifyDataSetChanged();
    }

    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_review, parent, false);
        return new ReviewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewHolder holder, int position) {
        int pos = holder.getAdapterPosition();

        Review review = reviews.get(pos);
        holder.txtReviewer.setText(review.getAuthor());
        holder.txtReview.setText(review.getContent());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    class ReviewHolder extends RecyclerView.ViewHolder {

        TextView txtReviewer;
        TextView txtReview;

        ReviewHolder(View itemView) {
            super(itemView);
            txtReviewer = itemView.findViewById(R.id.txt_reviewer);
            txtReview = itemView.findViewById(R.id.txt_review);
        }
    }
}
