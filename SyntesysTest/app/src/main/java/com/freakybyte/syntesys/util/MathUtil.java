package com.freakybyte.syntesys.util;

import java.util.Random;

/**
 * Created by Jose Torres in FreakyByte on 27/06/16.
 */
public class MathUtil {

    private static MathUtil mSingleton;
    private Random rnd;

    public MathUtil() {
        rnd = new Random();
    }

    public static MathUtil getSingleton() {
        if (mSingleton == null)
            mSingleton = new MathUtil();
        return mSingleton;
    }

    public double computePi() {
        int n = (int) (rnd.nextDouble() * 10000 + 1);

        double sequenceFormula = 0;
        for (int counter = 1; counter < n; counter += 2) {
            sequenceFormula = sequenceFormula + ((1.0 / (2.0 * counter - 1)) - (1.0 / (2.0 * counter + 1)));
        }
        double pi = 4 * sequenceFormula;
        return pi;
    }


}
