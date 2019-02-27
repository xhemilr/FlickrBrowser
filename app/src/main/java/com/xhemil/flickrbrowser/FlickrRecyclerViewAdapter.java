package com.xhemil.flickrbrowser;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

class FlickrRecyclerViewAdapter extends RecyclerView.Adapter<FlickrRecyclerViewAdapter.FlickrImageViewHolder>{

    private List<Photo> mPhotosList;
    private Context mContext;

    public FlickrRecyclerViewAdapter(Context context, List<Photo> photosList ) {
        mPhotosList = photosList;
        mContext = context;
    }

    @NonNull
    @Override
    public FlickrImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new FlickrImageViewHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.browse, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FlickrImageViewHolder flickrImageViewHolder, int i) {
        if (mPhotosList == null || mPhotosList.size() == 0){
            flickrImageViewHolder.thumbinail.setImageResource(R.drawable.placeholder);
            flickrImageViewHolder.thumbinail.setVisibility(View.INVISIBLE);
        }else{
            Photo photoItem = mPhotosList.get(i);
            Picasso.get().load(photoItem.getImage())
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(flickrImageViewHolder.thumbinail);
            flickrImageViewHolder.title.setText(photoItem.getTitle());
            }
        }

    @Override
    public int getItemCount() {
        return ((mPhotosList != null) && (mPhotosList.size() != 0) ? mPhotosList.size() : 1);
    }

    void loadNewData(List<Photo> newPhoto){
        mPhotosList = newPhoto;
        notifyDataSetChanged();
    }

    public Photo getPhoto(int position){
        return ((mPhotosList != null) && (mPhotosList.size() != 0) ? mPhotosList.get(position) : null);
    }

    static class FlickrImageViewHolder extends RecyclerView.ViewHolder {

        ImageView thumbinail;
        TextView title;

        public FlickrImageViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbinail = itemView.findViewById(R.id.thumbinail);
            title = itemView.findViewById(R.id.title);
        }
    }
}
