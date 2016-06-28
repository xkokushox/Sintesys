package com.freakybyte.syntesys.controller.home.impl;

import android.os.CountDownTimer;
import android.support.annotation.Nullable;

import com.freakybyte.syntesys.controller.home.constructors.HomePresenter;
import com.freakybyte.syntesys.controller.home.constructors.HomeView;
import com.freakybyte.syntesys.util.MathUtil;

import java.util.Calendar;

import javax.inject.Inject;

/**
 * Created by Jose Torres in FreakyByte on 27/06/16.
 */
public class HomePresenterImpl implements HomePresenter {

    private HomeView mHomeView;

    private CountDownTimer cdTimer = null;
    private Calendar mToday;
    private boolean isRunning;
    private double pi;
    private long diff_in_ms;

    @Inject
    public HomePresenterImpl(@Nullable HomeView homeView) {
        mHomeView = homeView;
    }

    @Override
    public void calculatePi() {
        if (mHomeView == null)
            return;

        if (cdTimer == null)
            cdTimer = new CountDownTimer(500, 300) {
                @Override
                public void onTick(long millisUntilFinished) {
                    isRunning = true;
                }

                @Override
                public void onFinish() {
                    isRunning = false;
                    if (mHomeView != null) {
                        mToday = Calendar.getInstance();
                        pi = MathUtil.getSingleton().computePi();
                        diff_in_ms = Calendar.getInstance().getTimeInMillis() - mToday.getTimeInMillis();
                        mHomeView.updatePi(pi, String.valueOf(diff_in_ms));
                        start();
                    }
                }
            };
        if (!isRunning)
            cdTimer.start();
    }

    @Override
    public void stopCalculatePi() {
        if (mHomeView == null)
            return;

        isRunning = false;
        cdTimer.cancel();
        mHomeView.cancelPi();
    }

    @Override
    public void pauseCalculatePi() {
        if (mHomeView == null)
            return;

        isRunning = false;

        if (cdTimer != null)
            cdTimer.cancel();

    }

    @Override
    public void onDestroy() {
        mHomeView = null;
        cdTimer.cancel();
        cdTimer = null;
        mToday = null;
    }
}
