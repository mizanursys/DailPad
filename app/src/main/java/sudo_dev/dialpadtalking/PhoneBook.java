package sudo_dev.dialpadtalking;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

@SuppressLint("Registered")
public class PhoneBook extends ListActivity {


    private ArrayList<String> conNames;
    private ArrayList<String> conNumbers;

   // @RequiresApi(api = Build.VERSION_CODES.O_MR1)
    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        conNames = new ArrayList<> ();
        conNumbers = new ArrayList<> ();

        Cursor curLog = phhonbookhelper.getAllCallLogs(getContentResolver());
        setCallLogs(curLog);

        setListAdapter(new MyAdapter (this, android.R.layout.simple_list_item_1,
                R.id.tvNameMain, conNames));
    }

    private class MyAdapter extends ArrayAdapter<String> {

        MyAdapter(Context context,int resource,int textViewResourceId,
                  ArrayList<String> conNames) {
            super ( context,resource,textViewResourceId,conNames );

        }

        @NonNull
        @Override
        public View getView(int position,View convertView,@NonNull ViewGroup parent) {
            return setList ( position,parent );
        }

        private View setList(final int position,ViewGroup parent) {

            LayoutInflater inf = (LayoutInflater) getSystemService ( Context.LAYOUT_INFLATER_SERVICE );

            assert inf != null;
            View row = inf.inflate ( R.layout.phonebook,parent,false );

            TextView tvName = row.findViewById ( R.id.name );
            TextView tvNumber = row.findViewById ( R.id.number );
            TextView tvTypesms = row.findViewById ( R.id.sms );
            TextView call =  row.findViewById ( R.id.call );
            Animation animation= AnimationUtils.loadAnimation ( PhoneBook.this, R.anim.fab_slide_in_from_left );

            row.startAnimation ( animation );


            tvNumber.setText ( conNumbers.get ( position ) );
            tvName.setText ( conNames.get ( position ) );

            tvTypesms.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    Intent smsIntent = new Intent ( Intent.ACTION_VIEW );
                    smsIntent.setData ( Uri.parse ( "smsto:" ) );
                    smsIntent.setType ( "vnd.android-dir/mms-sms" );
                    smsIntent.putExtra ( "address",conNumbers.get ( position ) );
                    smsIntent.putExtra ( "sms_body","" );
                    startActivity ( smsIntent );
                }
            } );
            call.setOnClickListener ( new View.OnClickListener () {
                @SuppressLint("MissingPermission")
                @Override
                public void onClick(View v) {
                    Intent intentcall = new Intent ( Intent.ACTION_CALL );
                    intentcall.setData ( Uri.parse ( "tel:" + conNumbers.get ( position ) ) );
                    startActivity ( intentcall );

                }
            } );

            return row;
        }


    }
    //@RequiresApi(api = Build.VERSION_CODES.O_MR1)
    @SuppressLint({"ResourceType","StringFormatInvalid","NewApi"})
    private void setCallLogs(Cursor curLog) {
        final int REQUEST_PHONE_CALL = 0;
//        Intent intent = new Intent ( Intent.ACTION_CALL );

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (ContextCompat.checkSelfPermission ( PhoneBook.this,Manifest.permission.CALL_PHONE ) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions ( PhoneBook.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL );
            }

        }

        while (curLog.moveToNext ()) {

            String callNumber = curLog.getString ( curLog.getColumnIndex ( ContactsContract.CommonDataKinds.Phone.NUMBER) );
            if (callNumber.equals ( "" ))
                continue;
            else conNumbers.add ( callNumber );


            String callName = curLog.getString ( curLog.getColumnIndex ( ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY) );
            if (callName.equals ( callNumber ))
                conNames.add ( "Unsaved" );
            else
            conNames.add ( callName );

            }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater imf = getMenuInflater ();
        imf.inflate ( R.menu.main_menu,menu );
        return true;
    }
    public void onBackPressed() {
        startActivity ( new Intent ( this,MainActivity.class  ) );
        finish ();
    }
}
