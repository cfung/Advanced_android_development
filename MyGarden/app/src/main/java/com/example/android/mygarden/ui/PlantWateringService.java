package com.example.android.mygarden.ui;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by cfung on 5/31/18.
 */

public class PlantWateringService extends IntentService {



    public PlantWateringService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
