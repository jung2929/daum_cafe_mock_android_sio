package com.softsquared.softsquared_daum_cafe.src.signselect.signout.interfaces;

import android.view.View;

public interface SignOutActivityView extends View.OnClickListener{

    void validateSuccess(String message);
    void validateFailure(String message);
}
