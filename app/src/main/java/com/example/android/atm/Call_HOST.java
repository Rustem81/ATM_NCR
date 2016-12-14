package com.example.android.atm;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

/**
 * Created by android on 17.12.2015.
 */
public class Call_HOST extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Phone();

    }

    private void  Phone()

    {
        // Create the implicit Intent to use to start a new Activity.
        Intent intent =
                new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+79603982505"));

        // Check if an Activity exists to perform this action.
        PackageManager pm = getPackageManager();
        ComponentName cn = intent.resolveActivity(pm);
        if (cn == null) {
            // If there is no Activity available to perform the action
            // Check to see if the Market is available.
            Uri marketUri =
                    Uri.parse("market://search?q=pname:com.myapp.packagename");
            Intent marketIntent = new
                    Intent(Intent.ACTION_VIEW).setData(marketUri);

            // If the Market is available, use it to download an application
            // capable of performing the required action. Otherwise log an
            // error.
            if (marketIntent.resolveActivity(pm) != null)
                startActivity(marketIntent);

        }
        else
            startActivity(intent);
    }


}
