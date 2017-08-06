package id.ihsan.popmovie.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.ihsan.popmovie.R;
import id.ihsan.popmovie.models.Video;

/**
 * Created by Ihsan Helmi Faisal
 * on 8/7/2017.
 */

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.VideoHolder> {

    private List<Video> videos = new ArrayList<>();

    public void setMovies(List<Video> videos) {
        this.videos = new ArrayList<>();
        this.videos.addAll(videos);
        notifyDataSetChanged();
    }

    public Video getVideo(int position) {
        return videos.get(position);
    }

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_trailer, parent, false);
        return new VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoHolder holder, int position) {
        int pos = holder.getAdapterPosition();

        Video video = videos.get(pos);
        holder.txtVideo.setText(video.getName());
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    class VideoHolder extends RecyclerView.ViewHolder {

        TextView txtVideo;

        VideoHolder(View itemView) {
            super(itemView);
            txtVideo = itemView.findViewById(R.id.txt_trailer);
        }
    }
}
