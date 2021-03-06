package com.softsquared.softsquared_daum_cafe.src.main;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseActivity;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.home.HomeFragment;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.MyCafeFragment;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.notification.NotificationFragment;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.PopularFragment;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.setting.SettingFragment;
import com.softsquared.softsquared_daum_cafe.src.main.interfaces.MainActivityView;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.USER_LOGINNED;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.sSharedPreferences;

public class MainActivity extends BaseActivity implements MainActivityView {

    private BottomNavigationView bottomNavMain;
    private FrameLayout flMain;

    private CoordinatorLayout.LayoutParams mLayoutParams;

    private boolean FIRST_LOADING = true;
    private int actionBarHeight = 0;

    private int prev_nav = R.id.nav_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* findViewByID */
        bottomNavMain = findViewById(R.id.bottomnav_main);
        flMain = findViewById(R.id.frame_main);


        /* Init View */
        getSupportFragmentManager().beginTransaction().add(R.id.frame_main, HomeFragment.newInstance(), "HOME").commit();

        /* Bottom Navigation View */
        bottomNavMain.setSelectedItemId(R.id.nav_home);
        bottomNavMain.setOnNavigationItemSelectedListener(this);
        setBottomNav(0);

        /* Constants */
        FIRST_LOADING = false;
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    private void setBottomNav(int marginBottom) {
        mLayoutParams = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mLayoutParams.bottomMargin = marginBottom;
        flMain.setLayoutParams(mLayoutParams);
        if (marginBottom == 0) {
            bottomNavMain.setBackgroundColor(Color.TRANSPARENT);
        } else
            bottomNavMain.setBackgroundColor(getColor(R.color.color_bottom_nav));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (prev_nav == menuItem.getItemId())
            return false;
        prev_nav = menuItem.getItemId();
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, HomeFragment.newInstance(), "HOME").commit();
                setBottomNav(0);
                return true;
            case R.id.nav_mycafe:
                if (!sSharedPreferences.getBoolean(USER_LOGINNED, false) && !FIRST_LOADING) {
                    mLoginAlert.show();
                    return false;
                } else if (sSharedPreferences.getBoolean(USER_LOGINNED, false)) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, MyCafeFragment.newInstance(), "MYCAFE").commit();
                }
                setBottomNav(actionBarHeight);
                return true;
            case R.id.nav_popular:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, PopularFragment.newInstance(), "POPULAR").commit();
                setBottomNav(actionBarHeight);
                return true;
            case R.id.nav_notification:
                if (!sSharedPreferences.getBoolean(USER_LOGINNED, false) && !FIRST_LOADING) {
                    mLoginAlert.show();
                    return false;
                } else if (sSharedPreferences.getBoolean(USER_LOGINNED, false)) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, NotificationFragment.newInstance(), "NOTIFICATION").commit();
                }
                setBottomNav(actionBarHeight);
                return true;
            case R.id.nav_setting:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, SettingFragment.newInstance(), "SETTING").commit();
                setBottomNav(actionBarHeight);
                return true;
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onNavigationItemReselected(@NonNull MenuItem menuItem) {

    }
}
