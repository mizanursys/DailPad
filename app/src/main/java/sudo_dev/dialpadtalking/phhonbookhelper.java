package sudo_dev.dialpadtalking;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

public class phhonbookhelper {

    static Cursor getAllCallLogs(ContentResolver cr) {
        // reading all data in descending order according to DATE
        String strOrder = ContactsContract.CommonDataKinds.Phone.SORT_KEY_PRIMARY  ;
        Uri callUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI ;

        return cr.query(callUri, null, null, null, strOrder);
    }
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
    @SuppressLint("MissingPermission")
    public static void insertPlaceholderCall(ContentResolver contentResolver,
                                             String name,String number) {
        ContentValues values = new ContentValues ();
        values.put( ContactsContract.CommonDataKinds.Phone.NUMBER, number);
        values.put( ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, name);
        Log.d("",   number+name);
        contentResolver.insert( ContactsContract.CommonDataKinds.Phone.CONTENT_URI, values);
    }

}
