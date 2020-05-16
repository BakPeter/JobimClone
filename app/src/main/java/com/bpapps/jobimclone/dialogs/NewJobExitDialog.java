package com.bpapps.jobimclone.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;

import com.bpapps.jobimclone.R;

public class NewJobExitDialog extends DialogFragment
        implements View.OnClickListener {

    private IDialogButtonOnClickListener mDialogOnClickListener;

    public static NewJobExitDialog getInstance(IDialogButtonOnClickListener callBack) {
        return new NewJobExitDialog(callBack);
    }

    public NewJobExitDialog(IDialogButtonOnClickListener dialogOnClickListener) {
        mDialogOnClickListener = dialogOnClickListener;
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
        View view = inflater.inflate(R.layout.dialog_new_job_exit, null);

        AppCompatButton buttonStay = view.findViewById(R.id.buttonStay);
        buttonStay.setOnClickListener(this);


        AppCompatButton buttonClose = view.findViewById(R.id.buttonClose);
        buttonClose.setOnClickListener(this);

        builder.setView(view);

        return builder.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonClose:
                Toast.makeText(requireContext(), "close", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonStay:
                Toast.makeText(requireContext(), "stay", Toast.LENGTH_SHORT).show();
                break;
        }

        if (mDialogOnClickListener != null)
            mDialogOnClickListener.onClick(v.getId());
    }


    public interface IDialogButtonOnClickListener {
        void onClick(int buttonId);
    }
}
