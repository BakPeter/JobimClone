package com.bpapps.jobimclone.fragments.registarationfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bpapps.jobimclone.R;
import com.bpapps.jobimclone.dialogs.AppAlertDialog;
import com.bpapps.jobimclone.fragments.registarationfragments.termsofuse.TermsOfUseShowerFragment;

public class RegisterPhoneNumberFragment extends Fragment
        implements View.OnClickListener, CountryChooserFragment.ICountryCodeChosenListener {
    public static final String STACK_TAG = "STACK_TAG." + RegisterPhoneNumberFragment.class.getName();
    private static final String TAG = "TAG." + RegisterPhoneNumberFragment.class.getName();

    private TextView mTextViewCountryCode;
    private TextView mTextViewPhoneNumber;

    private StringBuilder mPhoneNumberInput = new StringBuilder();
    private String mCode = null;

    public static RegisterPhoneNumberFragment getInstance() {
        return new RegisterPhoneNumberFragment();
    }

    public RegisterPhoneNumberFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register_phone_number, container, false);

        initViews(view);

        return view;
    }

    private void initViews(View view) {
        mTextViewCountryCode = view.findViewById(R.id.text_view_item_country_code);
        mTextViewCountryCode.setOnClickListener(this);

        mTextViewPhoneNumber = view.findViewById(R.id.text_view_phone_number);


        view.findViewById(R.id.text_view_show_licence).setOnClickListener(this);
        view.findViewById(R.id.text_view_next).setOnClickListener(this);

        view.findViewById(R.id.button_delete_char).setOnClickListener(this);
        view.findViewById(R.id.button_digit_0).setOnClickListener(this);
        view.findViewById(R.id.button_digit_1).setOnClickListener(this);
        view.findViewById(R.id.button_digit_2).setOnClickListener(this);
        view.findViewById(R.id.button_digit_3).setOnClickListener(this);
        view.findViewById(R.id.button_digit_4).setOnClickListener(this);
        view.findViewById(R.id.button_digit_5).setOnClickListener(this);
        view.findViewById(R.id.button_digit_6).setOnClickListener(this);
        view.findViewById(R.id.button_digit_7).setOnClickListener(this);
        view.findViewById(R.id.button_digit_8).setOnClickListener(this);
        view.findViewById(R.id.button_digit_9).setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        activity.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        activity.getSupportActionBar().hide();

        if (mCode != null) {
            mTextViewCountryCode.setText(mCode);
            mCode = null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_view_item_country_code:
                changeCountryCode();
                break;
            case R.id.text_view_next:
                goToNextStep();
                break;
            case R.id.text_view_show_licence:
                showLicence();
                break;
            case R.id.button_delete_char:
                deleteChar();
                break;
            case R.id.button_digit_0:
                updateInput(getResources().getString(R.string.digit_0));
                break;
            case R.id.button_digit_1:
                updateInput(getResources().getString(R.string.digit_1));
                break;
            case R.id.button_digit_2:
                updateInput(getResources().getString(R.string.digit_2));
                break;
            case R.id.button_digit_3:
                updateInput(getResources().getString(R.string.digit_3));
                break;
            case R.id.button_digit_4:
                updateInput(getResources().getString(R.string.digit_4));
                break;
            case R.id.button_digit_5:
                updateInput(getResources().getString(R.string.digit_5));
                break;
            case R.id.button_digit_6:
                updateInput(getResources().getString(R.string.digit_6));
                break;
            case R.id.button_digit_7:
                updateInput(getResources().getString(R.string.digit_7));
                break;
            case R.id.button_digit_8:
                updateInput(getResources().getString(R.string.digit_8));
                break;
            case R.id.button_digit_9:
                updateInput(getResources().getString(R.string.digit_9));
                break;
        }
    }

    private void updateInput(String inputToAppend) {
        mPhoneNumberInput.append(inputToAppend);
        mTextViewPhoneNumber.setHint(mPhoneNumberInput.toString());

        if (mPhoneNumberInput.length() == 10) {
            CodeVerificationFragment fragment = CodeVerificationFragment.getInstance
                    (mTextViewPhoneNumber.getHint().toString(), mTextViewCountryCode.getText().toString());

            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.app_main_fragment_nav_container, fragment)
                    .addToBackStack(CodeVerificationFragment.STACK_TAG)
                    .commit();

            mPhoneNumberInput = new StringBuilder();
            mTextViewPhoneNumber.setHint(getResources().getString(R.string.phone_number_hint));
        }
    }

    private void goToNextStep() {
        final AppAlertDialog dialog = new AppAlertDialog(
                getString(R.string.dialog_register_title),
                getString(R.string.dialog_register_message),
                getString(R.string.dialog_register_button_text));
        dialog.setDialogOnClickListener(new AppAlertDialog.IDialogButtonOnClickListener() {
            @Override
            public void onClick() {
                dialog.dismiss();
            }
        });

        dialog.show(requireActivity().getSupportFragmentManager(), null);
    }

    private void showLicence() {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.app_main_fragment_nav_container, TermsOfUseShowerFragment.getInstance())
                .addToBackStack(TermsOfUseShowerFragment.STACK_TAG)
                .commit();
    }

    private void changeCountryCode() {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.app_main_fragment_nav_container, CountryChooserFragment.getInstance(this))
                .addToBackStack(CountryChooserFragment.STACK_TAG)
                .commit();
    }

    private void deleteChar() {
        int length = mPhoneNumberInput.length();

        if (length > 0) {
            mPhoneNumberInput.deleteCharAt(length - 1);
        }

        if (mPhoneNumberInput.length() == 0) {
            mTextViewPhoneNumber.setHint(getResources().getString(R.string.phone_number_hint));
        } else {
            mTextViewPhoneNumber.setHint(mPhoneNumberInput.toString());
        }
    }

    @Override
    public void codeChosen(String code) {
        mCode = code;
    }
}
