package com.bpapps.jobimclone.fragments.registarationfragments;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bpapps.jobimclone.R;
import com.bpapps.jobimclone.adapters.CountriesAdapter;
import com.bpapps.jobimclone.dataclasees.Country;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CountryChooserFragment extends Fragment implements CountriesAdapter.IOnCountrySelectedListener {

    public static final String STACK_TAG = "STACK_TAG." + CountryChooserFragment.class.getName();
    private RecyclerView mRecyclerView;
    private CountriesAdapter mAdapter;

    private ICountryCodeChosenListener mCountryCodeChosen;

    public interface ICountryCodeChosenListener {
        void codeChosen(String code);
    }

    public void setCountryCodeChosen(ICountryCodeChosenListener callback) {
        mCountryCodeChosen = callback;
    }

    public CountryChooserFragment() {
    }

    public static Fragment getInstance(ICountryCodeChosenListener countryCodeChosen) {
        CountryChooserFragment fragment = new CountryChooserFragment();
        fragment.setCountryCodeChosen(countryCodeChosen);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        requireActivity().getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        setUpToolBar((AppCompatActivity) requireActivity());

        View view = inflater.inflate(R.layout.fragment_country_chooser, container, false);

        mAdapter = new CountriesAdapter(getDataset(), requireContext(), this);

        mRecyclerView = view.findViewById(R.id.countries_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration horizontalDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        Drawable horizontalDivider = requireContext().getDrawable(R.drawable.line_divider_orange);
        horizontalDecoration.setDrawable(horizontalDivider);
        mRecyclerView.addItemDecoration(horizontalDecoration);

        return view;
    }

    private ArrayList<Country> getDataset() {
        ArrayList<Country> dataSet = new ArrayList<>();

        Resources resources = getResources();
        String[] names = resources.getStringArray(R.array.countries_names);
        String[] codes = resources.getStringArray(R.array.countries_codes);
        TypedArray flagIconsIds = resources.obtainTypedArray(R.array.countries_flags_ids);

        for (int i = 0; i < names.length; i++) {
            Country country = new Country(names[i], codes[i], flagIconsIds.getDrawable(i));
            dataSet.add(country);
        }

        return dataSet;
    }

    private void setUpToolBar(AppCompatActivity activity) {
        activity.getSupportActionBar().show();

        Toolbar toolbar = activity.findViewById(R.id.tool_bar);
        ((TextView) toolbar.findViewById(R.id.text_view_tool_bar_title)).setText(R.string.countries_chooser_tool_bar_title);
        toolbar.findViewById(R.id.image_view_main_nav).setVisibility(View.GONE);
    }

    @Override
    public void onCountrySelected(Country item) {
        if (mCountryCodeChosen != null)
            mCountryCodeChosen.codeChosen(item.getCodeNumber());

        requireActivity().getSupportFragmentManager()
                .popBackStack();
    }
}
