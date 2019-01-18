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

import java.util.List;


public class AlbumRecyclerAdapter extends RecyclerView.Adapter<AlbumRecyclerAdapter.ViewHolder> {

    private List<Album> items;
    private int itemLayout;

    public AlbumRecyclerAdapter(List<Album> items, int itemLayout) {
        this.items = items;
        this.itemLayout = itemLayout;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        Album item = items.get(position);
        holder.id.setText(Integer.toString(item.id));
        holder.userId.setText(Integer.toString(item.userId));
        holder.title.setText(item.title);
//        Picasso.with(holder.image.getContext()).cancelRequest(holder.image);
//        Picasso.with(holder.image.getContext()).load(item.getImage()).into(holder.image);
        holder.itemView.setTag(item);
    }

    @Override public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id;
        public TextView userId;
        public TextView title;

        public ViewHolder(View v) {
            super(v);
            id = v.findViewById(R.id.id);
            userId = v.findViewById(R.id.userId);
            title = v.findViewById(R.id.title);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, PhotosActivity.class);
                    intent.putExtra("albumId",items.get(getLayoutPosition()).id);
                    context.startActivity(intent);
                }
            });
        }
    }
}