package org.kurukshetra.stark.Adapters;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.kurukshetra.stark.Common.UserDetails;
import org.kurukshetra.stark.Entities.Events.EventCategoryEntity;
import org.kurukshetra.stark.Entities.Events.EventsCategoryResponseEntity;
import org.kurukshetra.stark.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Balaji on 12/3/2017.
 */

public class EventCategoryAdapter extends RecyclerView.Adapter<EventCategoryAdapter.ViewHolder> {
    Context context;
    private OnItemClickListener mOnItemClickListener;
    private List<EventCategoryEntity> categoriesListEntityEvent;
    int[] myImageList = new int[]{R.drawable.engineering, R.drawable.robotics,R.drawable.management,R.drawable.extravaganza,R.drawable.coding,R.drawable.quiz,R.drawable.online};
    public EventCategoryAdapter(EventsCategoryResponseEntity eventsCategoryResponseEntity, Context context){
        this.categoriesListEntityEvent = eventsCategoryResponseEntity.getCategories();
        this.context = context;
    }
    public EventCategoryAdapter(){
        categoriesListEntityEvent =new ArrayList<EventCategoryEntity>();
    }
    public void setCategoriesListEntityEvent(List<EventCategoryEntity> categoriesListEntityEvent) {
        this.categoriesListEntityEvent = categoriesListEntityEvent;
        notifyDataSetChanged();
    }

    @Override
    public EventCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_category_item,parent,false);
        ViewHolder myViewHolder=new ViewHolder(view);
        return myViewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position){

        //text
        holder.eventCategory.setText(categoriesListEntityEvent.get(position).getName());
        holder.count.setText(String.format(Locale.US,"%d", categoriesListEntityEvent.get(position).getEvents().size()));

        //colors
       final int[] colors = UserDetails.getRandomGradientColors();
        GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors);
        gd.setCornerRadius(0f);
        holder.event_image.setImageDrawable(context.getResources().getDrawable(myImageList[position]));
        holder.event_image.setForeground(gd);
        holder.event_image.setAlpha(0.7f);

        //typeface
        holder.eventCategory.setTypeface(UserDetails.getRightiousFont(context));
        holder.count.setTypeface(UserDetails.getRightiousFont(context));

        holder.eventCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClicked(holder.getAdapterPosition(), holder.eventCategory,colors[0],colors[1],myImageList[position]);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoriesListEntityEvent.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView eventCategory,count;
        CardView eventCard;
        ImageView event_image;

        public  ViewHolder(View itemView){
            super(itemView);
            eventCategory=(TextView)itemView.findViewById(R.id.events_category);
            eventCard = itemView.findViewById(R.id.eventCard);
            count = itemView.findViewById(R.id.count);
            event_image = itemView.findViewById(R.id.event_image);
        }
    }

    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClicked(int pos, View view,int color1,int color2,int bgimage);
    }
}
