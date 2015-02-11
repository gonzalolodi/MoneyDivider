package co.mobilemakers.moneydivider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddFriendFragment extends Fragment {

    Button mButtonDone;
    EditText mEditTextName;
    EditText mEditTextMoney;

    public AddFriendFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_friend, container, false);
        wireUpEditTexts(rootView);
        mButtonDone = (Button) rootView.findViewById(R.id.button_done);
        mButtonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity();
                activity.setResult(Activity.RESULT_OK, getResultIntent());
                activity.finish();
            }
        });
        return rootView;
    }

    private Intent getResultIntent() {
        Intent intentAdd = new Intent();
        intentAdd.putExtra(Friend.FRIEND_NAME, mEditTextName.getText().toString());
        intentAdd.putExtra(Friend.FRIEND_MONEY, Integer.parseInt(mEditTextMoney.getText().toString()));
        return intentAdd;
    }

    private void wireUpEditTexts(View rootView) {
        mEditTextName = (EditText) rootView.findViewById(R.id.edit_text_friend_name);
        mEditTextMoney = (EditText) rootView.findViewById(R.id.edit_text_amount);
    }


}
