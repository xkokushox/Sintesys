package com.freakybyte.syntesys.controller.home.constructors;

/**
 * Created by Jose Torres in FreakyByte on 27/06/16.
 */
public interface HomePresenter {

    /**
     * Starts the timer of calculation.
     *
     */
    void calculatePi();

    /**
     * Stops the timer of calculation.
     *
     */
    void stopCalculatePi();

    /**
     * Pauses the timer of calculation.
     *
     */
    void pauseCalculatePi();

    void onDestroy();

}
