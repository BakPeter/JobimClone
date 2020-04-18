package com.bpapps.jobimclone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bpapps.jobimclone.R;
import com.bpapps.jobimclone.dataclasees.Country;

import java.util.ArrayList;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountryViewHolder> {
    private ArrayList<Country> mDataSet;
    private Context mContext;

    private IOnCountrySelectedListener mCountryItemClickListener;

    public CountriesAdapter(ArrayList<Country> dataSet, Context context, IOnCountrySelectedListener countryItemClickListener) {
        mDataSet = dataSet;
        mContext = context;
        mCountryItemClickListener = countryItemClickListener;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_item_country, parent, false);
        CountryViewHolder viewHolder = new CountryViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.bind(mDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void setCountryItemClickListener(IOnCountrySelectedListener countryItemClickListener) {
        mCountryItemClickListener = countryItemClickListener;
    }

    public interface IOnCountrySelectedListener {
        void onCountrySelected(Country item);
    }


    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //view holder
    public class CountryViewHolder extends RecyclerView.ViewHolder {

        private Country mCountry;

        private TextView mTextViewName;
        private TextView mTextViewCode;
        private ImageView mImageViewFlag;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewName = itemView.findViewById(R.id.text_view_item_country_name);
            mTextViewCode = itemView.findViewById(R.id.text_view_item_country_code);
            mImageViewFlag = itemView.findViewById(R.id.image_view_item_flag);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCountryItemClickListener != null)
                        mCountryItemClickListener.onCountrySelected(mCountry);
                }
            });
        }

        public void bind(Country country) {
            mCountry = country;

            mTextViewName.setText(mCountry.getName());
            mTextViewCode.setText("(" + mCountry.getCodeNumber() + ")");
            mImageViewFlag.setImageDrawable(mCountry.getFlagDrawable());
        }
    }
}
