package com.bpapps.jobimclone.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bpapps.jobimclone.R;

public class AppAlertDialog extends DialogFragment {

    private String mTitle;
    private String mMessage;
    private String mButtonText;

    private IDialogOnClickListener mDialogOnClickListener;

    public static AppAlertDialog newInstance(String title, String message, String buttonText, IDialogOnClickListener dialogOnClickListener) {
        return new AppAlertDialog(title, message, buttonText, dialogOnClickListener);
    }

    public AppAlertDialog(String title, String message, String buttonText, IDialogOnClickListener IDialogOnClickListener) {
        super();

        mTitle = title;
        mMessage = message;
        mButtonText = buttonText;
        mDialogOnClickListener = IDialogOnClickListener;
    }

    public AppAlertDialog(String title, String message, String buttonText) {
        this(title, message, buttonText, null);
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
        View view = inflater.inflate(R.layout.app_dialog_custom_layout, null);

        Button button = view.findViewById(R.id.button_dialog);
        button.setText(mButtonText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          //      AppAlertDialog.this.dismiss();
                if (mDialogOnClickListener != null)
                    mDialogOnClickListener.onClick();
            }
        });

        TextView title = view.findViewById(R.id.text_view_dialog_title);
        title.setText(mTitle);

        TextView message = view.findViewById(R.id.text_view_dialog_message);
        message.setText(mMessage);

        builder.setView(view);

        return builder.create();
    }

    public void setDialogOnClickListener(IDialogOnClickListener permissionRationalDialogOnClickListener) {
        mDialogOnClickListener = permissionRationalDialogOnClickListener;
    }

    public interface IDialogOnClickListener {
        void onClick();
    }
}
