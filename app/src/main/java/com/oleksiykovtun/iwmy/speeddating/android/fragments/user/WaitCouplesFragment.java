package com.oleksiykovtun.iwmy.speeddating.android.fragments.user;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oleksiykovtun.android.cooltools.CoolFragment;
import com.oleksiykovtun.android.cooltools.CoolFragmentManager;
import com.oleksiykovtun.iwmy.speeddating.Api;
import com.oleksiykovtun.iwmy.speeddating.R;
import com.oleksiykovtun.iwmy.speeddating.android.Account;
import com.oleksiykovtun.iwmy.speeddating.data.Attendance;
import com.oleksiykovtun.iwmy.speeddating.data.Couple;
import com.oleksiykovtun.iwmy.speeddating.data.Event;

import java.util.List;

/**
 * The 2nd stage of user waiting: waiting for organizer to confirm couples
 */
public class WaitCouplesFragment extends CoolFragment {

    private Event event = null;
    private static CountDownTimer timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_wait_couples, container, false);
        registerContainerView(view);

        event = (Event) getAttachment();

        return view;
    }

    @Override
    public void onReceiveWebData(List response) {
        if (response.size() > 0) {
            CoolFragmentManager.showAtBottom(new CoupleUserListFragment(), event);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        timer = new CountDownTimer(3600000, 6000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // checking until for this attendant couples are put
                post(Api.COUPLES + Api.GET_FOR_ATTENDANCE, Couple[].class,
                        new Attendance(Account.getUser(WaitCouplesFragment.this), event));
            }

            @Override
            public void onFinish() {
            }

        }.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (timer != null) {
            timer.cancel();
        }
    }

}