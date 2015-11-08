package com.codeday.thoughts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseFile;
import com.parse.ParseObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PostActivity extends AppCompatActivity {

    //Request Codes starting activities for results
    private static final int REQUEST_TAKE_PICTURE = 1;

    private int inputLength;
    private TextView inputView;
    private TextView charRemainView;
    private String mCurrentPhotoPath;
    private ParseFile imgFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_post);

        // Make sure the scrollbar isn't visible when scrolling
        ScrollView sv = (ScrollView)findViewById(R.id.scrollView);

        sv.setVerticalScrollBarEnabled(false);
        sv.setHorizontalScrollBarEnabled(false);

        inputView = (TextView) findViewById(R.id.inputText);

        inputLength = 600;
        charRemainView = (TextView) findViewById(R.id.charRemainView);
        inputLength = 600 - inputView.getText().toString().length();
        charRemainView.setText(inputLength + " characters remaining.");
    }

    public void takePictureWithIntent(View v){
        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(pictureIntent.resolveActivity(getPackageManager())!=null){
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(pictureIntent, REQUEST_TAKE_PICTURE);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_post; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.sendView:
                post();
                return true;
        }

        Intent myIntent = new Intent(getApplicationContext(), ReadActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    public void post() {
        if(isValidInput(inputView.getText().toString())) {
            ParseObject parseObject = new ParseObject("Content");
            parseObject.put("Text", inputView.getText().toString());
            if(imgFile!=null) {
                parseObject.put("Picture", imgFile);
                Toast.makeText(this, "Picture added"+imgFile.getName(), Toast.LENGTH_SHORT).show();
            }
            parseObject.saveInBackground();

            Intent myIntent = new Intent(getApplicationContext(), ReadActivity.class);
            //Toast.makeText(getApplicationContext(), "Your thought has been posted.", Toast.LENGTH_LONG).show();
            startActivity(myIntent);
        }
    }

    private boolean isValidInput(String str) {
        if (str.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter a thought!",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (str.length() > 600) {
            Toast.makeText(getApplicationContext(), "Please limit your thought to 600 characters.",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (str.length() < 10) {
            Toast.makeText(getApplicationContext(), "Please enter more than 10 characters.",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else { return true; }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(this,requestCode+" "+resultCode,Toast.LENGTH_SHORT).show();

        if (requestCode == REQUEST_TAKE_PICTURE && resultCode == RESULT_OK) {
            Toast.makeText(this,"making ParseFile",Toast.LENGTH_SHORT).show();
            /*
            Get Thumbnail:
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
            */
            File file = new File(mCurrentPhotoPath);
            imgFile = new ParseFile(file);
            Toast.makeText(this, "made ParseFile", Toast.LENGTH_SHORT).show();
            imgFile.saveInBackground();
        }
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        Log.d("PostActiviy", image.getPath());

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }
}
