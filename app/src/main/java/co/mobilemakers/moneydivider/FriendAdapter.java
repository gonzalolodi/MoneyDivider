package co.mobilemakers.moneydivider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Gonzalo on 11/02/2015.
 */
public class FriendAdapter extends ArrayAdapter<Friend> {

    Context mContext;
    List<Friend> mFriends;


    public FriendAdapter(Context context, List<Friend> friends) {
        super(context, R.layout.friend_list_item, friends);
        mContext = context;
        mFriends = friends;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        rowView = reuseOrGenerateRowView(convertView, parent);
        displayContentInView(position, rowView);
        return rowView;
    }

    private View reuseOrGenerateRowView(View convertView, ViewGroup parent) {
        View rowView;
        if (convertView != null) {
            rowView = convertView;
        } else {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.friend_list_item, parent, false);
        }
        return rowView;
    }

    private void displayContentInView(int position, View rowView) {
        if (rowView != null) {
            TextView textViewName = (TextView) rowView.findViewById(R.id.text_view_friend_name);
            textViewName.setText(mFriends.get(position).getName());
            TextView textViewMoney = (TextView) rowView.findViewById(R.id.text_view_friend_credit);
            textViewName.setText(mFriends.get(position).getMoney());
        }
    }
}
