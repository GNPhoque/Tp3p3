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


public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder> {

    private List<User> items;
    private int itemLayout;

    public UserRecyclerAdapter(List<User> items, int itemLayout) {
        this.items = items;
        this.itemLayout = itemLayout;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        User item = items.get(position);
        holder.id.setText(Integer.toString(item.id));
        holder.name.setText(item.name);
        holder.username.setText(item.username);
//        Picasso.with(holder.image.getContext()).cancelRequest(holder.image);
//        Picasso.with(holder.image.getContext()).load(item.getImage()).into(holder.image);
        holder.itemView.setTag(item);
    }

    @Override public int getItemCount() {
        return items.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id;
        public TextView name;
        public TextView username;

        public ViewHolder(View v) {
            super(v);
            id = v.findViewById(R.id.id);
            name= v.findViewById(R.id.name);
            username = v.findViewById(R.id.username);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, AlbumsActivity.class);
                    int uId = items.get(getLayoutPosition()).id;
                    intent.putExtra("userId", uId);
                    context.startActivity(intent);
                }
            });
        }
    }
}