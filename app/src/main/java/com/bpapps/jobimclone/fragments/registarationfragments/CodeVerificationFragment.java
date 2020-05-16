package com.bpapps.jobimclone.fragments.registarationfragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bpapps.jobimclone.MainActivity;
import com.bpapps.jobimclone.R;
import com.bpapps.jobimclone.asyncktasks.CodeVerificationAsyncTask;
import com.bpapps.jobimclone.asyncktasks.RequestCodeAsyncTask;
import com.bpapps.jobimclone.dialogs.AlertCodeVerificationWhiter;
import com.bpapps.jobimclone.dialogs.AppAlertDialog;
import com.bpapps.jobimclone.fragments.SplashScreenFragment;
import com.bpapps.jobimclone.fragments.myjobsfragments.FindJobsInList;

public class CodeVerificationFragment extends Fragment
        implements View.OnClickListener,
        RequestCodeAsyncTask.IOnCodeRequestedListener,
        CodeVerificationAsyncTask.IOnCodeVerifiedResult {

    public static final String STACK_TAG = "STACK_TAG." + CodeVerificationFragment.class.getName();
    private static final String TAG = "TAG." + CodeVerificationFragment.class.getName();

    private static final String ARGS_PHONE_NUMBER = MainActivity.PACKAGE_NAME + ".ARGS_PHONE_NUMBER";
    private static final String ARGS_CODE_VERIFICATION_FRAGMENT_COUNTRY_CODE_NUMBER = MainActivity.PACKAGE_NAME + ".ARGS_CODE_VERIFICATION_FRAGMENT_COUNTRY_CODE_NUMBER";

    private final String FRAGMENT_DIALOG_CODE_VERIFICATION_WHITER = MainActivity.PACKAGE_NAME + ".FRAGMENT_DIALOG_CODE_VERIFICATION_WHITER";
    private final String FRAGMENT_DIALOG_CODE_VERIFICATION_ERROR = MainActivity.PACKAGE_NAME + ".FRAGMENT_DIALOG_CODE_VERIFICATION_ERROR";
    private final String FRAGMENT_DIALOG_CODE_SEND = MainActivity.PACKAGE_NAME + ".FRAGMENT_DIALOG_CODE_SEND";

    private TextView mTextViewPhoneNumberShower;
    private TextView mTextViewFirstNumber;
    private TextView mTextViewSecondNumber;
    private TextView mTextViewThirdNumber;
    private TextView mTextViewFourthNumber;
    private TextView mTextViewFirstUnderLine;
    private TextView mTextViewSecondUnderLine;
    private TextView mTextViewThirdUnderLine;
    private TextView mTextViewFourthUnderLine;

    private StringBuilder mInput = new StringBuilder();
    private short mNumberOfTimesCodeSend;

    public static CodeVerificationFragment getInstance(@NonNull String phoneNumber, @NonNull String countryCode) {
        Bundle args = new Bundle();
        args.putString(ARGS_PHONE_NUMBER, phoneNumber);
        args.putString(ARGS_CODE_VERIFICATION_FRAGMENT_COUNTRY_CODE_NUMBER, countryCode);

        CodeVerificationFragment fragment = new CodeVerificationFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public CodeVerificationFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_code_verifacation, container, false);

        view.findViewById(R.id.text_view_change_number).setOnClickListener(this);
        view.findViewById(R.id.text_view_code_verification_next).setOnClickListener(this);
        view.findViewById(R.id.text_view_code_not_received).setOnClickListener(this);

        mTextViewPhoneNumberShower = view.findViewById(R.id.text_view_phone_number_shower);
        mTextViewPhoneNumberShower.setText(getPhoneNumberText());

        mTextViewFirstNumber = view.findViewById(R.id.text_view_first_number);
        mTextViewSecondNumber = view.findViewById(R.id.text_view_second_number);
        mTextViewThirdNumber = view.findViewById(R.id.text_view_third_number);
        mTextViewFourthNumber = view.findViewById(R.id.text_view_fourth_number);

        mTextViewFirstUnderLine = view.findViewById(R.id.text_view_underline_1);
        mTextViewSecondUnderLine = view.findViewById(R.id.text_view_underline_2);
        mTextViewThirdUnderLine = view.findViewById(R.id.text_view_underline_3);
        mTextViewFourthUnderLine = view.findViewById(R.id.text_view_underline_4);

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

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        sendCode();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_view_change_number:
                changeNumber();
                break;
            case R.id.text_view_code_verification_next:
                next();
                break;
            case R.id.text_view_code_not_received:
                sendCode();
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
        }
    }

    private void updateInput(String inputToAppend) {
        Log.d(TAG, "updateInput");

        if (inputToAppend != null) {
            mInput.append(inputToAppend);

            if (mInput.length() == 5) {
                mInput.deleteCharAt(3);
            }

            updateInputViews(mInput.length(), inputToAppend, getResources().getColor(R.color.digits_underline_digit_pressed));
        }

        if (mInput.length() == 4) {
            checkCode();
        }
    }

    private void checkCode() {
        mNumberOfTimesCodeSend++;

        Bundle args = getArguments();
        String phoneNumber = args.getString(ARGS_PHONE_NUMBER, null);
        String countryCode = args.getString(ARGS_CODE_VERIFICATION_FRAGMENT_COUNTRY_CODE_NUMBER, null);
        String code = mInput.toString();

        Log.d(TAG, "checkCode : phoneNumber = " + phoneNumber + ", countryCode = " + countryCode + ", code = " + code);

        showCodeBeingVerifiedDialog();

        new CodeVerificationAsyncTask(this).execute(phoneNumber, countryCode, code);
    }

    private void setInputToEmpty() {
        mInput = new StringBuilder();

        updateInputViews(1, "", getResources().getColor(R.color.digits_underline_digit_not_pressed));
        updateInputViews(2, "", getResources().getColor(R.color.digits_underline_digit_not_pressed));
        updateInputViews(3, "", getResources().getColor(R.color.digits_underline_digit_not_pressed));
        updateInputViews(4, "", getResources().getColor(R.color.digits_underline_digit_not_pressed));
    }

    private void updateInputViews(int digitNumber, String digit, int underLineColor) {
        switch (digitNumber) {
            case 1:
                mTextViewFirstNumber.setText(digit);
                mTextViewFirstUnderLine.setBackgroundColor(underLineColor);
                break;
            case 2:
                mTextViewSecondNumber.setText(digit);
                mTextViewSecondUnderLine.setBackgroundColor(underLineColor);
                break;
            case 3:
                mTextViewThirdNumber.setText(digit);
                mTextViewThirdUnderLine.setBackgroundColor(underLineColor);
                break;
            case 4:
                mTextViewFourthNumber.setText(digit);
                mTextViewFourthUnderLine.setBackgroundColor(underLineColor);
                break;
        }
    }

    private void deleteChar() {
        Log.d(TAG, "delete char");
        if (mInput.length() > 0) {
            mInput.deleteCharAt(mInput.length() - 1);

            updateInputViews(mInput.length() + 1, "", getResources().getColor(R.color.digits_underline_digit_not_pressed));
        }
    }

    private void sendCode() {
        Log.d(TAG, "sendCode");
        mNumberOfTimesCodeSend++;

        String phoneNumber = getArguments().getString(ARGS_PHONE_NUMBER);
        String code = getArguments().getString(ARGS_CODE_VERIFICATION_FRAGMENT_COUNTRY_CODE_NUMBER);

        new RequestCodeAsyncTask(this).execute(phoneNumber, code);
    }

    private void next() {
        Log.d(TAG, "next");
        showErrorDialog();
    }

    private void changeNumber() {
        Log.d(TAG, "change number");
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    private String getPhoneNumberText() {
        Bundle args = getArguments();
        String code, number;
        code = args.getString(ARGS_CODE_VERIFICATION_FRAGMENT_COUNTRY_CODE_NUMBER);
        number = args.getString(ARGS_PHONE_NUMBER);

        StringBuilder retVal = new StringBuilder("(");
        retVal.append(code);
        retVal.append(") ");
        retVal.append(number);
        retVal.insert(10, '-');
        retVal.insert(14, '-');

        return retVal.toString();
    }

    private void showCodeSendDialog() {
        showDialog(
                getString(R.string.dialog_code_send_title),
                getString(R.string.dialog_code_send_message),
                getString(R.string.dialog_code_send_button_text),
                FRAGMENT_DIALOG_CODE_SEND);
    }

    private void showErrorDialog() {
        Log.d(TAG, "showErrorDialog");

        showDialog(
                getString(R.string.dialog_error_title),
                getString(R.string.dialog_errer_massage),
                getString(R.string.dialog_error_button_text),
                FRAGMENT_DIALOG_CODE_VERIFICATION_ERROR);
    }

    private void showDialog(String title, String message, String buttonText, @NonNull final String tag) {
        AppAlertDialog dialog = AppAlertDialog.newInstance(
                title,
                message,
                buttonText,
                new AppAlertDialog.IDialogButtonOnClickListener() {
                    @Override
                    public void onClick() {
                        AppAlertDialog dialog = (AppAlertDialog) getParentFragmentManager().findFragmentByTag(tag);
                        if (dialog != null)
                            dialog.dismiss();
                    }
                });
        dialog.setCancelable(false);

        dialog.show(requireActivity().getSupportFragmentManager(), tag);
    }

    private void showCodeBeingVerifiedDialog() {
        AlertCodeVerificationWhiter dialog = AlertCodeVerificationWhiter.getInstance();
        dialog.setCancelable(false);

        dialog.show(requireActivity().getSupportFragmentManager(), FRAGMENT_DIALOG_CODE_VERIFICATION_WHITER);
    }

    @Override
    public void onCodeRequested() {
        Log.d(TAG, "onCodeRequested : mNumberOfTimesCodeSend = " + mNumberOfTimesCodeSend);
        if (mNumberOfTimesCodeSend > 1) {
            showCodeSendDialog();
        }
    }

    @Override
    public void onCodeVerified(boolean isVerified) {
        Log.d(TAG, "onCodeVerified : isVerified = " + isVerified);

        AlertCodeVerificationWhiter dialog = (AlertCodeVerificationWhiter) getParentFragmentManager().findFragmentByTag(FRAGMENT_DIALOG_CODE_VERIFICATION_WHITER);
        if (dialog != null)
            dialog.dismiss();

        if (isVerified) {
            SharedPreferences pref = requireActivity().getSharedPreferences(MainActivity.PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean(MainActivity.PREFERENCE_IS_REGISTERED_KEY, isVerified);
            editor.apply();

            FragmentManager fm = requireActivity().getSupportFragmentManager();
//            Log.d(TAG, "Total fragments : " +fm.getBackStackEntryCount());
//            for(int entry = 0; entry < fm.getBackStackEntryCount(); entry++){
//                Log.d(TAG, "Found fragment: " + fm.getBackStackEntryAt(entry).getName());
//            }
            fm.popBackStackImmediate(SplashScreenFragment.STACK_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fm.beginTransaction()
                    .replace(R.id.app_main_fragment_nav_container, FindJobsInList.getInstance(), FindJobsInList.FRAGMENT_TAG)
                    .addToBackStack(FindJobsInList.STACK_TAG)
                    .commit();
        } else {
            showErrorDialog();
        }

    }
}
