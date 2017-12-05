package org.kurukshetra.stark.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.kurukshetra.stark.Entities.CategoriesEntity;
import org.kurukshetra.stark.Entities.CategoriesList;
import org.kurukshetra.stark.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Balaji on 12/3/2017.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    private List<CategoriesEntity> categoriesEntityList;
    public EventsAdapter(CategoriesList categoriesList){
        this.categoriesEntityList=categoriesList.getCategoriesEntityList();
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_events,parent,false);
        ViewHolder myViewHolder=new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position){
        holder.eventCategory.setText(categoriesEntityList.get(position).getEventCategory());
    }

    @Override
    public int getItemCount() {
        return categoriesEntityList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView eventCategory;
        public  ViewHolder(View itemView){
            super(itemView);
            eventCategory=(TextView)itemView.findViewById(R.id.events_category);
        }
    }
}
