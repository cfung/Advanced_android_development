package com.example.android.mygarden.ui;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.example.android.mygarden.provider.PlantContract;
import com.example.android.mygarden.utils.PlantUtils;

import static com.example.android.mygarden.provider.PlantContract.BASE_CONTENT_URI;
import static com.example.android.mygarden.provider.PlantContract.PATH_PLANTS;

/**
 * Created by cfung on 5/31/18.
 */

//completed (2): Create a plant watering service that extends IntentService and supports the
// action ACTION_WATER_PLANTS which updates last_watered timestamp for all plants still alive -->

public class PlantWateringService extends IntentService {

    // completed (1): Change ACTION_WATER_PLANTS to ACTION_WATER_PLANT and
    // use EXTRA_PLANT_ID to pass the plant ID to the service and update the query to use SINGLE_PLANT_URI
    public static final String ACTION_WATER_PLANT = "com.example.android.mygarden.action.water_plant";
    public static final String ACTION_UPDATE_PLANT_WIDGET = "com.example.android.mygarden.action.update_plant_widgets";
    public static final String EXTRA_PLANT_ID = "com.example.android.mygarden.extra.PLANT_ID";

    public PlantWateringService(String name) {
        super(name);
    }

    public static void startActionWaterPlants(Context context){

        Intent intent = new Intent(context, PlantWateringService.class);
        intent.setAction(ACTION_WATER_PLANT);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent != null){

            final String action = intent.getAction();
            if (ACTION_WATER_PLANT.equals(action)){
                handleActionWaterPlants();
            }
        }
    }

    private void handleActionWaterPlants(){

        Uri PLANTS_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_PLANTS).build();
        ContentValues contentValues = new ContentValues();
        long timeNow = System.currentTimeMillis();
        contentValues.put(PlantContract.PlantEntry.COLUMN_LAST_WATERED_TIME, timeNow);
        getContentResolver().update(
                PLANTS_URI,
                contentValues,
                PlantContract.PlantEntry.COLUMN_LAST_WATERED_TIME + ">?",
                new String[]{String.valueOf(timeNow - PlantUtils.MAX_AGE_WITHOUT_WATER)});

    }

}
