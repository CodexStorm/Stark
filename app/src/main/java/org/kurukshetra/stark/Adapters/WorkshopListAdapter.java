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
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.kurukshetra.stark.Common.UserDetails;
import org.kurukshetra.stark.Entities.Workshops.WorkshopsEntity;
import org.kurukshetra.stark.R;

import java.util.List;


public class WorkshopListAdapter extends RecyclerView.Adapter<WorkshopListAdapter.ViewHolder> {
    Context context;
    Drawable bd;
    Drawable gd;
    private OnItemClickListener mOnItemClickListener;
    private List<WorkshopsEntity> workshopsEntityList;
    public WorkshopListAdapter(List<WorkshopsEntity> workshopsEntityList, Context context, Drawable drawable,Drawable gd){
        this.workshopsEntityList = workshopsEntityList;
        this.context = context;
        this.bd = drawable;
        this.gd = gd;
    }

    @Override
    public WorkshopListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workshop_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position){

        holder.eventName.setText(workshopsEntityList.get(position).getName());
        holder.eventName.setTypeface(UserDetails.getRightiousFont(context));
        holder.imagecard.setImageDrawable(bd);
        holder.rlworkshoplist.setAlpha(0.7f);
        holder.rlworkshoplist.setBackground(UserDetails.getRandomGradient());
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
        return workshopsEntityList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView eventName;
        ImageView imagecard;
        CardView eventCard;
        RelativeLayout rlworkshoplist;
        ViewHolder(View itemView){
            super(itemView);
            eventName =(TextView)itemView.findViewById(R.id.events_category);
            eventCard = itemView.findViewById(R.id.eventCard);
            imagecard = itemView.findViewById(R.id.imagecard);
            rlworkshoplist = itemView.findViewById(R.id.rlworkshoplist);
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
