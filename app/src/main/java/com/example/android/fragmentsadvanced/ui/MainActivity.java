package com.example.android.fragmentsadvanced.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.fragmentsadvanced.R;
import com.example.android.fragmentsadvanced.data.AndroidImageAssets;

//THis activity is responsible for displaying the master list of all images
//implement masterListFragment OnImageClickListener
public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    //variables to store the values for the list index of the selected images
    //The default value will be index = 0
    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    //boolean on whether to display one or two-pane
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.android_me_linear_layout) != null) {
            mTwoPane = true;

            //Get rid of the next button
            Button nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);

            //Change the Grid view to space out the images more on the tablet
            GridView gridView = (GridView) findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);

            //create new body parts fragment
            if (savedInstanceState == null) {
                //use a fragmentManager and transaction to add fragment to the screen
                FragmentManager fragmentManager = getSupportFragmentManager();

                //head=====================
                BodyPartFragment headFragment = new BodyPartFragment();

                //set the list for ids and set the position to the second in the list
                headFragment.setImageIds(AndroidImageAssets.getHeads());
                //headFragment.setListIndex(2);

                //Fragment transaction
                fragmentManager.beginTransaction()
                        .add(R.id.head_container, headFragment)
                        .commit();

                //body====================

                BodyPartFragment bodyFragment = new BodyPartFragment();
                bodyFragment.setImageIds(AndroidImageAssets.getBodies());
                //bodyFragment.setListIndex(4);
                fragmentManager.beginTransaction()
                        .add(R.id.body_container, bodyFragment)
                        .commit();

                //Lgs====================

                BodyPartFragment legsFragment = new BodyPartFragment();
                legsFragment.setImageIds(AndroidImageAssets.getLegs());
                //legsFragment.setListIndex(6);
                fragmentManager.beginTransaction()
                        .add(R.id.legs_container, legsFragment)
                        .commit();
            }

        } else {
            mTwoPane = false;
        }

    }

    @Override
    public void OnImageSelected(int position) {
        Toast.makeText(this, "Position " + position, Toast.LENGTH_SHORT).show();

        //simple math
        int bodyPartNumber = position / 12;
        int listIndex = position - 12 * bodyPartNumber;

        if (mTwoPane) {

            //set the currently displayed item for the correct body part fragment
            switch (bodyPartNumber) {
                case 0:
                    BodyPartFragment headFragment = new BodyPartFragment();
                    headFragment.setImageIds(AndroidImageAssets.getHeads());
                    headFragment.setListIndex(listIndex);

                    //replace the old head fragment with a new one
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container, headFragment)
                            .commit();
                    break;
                case 1:
                    BodyPartFragment bodyFragment = new BodyPartFragment();
                    bodyFragment.setImageIds(AndroidImageAssets.getBodies());
                    bodyFragment.setListIndex(listIndex);

                    //replace the old head fragment with a new one
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container, bodyFragment)
                            .commit();
                    break;
                case 2:
                    BodyPartFragment legsFragment = new BodyPartFragment();
                    legsFragment.setImageIds(AndroidImageAssets.getLegs());
                    legsFragment.setListIndex(listIndex);

                    //replace the old head fragment with a new one
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.legs_container, legsFragment)
                            .commit();
                    break;
            }

        } else {

            //set the currently displayed item for the correct body part fragment
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

            //put this infoprmation in abundle and attach it to an intent that will launch an androidMeActivity
            Bundle b = new Bundle();
            b.putInt("headIndex", headIndex);
            b.putInt("bodyIndex", bodyIndex);
            b.putInt("legIndex", legIndex);

            //Attach the bundle to an intent
            final Intent intent = new Intent(MainActivity.this, AndroidMeActivity.class);
            intent.putExtras(b);

            Button button = (Button) findViewById(R.id.next_button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(intent);
                }
            });
        }
    }
}
