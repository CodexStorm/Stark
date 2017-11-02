package org.kurukshetra.stark.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.kurukshetra.stark.Entities.ChatMessage;
import org.kurukshetra.stark.R;

import java.util.List;

/**
 * Created by ompra on 11/2/2017.
 */

public class ChatMessageAdapter extends ArrayAdapter<ChatMessage> {
    private static final int MY_MESSAGE = 0, OTHER_MESSAGE = 1;
    public ChatMessageAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ChatMessage> objects) {
        super(context, R.layout.item_my_message, objects);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage item = getItem(position);
        if(item.isMine()) return MY_MESSAGE;
        else return OTHER_MESSAGE;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int viewType = getItemViewType(position);
        if (viewType == MY_MESSAGE) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_my_message, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.text);
            textView.setText(getItem(position).getContent());
        }else if (viewType == OTHER_MESSAGE) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_bot_message, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.text);
            textView.setText(getItem(position).getContent());
        }

        return convertView;
    }
}
