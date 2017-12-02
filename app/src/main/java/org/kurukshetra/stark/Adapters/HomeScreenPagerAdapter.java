package org.kurukshetra.stark.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.kurukshetra.stark.R;

/**
 * Created by ompra on 12/2/2017.
 */

public class HomeScreenPagerAdapter extends PagerAdapter {
    private Context mContext;
    LayoutInflater mLayoutInflater;
    int[][] mResources;

    public HomeScreenPagerAdapter(Context context,int[][] resources) {
        mContext = context;
        mLayoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mResources = resources;
    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (LinearLayout)object;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item,container,false);
        ImageView imageView = itemView.findViewById(R.id.imageView);
        imageView.setImageResource(mResources[position][0]);
        TextView textView = itemView.findViewById(R.id.title);
        textView.setTypeface(Typeface.createFromAsset(mContext.getAssets(),"fonts/righteous.ttf"));
        textView.setText(mResources[position][2]);
        imageView.setForeground(mContext.getDrawable(mResources[position][1]));
        container.addView(itemView);
        return itemView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
