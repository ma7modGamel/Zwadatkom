package com.safwa.zawadatkm_user.profile.ui.profileinfo;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.safwa.zawadatkm_user.Models.ProfileUserModel;
import com.safwa.zawadatkm_user.R;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;
import com.safwa.zawadatkm_user.Utils.Utils;
import com.safwa.zawadatkm_user.databinding.ProfileInfoFragmentBinding;
import com.safwa.zawadatkm_user.profile.ProfileActivity;


public class ProfileInfoFragment extends Fragment {

    private ProfileInfoViewModel mViewModel;
    public static final int TAKE_PIC_REQUEST_CODE = 0;
    public static final int GALLERY_KITKAT_INTENT_CALLED = 1;
    public static final int MEDIA_TYPE_IMAGE = 2;
    public static final int GALLERY_INTENT_CALLED = 2;
    public static Uri mMediaUri;
    public static ProfileInfoFragment newInstance() {
        return new ProfileInfoFragment();
    }

    ProfileInfoFragmentBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding=DataBindingUtil.inflate(inflater,R.layout.profile_info_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(ProfileInfoViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setProfileInfiViewModel(mViewModel);
        return binding.getRoot();
    }

    GlobalPrefrencies globalPrefrencies;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        globalPrefrencies =new GlobalPrefrencies(getContext());
        Utils.setLocale(getContext(),"ar");
        ProfileUserModel info = (ProfileUserModel)getActivity().getIntent().getSerializableExtra("info");
        Log.e("vvv",info.getUser().getName()+".");
        // TODO: Use the ViewModel

        binding.emailEt.setText(info.getUser().getEmail()+"");
        binding.nameEt.setText(info.getUser().getName()+"");
        binding.phoneEt.setText(info.getUser().getMobile()+"");
        Glide.with(getContext()).load(info.getUser().getAvatar()+"")
                .placeholder(R.drawable.logocolor).into(binding.imgInfo);


        binding.changeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_INTENT_CALLED);
                } else {
                    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("image/*");
                    startActivityForResult(intent, GALLERY_KITKAT_INTENT_CALLED);
                }
            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Cursor cursor = null;
        if (resultCode != Activity.RESULT_OK)
            return;
        if (data == null)
            return;
        Uri originalUri = null;
        if (requestCode == GALLERY_INTENT_CALLED && data != null) {
            originalUri = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            cursor = getActivity().getContentResolver().query(originalUri,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            filePath = cursor.getString(columnIndex);
            cursor.close();
            Log.e("path of uri", filePath);

        } else if (requestCode == GALLERY_KITKAT_INTENT_CALLED && data != null) {
            originalUri = data.getData();

            final int takeFlags = data.getFlags()
                    & (Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            // Check for the freshest data.
            getActivity().getContentResolver().takePersistableUriPermission(
                    originalUri, takeFlags);
            Log.e("Uri: ", originalUri.toString());

            filePath = getPath(getContext(), originalUri);
            Glide.with(this)
                    .load( data.getData())
                    .into(binding.imgInfo);

        }

    }

    @SuppressLint("NewApi")
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/"
                            + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection,
                        selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri,
                                       String selection, String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection,
                    selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
    String filePath = "";
}