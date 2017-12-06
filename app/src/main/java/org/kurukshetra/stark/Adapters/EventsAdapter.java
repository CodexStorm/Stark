package org.kurukshetra.stark.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.kurukshetra.stark.Common.UserDetails;
import org.kurukshetra.stark.Entities.CategoriesEntity;
import org.kurukshetra.stark.Entities.CategoriesResponseEntity;
import org.kurukshetra.stark.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Balaji on 12/3/2017.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {
    Context context;
    private OnItemClickListener mOnItemClickListener;
    private List<CategoriesEntity> categoriesEntityList;
    public EventsAdapter(CategoriesResponseEntity categoriesResponseEntity,Context context){
        this.categoriesEntityList= categoriesResponseEntity.getCategories();
        this.context = context;
    }
    public EventsAdapter(){
        categoriesEntityList=new ArrayList<CategoriesEntity>();
    }
    public void setCategoriesEntityList(List<CategoriesEntity> categoriesEntityList) {
        this.categoriesEntityList = categoriesEntityList;
        notifyDataSetChanged();
    }

    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_category_item,parent,false);
        ViewHolder myViewHolder=new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position){
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.eventCategory.setText(categoriesEntityList.get(position).getName());
        holder.eventCard.setBackgroundColor(color);
        if(color > 128){
            holder.eventCategory.setTextColor(Color.parseColor("#000000"));
        }else {
            holder.eventCategory.setTextColor(Color.parseColor("#ffffff"));
        }
        holder.eventCategory.setTypeface(UserDetails.getRightiousFont(context));
        holder.eventCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClicked(holder.getAdapterPosition(), holder.eventCard);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoriesEntityList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView eventCategory;
        CardView eventCard;
        public  ViewHolder(View itemView){
            super(itemView);
            eventCategory=(TextView)itemView.findViewById(R.id.events_category);
            eventCard = itemView.findViewById(R.id.eventCard);
        }
    }

    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClicked(int pos, View view);
    }
}
