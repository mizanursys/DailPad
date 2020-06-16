package sudo_dev.dialpadtalking;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import static android.provider.CallLog.Calls.CACHED_NAME;

public class CallLogHelper {

	public static Cursor getAllCallLogs(ContentResolver cr) {
		// reading all data in descending order according to DATE
		String strOrder = CallLog.Calls.DATE + " DESC";
		Uri callUri = Uri.parse("content://call_log/calls");

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
		values.put( CallLog.Calls.NUMBER, number);
		values.put( CallLog.Calls.DATE, System.currentTimeMillis());
		values.put( CallLog.Calls.DURATION, 0);
		values.put( CallLog.Calls.TYPE, CallLog.Calls.INCOMING_TYPE);
		values.put( CallLog.Calls.NEW, 1);
		values.put( CACHED_NAME, name);
		values.put( CallLog.Calls.CACHED_NUMBER_TYPE, 0);
		values.put( CallLog.Calls.CACHED_NUMBER_LABEL, "");
		Log.d("Call Log", "Inserting call log placeholder for " + number);
		contentResolver.insert( CallLog.Calls.CONTENT_URI, values);
	}

}
