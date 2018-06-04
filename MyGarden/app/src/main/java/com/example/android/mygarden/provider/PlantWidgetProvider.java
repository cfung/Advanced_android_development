package com.example.android.mygarden.provider;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.RemoteViews;

/**
 * Created by cfung on 5/31/18.
 */

import com.example.android.mygarden.R;
import com.example.android.mygarden.ui.MainActivity;
import com.example.android.mygarden.ui.PlantWateringService;

public class PlantWidgetProvider extends AppWidgetProvider {

    //set updateAppWidget to handle clicks and launch MainActivity
    // completed (1): Modify updateAppWidget method to take an image recourse and call
    // setImageViewResource to update the widget’s image
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId,
                                int imageRes){

        // TODO (3): Set the click handler to open the DetailActivity for plant ID,
        // or the MainActivity if plant ID is invalid
        // Create an Intent to launch MainActivity when clicked

        // Create an Intent to launch MainActivity when clicked
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        //Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.plant_widget);

        // Widgets allow click handlers to only launch pending intents
        views.setOnClickPendingIntent(R.id.widget_plant_image, pendingIntent);

        // completed (4): Create a PendingIntent for the PlantWateringService and setOnClickPendingIntent for widget_water_button
        Intent wateringIntent = new Intent(context, PlantWateringService.class);
        wateringIntent.setAction(PlantWateringService.ACTION_WATER_PLANTS);
        PendingIntent pendingIntentWaterService = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.widget_water_btn, pendingIntentWaterService);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
        views.setImageViewResource(appWidgetId, imageRes);

    }

    // completed (2): Move the updateAppWidget loop to a new method called updatePlantWidgets and pass through the image recourse
    // There may be multiple widgets active, so update all of them
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
    {
        //updatePlantWidgets(context, appWidgetManager, appWidgetIds, imageRes);
        PlantWateringService.startActionWaterPlants(context);
    }

    private static void updatePlantWidgets(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds, int imageRes){

        for (int appWidgetId : appWidgetIds){
            updateAppWidget(context, appWidgetManager, appWidgetId, imageRes);
        }

    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds){

    }

    @Override
    public void onEnabled(Context context){

    }

    @Override
    public void onDisabled(Context context){

    }
}
