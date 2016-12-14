package com.example.android.atm;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class About extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t);

   }


    public void SendEmail(View view) {
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

    private void SendEmail() {
        String[] mail = {getString(R.string.IntegralEmailAddress)};
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("message/rfc822");
        share.putExtra(Intent.EXTRA_EMAIL, mail);
        share.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        share.putExtra(Intent.EXTRA_TEXT, "msg body");
        startActivity(Intent.createChooser(share, "Send Email"));
    }
    public static void dialNumber(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + "5551222"));
        activity.startActivity(intent);
    }

    private void call() {
        Intent in=new Intent(Intent.ACTION_CALL,Uri.parse("+79063742710"));
        try{
            startActivity(in);
        }

        catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(getApplicationContext(),"yourActivity is not founded", Toast.LENGTH_SHORT).show();
        }
    }
    private void callSendMeMail() {
        Intent Email = new Intent(Intent.ACTION_SEND);
        try{
        Email.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
        Email.setType("text/email");
        Email.putExtra(Intent.EXTRA_EMAIL, new String[]{"mail@mail.com" });
        Email.putExtra(Intent.EXTRA_SUBJECT, "ATM_Support");
        startActivity(Intent.createChooser(Email, "Отправить сообщение разработчику"));}
        catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(getApplicationContext(),"На телефоне не установлен клиент GMail", Toast.LENGTH_SHORT).show();
        }
    }



}
/*   public void URL(){

    Uri address = Uri.parse("http://developer.alexanderklimov.ru");
    Intent openlink = new Intent(Intent.ACTION_VIEW, address);
    startActivity(openlink);}

    public void Email(){

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");  //set the email recipient
        String recipient = getString(R.string.IntegralEmailAddress);
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL  , recipient);
        //let the user choose what email client to use
        startActivity(Intent.createChooser(emailIntent, "Send mail using...")); }
*/
