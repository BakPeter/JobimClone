package com.bpapps.jobimclone.fragments.myjobsfragments.searchparameters;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.bpapps.jobimclone.R;
import com.bpapps.jobimclone.adapters.SearchTypePagerAdapter;

public class SearchParametersDefinerFragment extends Fragment
        implements View.OnClickListener {

    private static final String TAG = "TAG." + SearchParametersDefinerFragment.class.getName();
    public static final String FRAGMENT_TAG = "FRAGMENT_TAG." + SearchParametersDefinerFragment.class.getName();
    public static final String STACK_TAG = "STACK_TAG." + SearchParametersDefinerFragment.class.getName();

    private TextView mTextViewSearchCompany;
    private TextView mTextViewSearchLocation;
    private TextView mTextViewSearchJobs;
    private TextView mCurrentSearchType;

    private ViewPager2 mViewPagerSearchType;
    private FragmentStateAdapter mAdapter;

    public static SearchParametersDefinerFragment getInstance() {
        return new SearchParametersDefinerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_serach_parameters_definer, container, false);

        requireActivity().getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        //    Log.d(TAG, requireActivity().getWindow().getDecorView().getLayoutDirection()==View.LAYOUT_DIRECTION_RTL?"RTL":"LTR");

        mTextViewSearchCompany = view.findViewById(R.id.text_view_search_company);
        mTextViewSearchCompany.setOnClickListener(this);

        mTextViewSearchLocation = view.findViewById(R.id.text_view_search_location);
        mTextViewSearchLocation.setOnClickListener(this);

        mTextViewSearchJobs = view.findViewById(R.id.text_view_search_jobs);
        mTextViewSearchJobs.setOnClickListener(this);
        mCurrentSearchType = mTextViewSearchJobs;

        view.findViewById(R.id.text_view_action_cancel).setOnClickListener(this);
        view.findViewById(R.id.text_view_action_confirm).setOnClickListener(this);

        mViewPagerSearchType = view.findViewById(R.id.view_pager_search_type);
        mAdapter = new SearchTypePagerAdapter(this);
        mViewPagerSearchType.setAdapter(mAdapter);
        mViewPagerSearchType.setCurrentItem(2);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) requireActivity();

        //    activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setupToolBar(activity);
    }

    @Override
    public void onPause() {
        super.onPause();

        // requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    private void setupToolBar(AppCompatActivity activity) {
        activity.getSupportActionBar().hide();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.text_view_action_cancel:
                Log.d(TAG, "cancel");
                break;
            case R.id.text_view_action_confirm:
                Log.d(TAG, "confirm");
                break;
            default:
                changeSearchType(id);
                break;
        }
    }

    private void changeSearchType(int id) {
        if (id == mCurrentSearchType.getId()) {
            Log.d(TAG, "current search chosen, no action taken");
        } else {
            setCurrentSearchToUnTaped();

            switch (id) {
                case R.id.text_view_search_company:
                    Log.d(TAG, "text_view_search_company");
                    mTextViewSearchCompany.setBackground(getResources().getDrawable(R.drawable.cornered_left_frame_taped_search_parmeters));
                    mTextViewSearchCompany.setTextAppearance(requireContext(), R.style.taped_chosen_theme);
                    mCurrentSearchType = mTextViewSearchCompany;
                    mViewPagerSearchType.setCurrentItem(0);
                    break;
                case R.id.text_view_search_location:
                    Log.d(TAG, "text_view_search_location");
                    mTextViewSearchLocation.setBackground(getResources().getDrawable(R.drawable.centered_not_cornered_taped_frame));
                    mTextViewSearchLocation.setTextAppearance(requireContext(), R.style.taped_chosen_theme);
                    mCurrentSearchType = mTextViewSearchLocation;
                    mViewPagerSearchType.setCurrentItem(1);
                    break;
                case R.id.text_view_search_jobs:
                    Log.d(TAG, "text_view_search_jobs");
                    mTextViewSearchJobs.setBackground(getResources().getDrawable(R.drawable.cornered_right_frame_taped_search_parameters));
                    mTextViewSearchJobs.setTextAppearance(requireContext(), R.style.taped_chosen_theme);
                    mCurrentSearchType = mTextViewSearchJobs;
                    mViewPagerSearchType.setCurrentItem(2);
                    break;
            }
        }
    }

    private void setCurrentSearchToUnTaped() {
        switch (mCurrentSearchType.getId()) {
            case R.id.text_view_search_company:
                Log.d(TAG, "text_view_search_company");
                mTextViewSearchCompany.setBackground(getResources().getDrawable(R.drawable.cornered_left_frame_un_taped_search_parmeters));
                mTextViewSearchCompany.setTextAppearance(requireContext(), R.style.taped_not_chosen_theme);
                break;
            case R.id.text_view_search_location:
                Log.d(TAG, "text_view_search_location");
                mTextViewSearchLocation.setBackground(getResources().getDrawable(R.drawable.centered_not_cornered_un_taped_frame));
                mTextViewSearchLocation.setTextAppearance(requireContext(), R.style.taped_not_chosen_theme);
                break;
            case R.id.text_view_search_jobs:
                Log.d(TAG, "text_view_search_jobs");
                mTextViewSearchJobs.setBackground(getResources().getDrawable(R.drawable.cornered_right_frame_un_taped_search_parameters));
                mTextViewSearchJobs.setTextAppearance(requireContext(), R.style.taped_not_chosen_theme);
                break;
        }
    }

}
