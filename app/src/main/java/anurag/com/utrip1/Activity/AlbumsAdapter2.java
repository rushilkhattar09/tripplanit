package anurag.com.utrip1.Activity;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import anurag.com.utrip1.R;

public class AlbumsAdapter2 extends RecyclerView.Adapter<AlbumsAdapter2.MyViewHolder> {

    private Context mContext;
    private List<Album2> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count,places,latitude,longitude,rating,type,time1;

        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            places = view.findViewById(R.id.title);
            rating = view.findViewById(R.id.title2);
            type = view.findViewById(R.id.title3);



            //  count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            // overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }


    public AlbumsAdapter2(Context mContext, List<Album2> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card2, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Album2 album2 = albumList.get(position);
        holder.places.setText("Place - "+album2.getPlace());
        holder.rating.setText("Rating - "+album2.getrating().toString());
        holder.type.setText("Type - "+album2.getType());


        // holder.count.setText(album.getNumOfSongs() + " songs");

        // loading album cover using Glide library
        Glide.with(mContext).load(album2.getThumbnail()).into(holder.thumbnail);

       /* holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);*/


    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }



    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}