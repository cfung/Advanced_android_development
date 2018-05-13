package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import android.content.Intent;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * Created by cfung on 5/2/18.
 */

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    // default should be -
    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    //Create a variable to track whether to display a two-pane or single-pane UI
    // A single-pane display refers to phone screens, and two-pane to larger tablet screens
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.android_me_linear_layout) != null){
            Log.d("MainActivity", "android_me_linear_layout is NOT null..");
            mTwoPane = true;

            Button nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);

            GridView gridView = (GridView) findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);

            // completed (4) If you are making a two-pane display, add new BodyPartFragments to create an initial Android-Me image
            // Also, for the two-pane display, get rid of the "Next" button in the master list fragment
            if (savedInstanceState == null){

                // Create a new head BodyPartFragment
                BodyPartFragment headFragment = new BodyPartFragment();

                // Set the list of image id's for the head fragment and set the position to the second image in the list
                headFragment.setImageIds(AndroidImageAssets.getHeads());

                // Get the correct index to access in the array of head images from the intent
                // Set the default value to 0
                //int headIndex = getIntent().getIntExtra("headIndex", 0);
                //headFragment.setListIndex(headIndex);

                // Add the fragment to its container using a FragmentManager and a Transaction
                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction()
                        .add(R.id.head_container, headFragment)
                        .commit();

                // Create and display the body and leg BodyPartFragments

                BodyPartFragment bodyFragment = new BodyPartFragment();
                bodyFragment.setImageIds(AndroidImageAssets.getBodies());
                //int bodyIndex = getIntent().getIntExtra("bodyIndex", 0);
                //bodyFragment.setListIndex(bodyIndex);

                fragmentManager.beginTransaction()
                        .add(R.id.body_container, bodyFragment)
                        .commit();

                BodyPartFragment legFragment = new BodyPartFragment();
                legFragment.setImageIds(AndroidImageAssets.getLegs());
                //int legIndex = getIntent().getIntExtra("legIndex", 0);
                //legFragment.setListIndex(legIndex);

                fragmentManager.beginTransaction()
                        .add(R.id.leg_container, legFragment)
                        .commit();

            }

        } else {
            mTwoPane = false;

        }
    }

    @Override
    public void onImageSelected(int position) {
        Toast.makeText(this, "Position clicked = " + position, Toast.LENGTH_SHORT).show();

        int bodyPartNumber = position / 12;
        int listIndex = position - 12 * bodyPartNumber;

        // Tablet mode
        if (mTwoPane){

            BodyPartFragment newFragment = new BodyPartFragment();

            switch(bodyPartNumber){

                // head image
                case 0:
                    newFragment.setImageIds(AndroidImageAssets.getHeads());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container, newFragment)
                            .commit();
                    break;

                // body image
                case 1:
                    newFragment.setImageIds(AndroidImageAssets.getHeads());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container, newFragment)
                            .commit();
                    break;

                // leg image
                case 2:
                    newFragment.setImageIds(AndroidImageAssets.getHeads());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.leg_container, newFragment)
                            .commit();
                    break;


            }


        } else {

            switch (bodyPartNumber) {

                case 0:
                    headIndex = listIndex;
                    break;

                case 1:
                    bodyIndex = listIndex;
                    break;

                case 2:
                    legIndex = listIndex;
                    break;

                default:
                    break;
            }

            Bundle b = new Bundle();
            b.putInt("headIndex", headIndex);
            b.putInt("bodyIndex", bodyIndex);
            b.putInt("legIndex", legIndex);

            final Intent intent = new Intent(this, AndroidMeActivity.class);
            intent.putExtras(b);

            Button nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    startActivity(intent);
                }
            });

        }



    }
}
