package co.mobilemakers.moneydivider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class FriendListFragment extends ListFragment{

    FriendAdapter mFriendAdapter;



    public final static int REQUEST_CODE = 0;

    public FriendListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friend_list, container, false);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fragment_main,menu);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Boolean handled = false;
        switch (id){
            case R.id.action_add:
                Intent intent = new Intent(getActivity(), AddFriendActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                handled = true;
                break;
            case R.id.action_done:
                List<Friend> friendsAdded = mFriendAdapter.mFriends;
                float totalAmount = 0;
                int totalFriends = 0;
                for (Friend f:friendsAdded) {
                    totalAmount += f.getMoney();
                    totalFriends ++;
                }
                if (totalFriends > 0) {
                    float payingAmountPerPerson = totalAmount/totalFriends;
                    Intent resultIntent = new Intent(getActivity(), ResultActivity.class);
                    for (Friend f:friendsAdded){
                        f.setMoney(f.getMoney() - payingAmountPerPerson);
                    }
                    resultIntent.putParcelableArrayListExtra(Friend.FRIEND_PARCELABLE_ARRAY, (ArrayList<? extends android.os.Parcelable>) friendsAdded);
                    startActivity(resultIntent);
                } else {
                    Toast.makeText(getActivity(),"You haven't added any friend",Toast.LENGTH_SHORT).show();
                }


                /*getFragmentManager().beginTransaction().
                        replace(R.id.container, new ResultFragment()).addToBackStack(null).
                        commit();
*/
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        prepareListView();
    }

    private void prepareListView() {
        List<Friend> entries = new ArrayList<>();
        mFriendAdapter = new FriendAdapter(getActivity(), entries);
        setListAdapter(mFriendAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK){
                String name = data.getStringExtra(Friend.FRIEND_NAME);
                float money = data.getFloatExtra(Friend.FRIEND_MONEY, 0);
                Friend friend = new Friend(name, money);
                mFriendAdapter.add(friend);
            }
        }
    }
}
