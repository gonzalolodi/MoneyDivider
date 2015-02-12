package co.mobilemakers.moneydivider;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ResultFragment extends ListFragment {

    FriendAdapter mFriendAdapter;
    ArrayList<Friend> mFriends;

    public ResultFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_result, container, false);
        prepareListView();
        return rootView;
    }

    private void prepareListView() {
        List<Friend> entries = new ArrayList<>();
        mFriendAdapter = new FriendAdapter(getActivity(), entries);
        setListAdapter(mFriendAdapter);
        mFriends = this.getArguments().getParcelableArrayList(Friend.FRIEND_PARCELABLE_ARRAY);
        for (Friend f:mFriends) {
            mFriendAdapter.add(f);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


}
