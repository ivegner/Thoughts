package com.codeday.thoughts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Context context;

    private int counter = 10;

    ArrayList<Thought> myDataset = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        context = this;

        mRecyclerView = (RecyclerView) findViewById(R.id.peruse_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int index = viewHolder.getLayoutPosition();
                myDataset.remove(index);
                mAdapter.notifyItemRemoved(index);

                ParseQuery<ParseObject> query = ParseQuery.getQuery("Content");
                query.whereExists("Text");
                ParseObject parseObject = null;
                try {
                    List<ParseObject> parseObjectList = query.find();
                    if (counter < parseObjectList.size()) {
                        parseObject = parseObjectList.get(counter);
                        counter++;

                        String[] tempString = parseObject.getCreatedAt().toString().split(" ");
                        String dateString = "";
                        for (int j = 1; j < tempString.length - 1; j++) {
                            dateString = dateString + " " + (tempString[j]);
                        }
                        ParseFile parseFile = parseObject.getParseFile("Picture");
                        if (parseFile != null) {
                            File file = null;
                            try {
                                file = parseFile.getFile();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                            Bitmap bitmap = BitmapFactory.decodeFile(file.getPath(), options);

                            myDataset.add(new Thought(dateString.trim()
                                    + "\n" + "\n"
                                    + parseObject.getString("Text"), bitmap));
                            mAdapter.notifyDataSetChanged();
                        } else {
                            myDataset.add(new Thought(dateString.trim()
                                    + "\n" + "\n"
                                    + parseObject.getString("Text"), null));
                        }
                    }
                } catch (ParseException e) {
                }
                ;
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        parseQuery();
    }

    @Override
    public void onBackPressed() {
        //Nothing!
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_post; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.goToAccount:
                startAccountActivity();
                return true;
        }
        return false;
    }

    public void startAccountActivity() {
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
    }

    public void startPostActivity(View v) {
        Intent intent = new Intent(this, PostActivity.class);
        startActivity(intent);
    }

    private void parseQuery() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Content");
        query.whereExists("Text");
        try {
            List<ParseObject> parseObjects = query.find();
            for (int i = 0; i < 10; i++) {
                String[] tempString = parseObjects.get(i).getCreatedAt().toString().split(" ");
                String dateString = "";
                for (int j = 1; j < tempString.length - 1; j++) {
                    dateString = dateString + " " + (tempString[j]);
                }
                ParseFile parseFile = parseObjects.get(i).getParseFile("Picture");
                if (parseFile != null) {
                    File file = parseFile.getFile();
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getPath(), options);

                    myDataset.add(new Thought(dateString.trim()
                            + "\n" + "\n"
                            + parseObjects.get(i).getString("Text"), bitmap));
                    mAdapter.notifyDataSetChanged();
                } else {
                    myDataset.add(new Thought(dateString.trim()
                            + "\n" + "\n"
                            + parseObjects.get(i).getString("Text"), null));
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
