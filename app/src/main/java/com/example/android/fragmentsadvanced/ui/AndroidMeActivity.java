/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.fragmentsadvanced.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.fragmentsadvanced.R;
import com.example.android.fragmentsadvanced.data.AndroidImageAssets;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        int headIndex = getIntent().getExtras().getInt("headIndex");
        int bodyIndex = getIntent().getExtras().getInt("bodyIndex");
        int legsIndex = getIntent().getExtras().getInt("legIndex");

        if (savedInstanceState == null) {
            //use a fragmentManager and transaction to add fragment to the screen
            FragmentManager fragmentManager = getSupportFragmentManager();

            //head=====================
            BodyPartFragment headFragment = new BodyPartFragment();

            //set the list for ids and set the position to the second in the list
            headFragment.setImageIds(AndroidImageAssets.getHeads());
            headFragment.setListIndex(headIndex);

            //Fragment transaction
            fragmentManager.beginTransaction()
                    .replace(R.id.head_container, headFragment)
                    .commit();

            //body====================

            BodyPartFragment bodyFragment = new BodyPartFragment();
            bodyFragment.setImageIds(AndroidImageAssets.getBodies());
            bodyFragment.setListIndex(bodyIndex);
            fragmentManager.beginTransaction()
                    .add(R.id.body_container, bodyFragment)
                    .commit();

            //Lgs====================

            BodyPartFragment legsFragment = new BodyPartFragment();
            legsFragment.setImageIds(AndroidImageAssets.getLegs());
            legsFragment.setListIndex(legsIndex);
            fragmentManager.beginTransaction()
                    .add(R.id.legs_container, legsFragment)
                    .commit();
        }

    }
}
