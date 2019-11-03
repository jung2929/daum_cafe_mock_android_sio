package com.softsqaured.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.bookmarklist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.softsqaured.softsquared_daum_cafe.R;
import com.softsqaured.softsquared_daum_cafe.src.BaseFragment;
import com.softsqaured.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.bookmarklist.interfaces.BookmarkListFragmentView;

public class BookmarkListFragment extends BaseFragment implements BookmarkListFragmentView {

    public BookmarkListFragment() {
    }

    public static BookmarkListFragment newInstance() {

        BookmarkListFragment fragment = new BookmarkListFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookmark_mycafe, container, false);
        return view;
    }
}
