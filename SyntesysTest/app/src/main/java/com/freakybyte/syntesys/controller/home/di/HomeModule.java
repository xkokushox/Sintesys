package com.freakybyte.syntesys.controller.home.di;

import com.freakybyte.syntesys.controller.home.constructors.HomeView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jose Torres in FreakyByte on 27/06/16.
 */
@Module
public class HomeModule {

    private final HomeView mView;

    public HomeModule(HomeView view) {
        mView = view;
    }

    @Provides
    HomeView provideTasksContractView() {
        return mView;
    }

}
