package com.bpapps.jobimclone.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bpapps.jobimclone.R;
import com.bpapps.jobimclone.dataclasees.JobTypeDataManager;

import java.util.ArrayList;


public class JobTypesAdapter extends RecyclerView.Adapter<JobTypesAdapter.JobViewHolder> {

    private static final String TAG = "TAG." + JobTypesAdapter.class.getSimpleName();

    private ArrayList<JobTypeDataManager> mDataSet;
    private Context mContext;
    private IOnJobTypeCLickListener mOnJobTypeCLickListener;
    private JobTypeDataManager mCheckedJob;

    public interface IOnJobTypeCLickListener {
        void jobTypeClicked(JobTypeDataManager itemClicked);
    }

    public JobTypesAdapter(ArrayList<JobTypeDataManager> dataSet, Context context, IOnJobTypeCLickListener onJobTypeCLickListener) {
        mDataSet = dataSet;
        mContext = context;
        mOnJobTypeCLickListener = onJobTypeCLickListener;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_item_job_type, parent, false);
        JobViewHolder viewHolder = new JobViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        holder.bind(mDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    //ViewHolder
    ////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////
    public class JobViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIVJobTypeIc;
        private TextView mTextViewJobDescription;
        private RadioButton mRadioButtonTypeChosen;

        private JobTypeDataManager mJob;

        public JobViewHolder(@NonNull View itemView) {
            super(itemView);

            mIVJobTypeIc = itemView.findViewById(R.id.ivJobTypeIcon);
            mTextViewJobDescription = itemView.findViewById(R.id.textViewJobTypeDescription);
            mRadioButtonTypeChosen = itemView.findViewById(R.id.radioButtonTypeChosen);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, mJob.toString());

                    if (mCheckedJob != null)
                        mCheckedJob.setChecked(false);

                    mJob.setChecked(true);
                    mCheckedJob = mJob;

                    notifyDataSetChanged();

                    if (mOnJobTypeCLickListener != null)
                        mOnJobTypeCLickListener.jobTypeClicked(mJob);
                }
            });
        }

        private void bind(JobTypeDataManager job) {
            mJob = job;

            mTextViewJobDescription.setText(mJob.getDescription());
            mIVJobTypeIc.setImageResource(mJob.getJobIconId());
            mRadioButtonTypeChosen.setChecked(job.isChecked());
        }
    }
}
