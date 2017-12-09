package org.kurukshetra.stark.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
import org.kurukshetra.stark.Entities.Events.EventsEntity;
import org.kurukshetra.stark.R;

import java.util.List;


public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {
    Context context;
    private OnItemClickListener mOnItemClickListener;
    private List<EventsEntity> eventsEntityList;
    Drawable bd,gd;
    public EventListAdapter(List<EventsEntity> eel, Context context, Drawable drawable,Drawable gd){
        this.eventsEntityList = eel;
        this.context = context;
        this.bd = drawable;
        this.gd = gd;
    }

    @Override
    public EventListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item,parent,false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position){

        holder.eventName.setText(eventsEntityList.get(position).getEventName());
        holder.eventName.setTypeface(UserDetails.getRightiousFont(context));
        holder.imagecard.setImageDrawable(bd);
        holder.imagecard.setAlpha(0.7f);
        holder.imagecard.setForeground(UserDetails.getRandomGradient());
        holder.eventCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClicked(holder.getAdapterPosition(), holder.eventName);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventsEntityList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView eventName;
        CardView eventCard;
        ImageView imagecard;
        ViewHolder(View itemView){
            super(itemView);
            eventName =(TextView)itemView.findViewById(R.id.events_category);
            eventCard = itemView.findViewById(R.id.eventCard);
            imagecard = itemView.findViewById(R.id.eventImage);
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
