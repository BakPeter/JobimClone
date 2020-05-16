package com.bpapps.jobimclone.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bpapps.jobimclone.R;

public class NewJobHelpDialog extends DialogFragment
        implements View.OnClickListener {

    private static final String SUPPORT_PHONE_NUMBER = "972035555555";
    private static final String[] SUPPORT_EMAIL_ADDRESS = {"jobimsupoort@gmail.com", "jobimservice@gmail.com"};
    private static final String EMAIL_SUBJECT = "jobim support question";

    public static NewJobHelpDialog getInstance() {
        return new NewJobHelpDialog();
    }

    public NewJobHelpDialog() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_new_job_help, null);

        view.findViewById(R.id.tvClose).setOnClickListener(this);
        view.findViewById(R.id.ibHelpMail).setOnClickListener(this);
        view.findViewById(R.id.tvNewJobHelpMail).setOnClickListener(this);
        view.findViewById(R.id.ibHelpPhone).setOnClickListener(this);
        view.findViewById(R.id.tvNewJobHelpPhone).setOnClickListener(this);

        builder.setView(view);

        return builder.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvClose:
                //Toast.makeText(requireContext(), "close", Toast.LENGTH_SHORT).show();
                this.dismiss();
                break;
            case R.id.ibHelpMail:
            case R.id.tvNewJobHelpMail:
                Toast.makeText(requireContext(), "help : mail", Toast.LENGTH_SHORT).show();
                sendEMail();
                break;
            case R.id.ibHelpPhone:
            case R.id.tvNewJobHelpPhone:
//                Toast.makeText(requireContext(), "help : phone", Toast.LENGTH_SHORT).show();
                callPhone();
                break;
        }
    }

    private void sendEMail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, SUPPORT_EMAIL_ADDRESS);
        intent.putExtra(Intent.EXTRA_SUBJECT, EMAIL_SUBJECT);
        if (intent.resolveActivity((requireActivity().getPackageManager())) != null)
            startActivity(intent);
    }

    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + SUPPORT_PHONE_NUMBER));
        if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
