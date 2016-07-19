package com.cinnamint.cotidiano;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Calendar;

public class DailyAppWidgetProvider extends AppWidgetProvider {

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        Log.d(MainActivity.TAG, "onUpdate called on " + Calendar.getInstance().getTime());

        final int N = appWidgetIds.length;

        // Loop through each App Widget that belongs to this provider (1)
        for(int i = 0; i < N; i++) {
            int appWidgetId = appWidgetIds[i];

            // Intent launches activity
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            // Get the layout for the App Widget and attach an on-click listener
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_daily_layout);
            views.setOnClickPendingIntent(R.id.widget_layout, pendingIntent);

            WordDatabase wdb = new WordDatabase(context);
            wdb.open();
            Words todayWord = wdb.getTodayWord(MainActivity.getDaysSinceCinnamintEpoch(Calendar.getInstance()) + 1);
            wdb.close();

            views.setTextViewText(R.id.spanish_word, todayWord.getText());
            views.setTextViewText(R.id.english_translation, todayWord.getDefinition());

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);

        }
    }



}
