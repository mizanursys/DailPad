package sudo_dev.dialpadtalking;

import android.annotation.SuppressLint;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Locale;

@SuppressLint("Registered")
public class InsertCallLog extends AppCompatActivity {

	String number="";
    TextToSpeech t1;


	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate ( savedInstanceState );
		setContentView ( R.layout.addcontact );
		Bundle bundle = getIntent ().getExtras ();
		assert bundle != null;
		String number = bundle.getString ( "key" );
		EditText etMobile =findViewById ( R.id.et_mobile_phone );
		etMobile.setText ( number );
		View.OnClickListener addClickListener = new View.OnClickListener () {

			@Override
			public void onClick(View v) {
				// Getting reference to Name EditText
				EditText etName = findViewById ( R.id.et_name );
				// Getting reference to Mobile EditText
				EditText etMobile = findViewById ( R.id.et_mobile_phone );
				// Getting reference to HomePhone EditText
				EditText etHomePhone = findViewById ( R.id.et_home_phone );
				// Getting reference to HomeEmail EditText
				EditText etHomeEmail =findViewById ( R.id.et_home_email );
				// Getting reference to WorkEmail EditText
				EditText etWorkEmail =findViewById ( R.id.et_work_email );

				ArrayList<ContentProviderOperation> ops = new ArrayList<> ();

				int rawContactID = ops.size ();

				// Adding insert operation to operations list
				// to insert a new raw contact in the table ContactsContract.RawContacts
				ops.add ( ContentProviderOperation.newInsert ( ContactsContract.RawContacts.CONTENT_URI )
						.withValue ( ContactsContract.RawContacts.ACCOUNT_TYPE,null )
						.withValue ( ContactsContract.RawContacts.ACCOUNT_NAME,null )
						.build () );

				// Adding insert operation to operations list
				// to insert display name in the table ContactsContract.Data
				ops.add ( ContentProviderOperation.newInsert ( ContactsContract.Data.CONTENT_URI )
						.withValueBackReference ( ContactsContract.Data.RAW_CONTACT_ID,rawContactID )
						.withValue ( ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE )
						.withValue ( ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,etName.getText ().toString () )
						.build () );

				// Adding insert operation to operations list
				// to insert Mobile Number in the table ContactsContract.Data
				ops.add ( ContentProviderOperation.newInsert ( ContactsContract.Data.CONTENT_URI )
						.withValueBackReference ( ContactsContract.Data.RAW_CONTACT_ID,rawContactID )
						.withValue ( ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE )
						.withValue ( ContactsContract.CommonDataKinds.Phone.NUMBER,etMobile.getText ().toString () )
						.withValue ( ContactsContract.CommonDataKinds.Phone.TYPE,ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE )
						.build () );

				// Adding insert operation to operations list
				// to  insert Home Phone Number in the table ContactsContract.Data
				ops.add ( ContentProviderOperation.newInsert ( ContactsContract.Data.CONTENT_URI )
						.withValueBackReference ( ContactsContract.Data.RAW_CONTACT_ID,rawContactID )
						.withValue ( ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE )
						.withValue ( ContactsContract.CommonDataKinds.Phone.NUMBER,etHomePhone.getText ().toString () )
						.withValue ( ContactsContract.CommonDataKinds.Phone.TYPE,ContactsContract.CommonDataKinds.Phone.TYPE_HOME )
						.build () );

				// Adding insert operation to operations list
				// to insert Home Email in the table ContactsContract.Data
				ops.add ( ContentProviderOperation.newInsert ( ContactsContract.Data.CONTENT_URI )
						.withValueBackReference ( ContactsContract.Data.RAW_CONTACT_ID,rawContactID )
						.withValue ( ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE )
						.withValue ( ContactsContract.CommonDataKinds.Email.ADDRESS,etHomeEmail.getText ().toString () )
						.withValue ( ContactsContract.CommonDataKinds.Email.TYPE,ContactsContract.CommonDataKinds.Email.TYPE_HOME )
						.build () );

				// Adding insert operation to operations list
				// to insert Work Email in the table ContactsContract.Data
				ops.add ( ContentProviderOperation.newInsert ( ContactsContract.Data.CONTENT_URI )
						.withValueBackReference ( ContactsContract.Data.RAW_CONTACT_ID,rawContactID )
						.withValue ( ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE )
						.withValue ( ContactsContract.CommonDataKinds.Email.ADDRESS,etWorkEmail.getText ().toString () )
						.withValue ( ContactsContract.CommonDataKinds.Email.TYPE,ContactsContract.CommonDataKinds.Email.TYPE_WORK )
						.build () );
				ops.add ( ContentProviderOperation.newInsert ( ContactsContract.Data.CONTENT_URI )
						.withValueBackReference ( ContactsContract.Data.RAW_CONTACT_ID,rawContactID )
						.withValue ( ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE )
						.withValue ( ContactsContract.CommonDataKinds.Email.ADDRESS,etWorkEmail.getText ().toString () )
						.withValue ( ContactsContract.CommonDataKinds.Email.TYPE,ContactsContract.CommonDataKinds.Email.TYPE_WORK )
						.build () );


				try {
					// Executing all the insert operations as a single database transaction
					getContentResolver ().applyBatch ( ContactsContract.AUTHORITY,ops );
					Toast.makeText ( getBaseContext (),"Contact is successfully added",Toast.LENGTH_SHORT ).show ();
					startActivity ( new Intent ( getApplicationContext (),MainActivity.class ) );

					// Starting the activity
				} catch (RemoteException | OperationApplicationException e) {
					e.printStackTrace ();
				}
			}

		};


		// Creating a button click listener for the "Add Contact" button
		View.OnClickListener contactsClickListener = new View.OnClickListener () {

			@Override
			public void onClick(View v) {
				// Creating an intent to open Android's Contacts List
				Intent contacts = new Intent ( Intent.ACTION_VIEW,ContactsContract.Contacts.CONTENT_URI );
				// Starting the activity
				startActivity ( contacts );
			}
		};

		// Getting reference to "Add Contact" button
		Button btnAdd =findViewById ( R.id.btn_add );

		// Getting reference to "Contacts List" button

		// Setting click listener for the "Add Contact" button
		btnAdd.setOnClickListener ( addClickListener );

		// Setting click listener for the "List Contacts" button


		t1 = new TextToSpeech ( getApplicationContext (),new TextToSpeech.OnInitListener () {
			@Override
			public void onInit(int status) {
				if (status != TextToSpeech.ERROR) {
					t1.setLanguage ( Locale.ENGLISH );

				}
			}
		} );
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(android.view.MenuItem menu) {
		switch (menu.getItemId ()) {

			case R.id.about:
				startActivity ( new Intent ( this,About_ME.class ) );
				break;

			case R.id.call_list:
				String toSpeak = "Call List";
				t1.speak ( toSpeak,TextToSpeech.QUEUE_FLUSH,null );
				startActivity ( new Intent ( this,MainAct.class ) );
				break;
			case R.id.Missed:
				toSpeak = "Missed Calls";
				t1.speak ( toSpeak,TextToSpeech.QUEUE_FLUSH,null );
				startActivity ( new Intent ( this,Missedcall.class ) );
				break;
			case R.id.msg:
				toSpeak="Message";
				t1.speak ( toSpeak,TextToSpeech.QUEUE_FLUSH,null );
				Intent smsIntent = new Intent ( Intent.ACTION_VIEW );
				smsIntent.setData ( Uri.parse ( "smsto:" ) );
				smsIntent.setType ( "vnd.android-dir/mms-sms" );

				smsIntent.putExtra ( "address",number );
				smsIntent.putExtra ( "sms_body","" );
				try {
					startActivity ( smsIntent );
					finish ();
					Log.i ( "Finished sending SMS...","" );
				} catch (android.content.ActivityNotFoundException ignored) {

				}
				break;
			case R.id.contack:
				toSpeak = "contact";
				Toast.makeText ( getApplicationContext (),toSpeak,Toast.LENGTH_LONG ).show ();
				t1.speak ( toSpeak,TextToSpeech.QUEUE_FLUSH,null );
				startActivity ( new Intent ( this,PhoneBook.class ) );
				break;

		}
		return true;

	}
	@Override
	public void onBackPressed() {
		startActivity ( new Intent ( this,MainActivity.class  ) );
	}
}
