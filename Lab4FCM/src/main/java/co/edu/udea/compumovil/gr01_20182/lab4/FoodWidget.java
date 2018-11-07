package co.edu.udea.compumovil.gr01_20182.lab4;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import co.edu.udea.compumovil.gr01_20182.lab4.Utilities.Utilities;

/**
 * Implementation of App Widget functionality.
 */
public class FoodWidget extends AppWidgetProvider {



    static void updateAppWidget(final Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        SharedPreferences preferences = context.getSharedPreferences("credentials", context.MODE_PRIVATE);
        CharSequence widgetName = preferences.getString("wg_name", "No existe la información");
        CharSequence widgetType = preferences.getString("wg_type", "No existe la información");
        CharSequence widgetPrice = preferences.getString("wg_price", "No existe la información");
        //CharSequence widgetText = context.getString(R.string.appwidget_text);
        //CharSequence widgetText = Utilities.WG_NAME;
        //CharSequence widgetName = Utilities.WG_TYPE;
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.food_widget);
        views.setTextViewText(R.id.appwidget_name, widgetName);
        views.setTextViewText(R.id.appwidget_type, "Tipo: "+widgetType);
        views.setTextViewText(R.id.appwidget_price, "Precio: $"+widgetPrice);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }



    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }



}

