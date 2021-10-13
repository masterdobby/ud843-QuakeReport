/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        /*ArrayList<Earthquake> earthquakes = new ArrayList<>();
        earthquakes.add(new Earthquake("San Francisco", 7.2, 1));
        earthquakes.add(new Earthquake("London", 7.2, 1));
        earthquakes.add(new Earthquake("Tokyo", 7.2, 1));
        earthquakes.add(new Earthquake("Mexico City", 7.2, 1));
        earthquakes.add(new Earthquake("Moscow", 7.2, 1));
        earthquakes.add(new Earthquake("Rio de Janeiro", 7.2, 1));
        earthquakes.add(new Earthquake("Paris", 7.2, 1));*/

        final ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        earthquakeListView.setOnItemClickListener((parent, view, position, id) -> {
            Earthquake earthquake = adapter.getItem(position);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(earthquake.getUrl()));
            startActivity(intent);
        });
    }
}
