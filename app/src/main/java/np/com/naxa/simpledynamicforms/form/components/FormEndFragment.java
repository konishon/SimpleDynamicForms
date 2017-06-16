package np.com.naxa.simpledynamicforms.form.components;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import np.com.naxa.simpledynamicforms.R;
import np.com.naxa.simpledynamicforms.form.listeners.onFormFinishedListener;
import np.com.naxa.simpledynamicforms.formhost.FormEntryActivity;


public class FormEndFragment extends Fragment implements FormEntryActivity.fragmentStateListener {

    private onFormFinishedListener listener;

    public FormEndFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_form_end, container, false);

        return rootview;

    }


    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onFormFinishedListener) {
            listener = (onFormFinishedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement onFormFinishedListener");
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) return;
        if (activity instanceof onFormFinishedListener) {
            listener = (onFormFinishedListener) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement onFormFinishedListener");
        }
    }


    private void notifyFormHasEnded(int pos) {

        try {
            listener.uploadForm();
        } catch (ClassCastException cce) {

        }
    }

    @Override
    public void fragmentStateChange(int state, int pos) {
        notifyFormHasEnded(pos);
    }
}
