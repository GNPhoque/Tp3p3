package l.jordan.myapplication;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class PhotoRecyclerAdapter extends RecyclerView.Adapter<PhotoRecyclerAdapter.ViewHolder> {

    private List<Photo> items;
    private int itemLayout;

    public PhotoRecyclerAdapter(List<Photo> items, int itemLayout) {
        this.items = items;
        this.itemLayout = itemLayout;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        Photo item = items.get(position);
        holder.id.setText(Integer.toString(item.id));
        holder.albumId.setText(Integer.toString(item.albumId));
        holder.title.setText(item.title);
        holder.url.setText(item.url);
        Picasso.get().load(item.url).into(holder.image);
//        Picasso.with(holder.image.getContext()).cancelRequest(holder.image);
//        Picasso.with(holder.image.getContext()).load(item.getImage()).into(holder.image);
        holder.itemView.setTag(item);
    }

    @Override public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id;
        public TextView albumId;
        public TextView title;
        public TextView url;
        public ImageView image;

        public ViewHolder(View v) {
            super(v);
            id = v.findViewById(R.id.id);
            albumId = v.findViewById(R.id.albumId);
            title = v.findViewById(R.id.title);
            url = v.findViewById(R.id.url);
            image = v.findViewById(R.id.image);
        }
    }
}