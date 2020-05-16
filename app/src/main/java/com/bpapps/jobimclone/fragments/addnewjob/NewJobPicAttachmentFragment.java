package com.bpapps.jobimclone.fragments.addnewjob;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import com.bpapps.jobimclone.R;
import com.bpapps.jobimclone.dataclasees.Job;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewJobPicAttachmentFragment extends Fragment
        implements View.OnClickListener {
    private static final String TAG = "TAG.NewJobPicAttachmentFrag";
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_GALLERY = 2;

    private Job mJob;

    private AppCompatButton mButtonTakePicture;
    private AppCompatButton mButtonGetFromGallery;
    private AppCompatImageView mIVJobPic;

    public NewJobPicAttachmentFragment() {
        // Required empty public constructor
    }

    public static NewJobPicAttachmentFragment getInstance(Job job) {
        NewJobPicAttachmentFragment instance = new NewJobPicAttachmentFragment();
        instance.mJob = job;

        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_job_pic_attachment, container, false);

        mButtonTakePicture = view.findViewById(R.id.buttonTakePicture);
        mButtonTakePicture.setOnClickListener(this);

        mButtonGetFromGallery = view.findViewById(R.id.buttonGetFromGallery);
        mButtonGetFromGallery.setOnClickListener(this);

        mIVJobPic = view.findViewById(R.id.ivJobPic);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonTakePicture:
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
                    NewJobPicAttachmentFragment.this.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
                break;
            case R.id.buttonGetFromGallery:
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, REQUEST_GALLERY);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mIVJobPic.setImageBitmap(imageBitmap);
        }

        if (requestCode == REQUEST_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                mIVJobPic.setImageBitmap(bitmap);
            } catch (IOException e) {
                Log.i("TAG", "Some exception " + e);
            }
        }
    }

}
