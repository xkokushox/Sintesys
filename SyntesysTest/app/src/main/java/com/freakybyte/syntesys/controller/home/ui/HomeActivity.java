package com.freakybyte.syntesys.controller.home.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.freakybyte.syntesys.R;
import com.freakybyte.syntesys.controller.home.constructors.HomeView;
import com.freakybyte.syntesys.controller.home.di.DaggerHomeComponent;
import com.freakybyte.syntesys.controller.home.di.HomeModule;
import com.freakybyte.syntesys.controller.home.impl.HomePresenterImpl;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity implements HomeView {

    @Inject
    public HomePresenterImpl mPresenter;

    private Toolbar mToolbar;
    private TextView txtNumberPi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DaggerHomeComponent.builder()
                .homeModule(new HomeModule(this)).build().inject(this);

        setSupportActionBar(getToolbar());

    }

    @Override
    public void updatePi(final double pi, final String elapse) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getTxtNumberPi().setText(String.format(getString(R.string.txt_pi), getTxtNumberPi().getText().toString(), (float) pi, elapse));
            }
        });
    }

    @Override
    public void cancelPi() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getTxtNumberPi().setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_pause:
                mPresenter.pauseCalculatePi();
                return true;
            case R.id.action_start:
                mPresenter.calculatePi();
                return true;
            case R.id.action_stop:
                mPresenter.stopCalculatePi();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.calculatePi();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.pauseCalculatePi();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    private TextView getTxtNumberPi() {
        if (txtNumberPi == null)
            txtNumberPi = (TextView) findViewById(R.id.txtNumberPi);
        return txtNumberPi;
    }

    private Toolbar getToolbar() {
        if (mToolbar == null)
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
        return mToolbar;
    }

}
