package org.kurukshetra.stark.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.kurukshetra.stark.Common.UserDetails;
import org.kurukshetra.stark.Entities.EventsEntity;
import org.kurukshetra.stark.R;

import java.util.ArrayList;
import java.util.List;


public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {
    Context context;
    private OnItemClickListener mOnItemClickListener;
    private List<EventsEntity> eventsEntityList;
    public EventListAdapter(List<EventsEntity> eel, Context context){
        this.eventsEntityList = eel;
        this.context = context;
    }
    public EventListAdapter(){
        eventsEntityList =new ArrayList<EventsEntity>();
    }

    @Override
    public EventListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position){

        holder.eventName.setText(eventsEntityList.get(position).getEventName());
        holder.eventName.setTypeface(UserDetails.getRightiousFont(context));
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
        ViewHolder(View itemView){
            super(itemView);
            eventName =(TextView)itemView.findViewById(R.id.events_category);
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
