package sudo_dev.dialpadtalking;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Locale;

import sudo_dev.dialpadtalking.advert.SP;

import static android.net.Uri.encode;
import static sudo_dev.dialpadtalking.advert.AdsLib.checkSubStatus;
import static sudo_dev.dialpadtalking.advert.bdapps.Robi.MSG_TEXT;
import static sudo_dev.dialpadtalking.advert.bdapps.Robi.USSD;

public class MainActivity extends AppCompatActivity {
    public static boolean hasPermissions(Context context,String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission ( context,permission ) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public  boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG","Permission is granted");
                return true;
            } else {

                Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG","Permission is granted");
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    String number = "*213*"+USSD;
                    number =  number.replace("*", Uri.encode("*")).replace("#",Uri.encode("#"));
                    Intent mIntent = new Intent(Intent.ACTION_CALL);
                    Uri data = Uri.parse("tel:" + number);
                    mIntent.setData(data);
                    startActivity(mIntent);
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
    TextToSpeech t1;
    Button d2, d3, d4, d5, six, seven, eight, nine, zero, star, hash, one, call, c;
    TextView edt;
    String number = "";
    public static  String CALL_LOG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
//
//        checkSubStatus(SP.getSubCode());
//        if (SP.getSubscriptionStatus()) {
//            showDialog(MainActivity.this, "");
//        }
        edt = findViewById ( R.id.edt );
        one = findViewById ( R.id.one );
        d2 = findViewById ( R.id.two );
        d3 = findViewById ( R.id.three );
        d4 = findViewById ( R.id.four );
        d5 = findViewById ( R.id.five );
        six = findViewById ( R.id.six );
        seven = findViewById ( R.id.seven );
        eight = findViewById ( R.id.eight );
        nine = findViewById ( R.id.nine );
        zero = findViewById ( R.id.zero );
        call = findViewById ( R.id.call );
        c = findViewById ( R.id.delet );
        star = findViewById ( R.id.star );
        hash = findViewById ( R.id.hash );

        final int PERMISSION_ALL = 123;
        String[] PERMISSIONS = {Manifest.permission.READ_CONTACTS,Manifest.permission.WRITE_CONTACTS,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.WRITE_CALL_LOG,
                Manifest.permission.READ_CALL_LOG};

        if (!hasPermissions ( this,PERMISSIONS )) {
            ActivityCompat.requestPermissions ( this,PERMISSIONS,PERMISSION_ALL );
        }

        int REQUEST_PHONE_CALL = 123;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (ContextCompat.checkSelfPermission ( MainActivity.this,Manifest.permission.CALL_PHONE ) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions ( MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL );
            }
        }




        one.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                number = number + "1";
                edt.setText ( number );
                String toSpeak = one.getText ().toString ();
                t1.speak ( toSpeak,TextToSpeech.QUEUE_FLUSH,null );
                YoYo.with ( Techniques.FadeIn )
                        .duration ( 1000 )
                        .repeat(3)
                        .playOn ( findViewById ( R.id.one ) );
            }
        } );

        d2.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                number = number + "2";
                edt.setText ( number );
                String toSpeak = d2.getText ().toString ();
                t1.speak ( toSpeak,TextToSpeech.QUEUE_FLUSH,null );
                YoYo.with ( Techniques.FadeIn )
                        .duration ( 1000 )
                        .repeat(3)
                        .playOn ( findViewById ( R.id.two ) );
            }
        } );

        d3.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                number = number + "3";
                edt.setText ( number );
                String toSpeak = d3.getText ().toString ();
                t1.speak ( toSpeak,TextToSpeech.QUEUE_FLUSH,null );
                YoYo.with ( Techniques.FadeIn )
                        .duration ( 1000 )
                        .repeat(3)
                        .playOn ( findViewById ( R.id.three ) );
            }
        } );

        d4.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                number = number + "4";
                edt.setText ( number );
                String toSpeak = d4.getText ().toString ();
                t1.speak ( toSpeak,TextToSpeech.QUEUE_FLUSH,null );
                YoYo.with ( Techniques.FadeIn )
                        .duration ( 1000 )
                        .repeat(3)
                        .playOn ( findViewById ( R.id.four ) );
            }
        } );

        d5.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                number = number + "5";
                edt.setText ( number );
                String toSpeak = d5.getText ().toString ();
                t1.speak ( toSpeak,TextToSpeech.QUEUE_FLUSH,null );
                YoYo.with ( Techniques.FadeIn )
                        .duration ( 1000 )
                        .repeat(3)
                        .playOn ( findViewById ( R.id.five ) );
            }
        } );

        six.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                number = number + "6";
                edt.setText ( number );
                String toSpeak = six.getText ().toString ();
                t1.speak ( toSpeak,TextToSpeech.QUEUE_FLUSH,null );
                YoYo.with ( Techniques.FadeIn )
                        .duration ( 1000 )
                        .repeat(3)
                        .playOn ( findViewById ( R.id.six ) );
            }
        } );

        seven.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                number = number + "7";
                edt.setText ( number );

                String toSpeak = seven.getText ().toString ();
                t1.speak ( toSpeak,TextToSpeech.QUEUE_FLUSH,null );
                YoYo.with ( Techniques.FadeIn )
                        .duration ( 1000 )
                        .repeat(3)
                        .playOn ( findViewById ( R.id.seven ) );
            }
        } );

        eight.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                number = number + "8";
                edt.setText ( number );
                String toSpeak = eight.getText ().toString ();
                t1.speak ( toSpeak,TextToSpeech.QUEUE_FLUSH,null );
                YoYo.with ( Techniques.FadeIn )
                        .duration ( 1000 )
                        .repeat(3)
                        .playOn ( findViewById ( R.id.eight ) );
            }
        } );

        nine.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                number = number + "9";
                edt.setText ( number );
                String toSpeak = nine.getText ().toString ();
                t1.speak ( toSpeak,TextToSpeech.QUEUE_FLUSH,null );
                YoYo.with ( Techniques.FadeIn )
                        .duration ( 1000 )
                        .repeat(3)
                        .playOn ( findViewById ( R.id.nine ) );
            }
        } );

        zero.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                number = number + "0";
                edt.setText ( number );
                String toSpeak = "zero";
                t1.speak ( toSpeak,TextToSpeech.QUEUE_FLUSH,null );
                YoYo.with ( Techniques.FadeIn )
                        .duration ( 1000 )
                        .repeat(3)
                        .playOn ( findViewById ( R.id.zero ) );

            }
        } );
        zero.setOnLongClickListener ( new View.OnLongClickListener () {
            @Override
            public boolean onLongClick(View v) {
                number = number + "+";
                edt.setText ( number );
                String toSpeak = zero.getText ().toString ();
                t1.speak ( toSpeak,TextToSpeech.QUEUE_FLUSH,null );
                YoYo.with ( Techniques.FadeIn )
                        .duration ( 1000 )
                        .repeat(3)
                        .playOn ( findViewById ( R.id.zero ) );
                return true;
            }

        } );

        hash.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                number =number+ encode ( "#" ) ;
                edt.setText ("#");
                String toSpeak = "hash";
                t1.speak ( toSpeak,TextToSpeech.QUEUE_FLUSH,null );
                YoYo.with ( Techniques.ZoomInUp )
                        .playOn ( findViewById ( R.id.hash ) );
            }
        } );

        star.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                number = number + "*";
                edt.setText ( number );
                String toSpeak = "Star";
                t1.speak ( toSpeak,TextToSpeech.QUEUE_FLUSH,null );
                YoYo.with ( Techniques.ZoomInUp )
                        .duration ( 500 )
                        .repeat(3)
                        .playOn ( findViewById ( R.id.star ) );
            }
        } );

        t1 = new TextToSpeech ( getApplicationContext (),new TextToSpeech.OnInitListener () {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage ( Locale.ENGLISH );

                }
            }
        } );

        REQUEST_PHONE_CALL = 123;
//        Intent intent = new Intent ( Intent.ACTION_CALL );
        call.setOnClickListener ( new View.OnClickListener () {
            @Override
            public String toString() {
                return "$classname{}";
            }

            @SuppressLint("MissingPermission")
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                    String toSpeak = "Calling   \n" + number;
                    Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_LONG).show();

                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + number));
                    startActivity(intent);


            }
        } );


        c.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                String toSpeak = getString( R.string.BackSpace);

                t1.speak ( toSpeak,TextToSpeech.QUEUE_FLUSH,null );
                if (number.length () > 0) {

                    number = number.substring ( 0,number.length () - 1 );
                    edt.setText ( number );
                }

            }
        } );
        c.setOnLongClickListener ( new View.OnLongClickListener () {
            @Override
            public boolean onLongClick(View v) {
                String toSpeak = getString( R.string.Clear);
                t1.speak ( toSpeak,TextToSpeech.QUEUE_FLUSH,null );
                number = "";
                edt.setText ( number );
                return true;
            }
        } );

    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater ().inflate ( R.menu.main_menu,menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {
        switch (menu.getItemId ()) {
            case R.id.about:

                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.setComponent(new ComponentName(this, About_ME.class));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    setDefaultKeyMode(DEFAULT_KEYS_DIALER);
                    startActivity(intent);
                    //startActivity ( new Intent ( this,MainActivity.class ) );

                    break;
            case R.id.privecy:
                startActivity( new Intent ( this,privacy.class ) );
                break;
            case R.id.call_list:

                    String toSpeak = "Call List";
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                    startActivity(new Intent(this, MainAct.class));

                break;
            case R.id.Missed:

                    String toSpeak2 = "Missed Calls";
                    t1.speak(toSpeak2, TextToSpeech.QUEUE_FLUSH, null);
                    startActivity(new Intent(this, Missedcall.class));

                break;
            case R.id.msg:

                    String toSpeak3 = "Message";
                    t1.speak(toSpeak3, TextToSpeech.QUEUE_FLUSH, null);
                    Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                    smsIntent.setData(Uri.parse("smsto:"));
                    smsIntent.setType("vnd.android-dir/mms-sms");

                    smsIntent.putExtra("address", number);
                    smsIntent.putExtra("sms_body", "");
                    try {
                        startActivity(smsIntent);
                        finish();
                        Log.i("Finished sending SMS...", "");
                    } catch (ActivityNotFoundException ignored) {


                }
                break;
            case R.id.contack:

                    startActivity(new Intent(this, PhoneBook.class));
                toSpeak = "contact";
                    Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_LONG).show();
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

                break;
            case R.id.Add2:

                toSpeak = " Add New Contact";
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                    Intent intent2 = new Intent(MainActivity.this, InsertCallLog.class);
                    String number = edt.getText().toString();
                    intent2.putExtra("key", number);
                    startActivity(intent2);//content1 is String you want to pass in another activity
                    try {
                        startActivity(intent2);
                        finish();
                        Log.i("Finished sending SMS...", "");
                    } catch (ActivityNotFoundException ignored) {

                    }

                break;
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        finishAffinity ();
    }


    public void showDialog(final Activity activity, String msg) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_ACTION_MODE_OVERLAY);
        dialog.setCancelable(false);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.subscribe);

/*
        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
        text.setText(msg);
*/

        final LinearLayout ll_submit = dialog.findViewById(R.id.ll_sub3);
        final LinearLayout ll_msg = dialog.findViewById(R.id.ll_sub);
        final LinearLayout ll_code = dialog.findViewById(R.id.ll_code);
        Button button_cancel=(Button) dialog.findViewById(R.id.button_cancel1);

//
//        if (msg.equals("clicked")) {
//
//            ll_submit.setVisibility(View.GONE);
//            ll_code.setVisibility(View.VISIBLE);
//        }

        final EditText getCode = dialog.findViewById(R.id.editText_getCode);

        final Button dialogButton = (Button) dialog.findViewById(R.id.button_sendSMS3);

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dialog.dismiss();

              /*  Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address","21213");
                smsIntent.putExtra("sms_body","start abcd");
                startActivity(smsIntent);*/
                // SP.setSubscriptionClicked(true);

                ll_submit.setVisibility(View.GONE);
                ll_code.setVisibility(View.VISIBLE);
                Uri uri = Uri.parse("smsto:21213");
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra("sms_body", MSG_TEXT);
                startActivity(intent);
            }
        });


        dialog.findViewById(R.id.button_submit3)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(isPermissionGranted()){
                            String number = "*213*"+USSD;
                            number =  number.replace("*", Uri.encode("*")).replace("#",Uri.encode("#"));
                            Intent mIntent = new Intent(Intent.ACTION_CALL);
                            Uri data = Uri.parse("tel:" + number);
                            mIntent.setData(data);
                            startActivity(mIntent);
                        }
//                        Intent callIntent = new Intent(Intent.ACTION_CALL);
//                        callIntent.setData(Uri.parse("tel:*213*7821%23"));
//                        startActivity(callIntent);
                        Toast.makeText(activity, "Write a vald code", Toast.LENGTH_SHORT).show();
                        ll_submit.setVisibility(View.VISIBLE);
                    }
                });


        dialog.findViewById(R.id.button_code)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getCode.getText().toString().isEmpty()) {
                            Toast.makeText(activity, "Write a vald code", Toast.LENGTH_SHORT).show();
                        } else {
                            SP.setSubCode(getCode.getText().toString());
                            SP.setSubscriptionClicked(false);
                            checkSubStatus(getCode.getText().toString());
                            dialog.dismiss();
                        }

                    }
                });

        button_cancel.findViewById(R.id.button_cancel1)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SP.setSubscriptionClicked(false);
                        finish();
//                        Toast.makeText(activity, "Write a vald code", Toast.LENGTH_SHORT).show();
//                        ll_submit.setVisibility(View.GONE);
//                        ll_code.setVisibility(View.VISIBLE);
//                        dialog.dismiss();
                    }
                });
        dialog.show();
    }
}
