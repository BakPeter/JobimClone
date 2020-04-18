package com.bpapps.jobimclone.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bpapps.jobimclone.R;


public class AlertCodeVerificationWhiter extends DialogFragment {

    public static AlertCodeVerificationWhiter getInstance() {
        return new AlertCodeVerificationWhiter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_code_verification_whiter, null);

        ImageView ivWorld = view.findViewById(R.id.iv_world);
        ImageView ivApp = view.findViewById(R.id.iv_app);

        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(1000);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setRepeatCount(Animation.INFINITE);
        ivWorld.startAnimation(rotate);

        AnimationSet as = new AnimationSet(true);
        TranslateAnimation down = new TranslateAnimation(
                0.0f, 0.0f,
                0.0f, 150.0f);
        down.setDuration(200);
        down.setRepeatCount(Animation.INFINITE);
        as.addAnimation(down);

        TranslateAnimation up = new TranslateAnimation(
                0.0f, 0.0f,
                150.0f, 0.0f);
        up.setDuration(200);
        up.setStartOffset(200);
        as.addAnimation(up);
        ivApp.startAnimation(as);

        builder.setView(view);

        Dialog dialog = builder.create();

        return dialog;
    }
}
