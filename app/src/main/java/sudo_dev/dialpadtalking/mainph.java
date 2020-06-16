package sudo_dev.dialpadtalking;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Home on 3/21/2019.
 */

public class mainph extends AppCompatActivity {

    private EditText search;
    private CustomAdapter customAdapter;
    private ArrayList<model> contactModelArrayList;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_phone_book );

        ListView listView =findViewById ( R.id.listView );
        search = findViewById ( R.id.search );


        contactModelArrayList = new ArrayList<> ();


        // String strOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " DESC";

        @SuppressLint("Recycle") Cursor phones = getContentResolver ().query ( ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,
                ContactsContract.CommonDataKinds.Phone.SORT_KEY_PRIMARY );
        if (phones != null) {
            while (phones.moveToNext ()) {

                String name = phones.getString ( phones.getColumnIndex ( ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME ) );
                String phoneNumber = phones.getString ( phones.getColumnIndex ( ContactsContract.CommonDataKinds.Phone.NUMBER ) );
              //  String search = phones.getString ( phones.getColumnIndex ( ContactsContract.CommonDataKinds.Phone.SEARCH_DISPLAY_NAME_KEY ) );
                // String pic=phones.getString (phones. getColumnIndex ( ContactsContract.CommonDataKinds.Phone.PHOTO_URI));
                //   if (Integer.parseInt(phones.getString(phones.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)


    //            if (contactImage != null) {
    //                try {
    //                    bitmap = MediaStore.Images.Media
    //                            .getBitmap(getApplicationContext().getContentResolver(),
    //                                    Uri.parse(contactImage));
    //                    contactImage = getImageBytes(bitmap) ;
    //                } catch (FileNotFoundException e) {
    //                    e.printStackTrace();
    //                } catch (IOException e) {
    //                    e.printStackTrace();
    //                }
    //            } else {
    //                contactImage = null;
                //}

                model contactModel = new model ();

                contactModel.setName ( name );

                if (phoneNumber.equals ( "" ))
                    continue;
                else
                    contactModel.setNumber ( phoneNumber );


                contactModelArrayList.add ( contactModel );
                Log.d ( "",name + phoneNumber );
            }
        }
    }
}

// call.setOnClickListener ( new View.OnClickListener () {
//    @SuppressLint("MissingPermission")
//    @Override
//    public void onClick(View v) {
//        Intent intent = new Intent ( Intent.ACTION_CALL );
//        intent.setData ( Uri.parse ( "tel:" + contactModelArrayList) );
//        startActivity ( intent );
//    }
//}
//);


//    private byte[] getImageBytes(Bitmap bitmap) {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream ();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
//        return outputStream.toByteArray();
//    }

