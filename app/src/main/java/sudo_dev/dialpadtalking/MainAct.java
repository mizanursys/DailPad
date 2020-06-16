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
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SuppressLint("Registered")
public class MainAct extends ListActivity {


	private ArrayList<String> conNames;
	private ArrayList<String> conNumbers;
	private ArrayList<String> conTime;
	private ArrayList<String> conDate;
	private ArrayList<String> Type;

	//@RequiresApi(api = Build.VERSION_CODES.O_MR1)
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		conNames = new ArrayList<> ();
		conNumbers = new ArrayList<> ();
		conTime = new ArrayList<> ();
		conDate = new ArrayList<> ();
		Type = new ArrayList<> ();

		Cursor curLog = CallLogHelper.getAllCallLogs(getContentResolver());
		setCallLogs(curLog);

		setListAdapter(new MyAdapter(this, android.R.layout.simple_list_item_1,
				R.id.tvNameMain, conNames));

	}

	private class MyAdapter extends ArrayAdapter<String> {

		MyAdapter(Context context,int resource,int textViewResourceId,
				  ArrayList <String> conNames) {
			super ( context,resource,textViewResourceId,conNames );

		}

		@NonNull
		@Override
		public View getView(int position,View convertView,@NonNull ViewGroup parent) {
			View row = setList ( position,parent );

			return row;
		}

		@SuppressLint("SetTextI18n")
		private View setList(final int position,ViewGroup parent) {

			LayoutInflater inf = (LayoutInflater) getSystemService ( Context.LAYOUT_INFLATER_SERVICE );

			assert inf != null;
			View row = inf.inflate ( R.layout.liststyle,parent,false );

			TextView tvName = row.findViewById ( R.id.tvNameMain );
			TextView tvNumber =  row.findViewById ( R.id.tvNumberMain );
			final TextView tvTime =  row.findViewById ( R.id.tvTime );
			final TextView tvDate = row.findViewById ( R.id.tvDate );
			TextView tvType = row.findViewById ( R.id.tvType );
			TextView tvTypesms =  row.findViewById ( R.id.tvTypesms );
			TextView add = row.findViewById ( R.id.add );



			tvNumber.setText ( conNumbers.get ( position ) );
			tvName.setText ( conNames.get ( position ) );
			tvTime.setText ( "( " + conTime.get ( position ) +" sec)" );
			//tvTime.setText ( conTime.get ( position ) );
			tvDate.setText ( conDate.get ( position ) );
			tvType.setText (  "" + Type.get ( position ) + "");

			Animation animation= AnimationUtils.loadAnimation ( MainAct.this, R.anim.slide2 );
			row.startAnimation ( animation );


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

			add.setOnClickListener ( new View.OnClickListener () {
				@Override
				public void onClick(View v) {
					Intent intent2 = new Intent ( MainAct.this,InsertCallLog.class );
					intent2.putExtra ( "address:",conNumbers.get ( position ) );
					String number = conNumbers.get ( position ).toString ();
					intent2.putExtra ( "key",number );
					startActivity ( intent2 );

				}
			} );

			tvNumber.setOnClickListener ( new View.OnClickListener () {
				@SuppressLint({"MissingPermission","NewApi"})
				@Override
				public void onClick(View v) {
					Intent intent = new Intent ( Intent.ACTION_CALL );
					intent.setData ( Uri.parse ( "tel:" + conNumbers.get ( position ) ) );
					startActivity ( intent );

				}
			} );
			tvDate.setOnClickListener ( new View.OnClickListener () {
				@SuppressLint({"MissingPermission","NewApi"})
				@Override
				public void onClick(View v) {
					Intent intent = new Intent ( Intent.ACTION_CALL );
					intent.setData ( Uri.parse ( "tel:" + conNumbers.get ( position ) ) );
					startActivity ( intent );

				}
			} );
			tvName.setOnClickListener ( new View.OnClickListener () {
				@SuppressLint({"MissingPermission","NewApi"})
				@Override
				public void onClick(View v) {
					Intent intent = new Intent ( Intent.ACTION_CALL );
					intent.setData ( Uri.parse ( "tel:" + conNumbers.get ( position ) ) );
					startActivity ( intent );

				}
			} );
			tvTime.setOnClickListener ( new View.OnClickListener () {
				@SuppressLint({"MissingPermission","NewApi"})
				@Override
				public void onClick(View v) {
					Intent intent = new Intent ( Intent.ACTION_CALL );
					intent.setData ( Uri.parse ( "tel:" + conNumbers.get ( position ) ) );
					startActivity ( intent );

				}
			} );
			tvDate.setOnLongClickListener ( new View.OnLongClickListener () {
				@Override
				public boolean onLongClick(View v) {
					Log.v ( "long clicked","pos: " + conNames.get ( position ) );
					return false;
				}

			} );
			return row;
		}

	}


			//@RequiresApi(api = Build.VERSION_CODES.O_MR1)
			@SuppressLint({"ResourceType","StringFormatInvalid","NewApi"})
			private ArrayList<String> setCallLogs(Cursor curLog) {
				final int REQUEST_PHONE_CALL = 0;
				Intent intent = new Intent ( Intent.ACTION_CALL );

				if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
					if (ContextCompat.checkSelfPermission ( MainAct.this,Manifest.permission.CALL_PHONE ) != PackageManager.PERMISSION_GRANTED) {
						ActivityCompat.requestPermissions ( MainAct.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL );
					}

				}
				//TextView tvType = (TextView) findViewById(R.id.tvType);
				//tvType.setText ( "( " + Type.get ( position ) + " )" );
				label:
				while (curLog.moveToNext()) {

					String callNumber=curLog.getString(curLog.getColumnIndex(CallLog.Calls.NUMBER));
					conNumbers.add(callNumber);


					String callName=curLog.getString(curLog.getColumnIndex(CallLog.Calls.CACHED_NAME));

					if (callName == null) {
						conNames.add("unsaved");
					} else
						conNames.add(callName);

					String callDate=curLog.getString(curLog.getColumnIndex(CallLog.Calls.DATE));
					String duration=curLog.getString(curLog.getColumnIndex(CallLog.Calls.DURATION));
					conTime.add(duration);


					@SuppressLint("SimpleDateFormat")
					SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yy hh:mm a");
					String dateString=formatter.format(new Date(Long.parseLong(callDate)));
					conDate.add(dateString);


					String callType=curLog.getString(curLog.getColumnIndex(CallLog.Calls.TYPE));

					switch (callType) {
						case ("1"):
							Type.add("Incoming");
							break;
						case ("3"):
							Type.add("Missed");
							break ;
						case ("2"):
							Type.add("Outgoing");
							break;

					}
				}



				return null;

			}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater imf = getMenuInflater();
		imf.inflate(R.menu.main_menu, menu);
		return true;
	}

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		if (item.getItemId() == R.id.ifRoom) {
//			Intent intent = new Intent(MainAct.this, InsertCallLog.class);
//			startActivity(intent);
//		}
//		return super.onOptionsItemSelected(item);
//	}
@Override
public void onBackPressed() {
	startActivity ( new Intent ( this,MainActivity.class  ) );
	finish ();
    }
}
