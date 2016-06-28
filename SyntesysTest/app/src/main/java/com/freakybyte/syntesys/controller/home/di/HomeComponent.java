package com.freakybyte.syntesys.controller.home.di;

import com.freakybyte.syntesys.controller.home.scope.ActivityScoped;
import com.freakybyte.syntesys.controller.home.ui.HomeActivity;

import dagger.Component;

/**
 * Created by Jose Torres in FreakyByte on 27/06/16.
 */
@ActivityScoped
@Component(modules = {HomeModule.class})
public interface HomeComponent {
    void inject(HomeActivity activity);
}
