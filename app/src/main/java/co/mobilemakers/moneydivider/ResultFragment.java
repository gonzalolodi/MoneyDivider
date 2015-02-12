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
    ArrayList<Friend> mResultFriendDebts;

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
        mResultFriendDebts = new ArrayList<Friend>();
        for (Friend f:mFriends){
            if (f.getMoney() < 0 ) {
                for (Friend f2:mFriends){
                    if (f2.getMoney() > 0) {
                        Friend resultFriend = new Friend();
                        if (Math.abs(f.getMoney()) >= f2.getMoney()) {
                            resultFriend.setName(f.getName() + " has to pay " + f2.getName() + ":");
                            resultFriend.setMoney(f2.getMoney());
                            f.setMoney(f.getMoney() + f2.getMoney());
                            f2.setMoney(0);
                            mResultFriendDebts.add(resultFriend);
                        } else {
                            if (Math.abs(f.getMoney()) < f2.getMoney()){
                                resultFriend.setName(f.getName() + " has to pay " + f2.getName() + ":");
                                f2.setMoney(f2.getMoney() - Math.abs(f.getMoney()));
                                resultFriend.setMoney(Math.abs(f.getMoney()));
                                f.setMoney(0);
                                mResultFriendDebts.add(resultFriend);
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (Friend f:mResultFriendDebts) {
            mFriendAdapter.add(f);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


}
