package ro.pub.cs.systems.pdsd.lab04.contactsmanager;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class ContactsManagerActivity extends Activity {
	
	LinearLayout lay;
	boolean click=false;
	Button btn1;
	Button btn2;
	Button btn3;
	
	EditText name;
	EditText phone;
	EditText email;
	EditText address;
	EditText jobTitle;
	EditText company;
	EditText website;
	EditText im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);
        btn1 =(Button)findViewById(R.id.button1);
        btn2 =(Button)findViewById(R.id.button2);
        btn3 =(Button)findViewById(R.id.button3);
        
        name =(EditText)findViewById(R.id.editText1);
        phone =(EditText)findViewById(R.id.editText2);
        email =(EditText)findViewById(R.id.editText3);
        address =(EditText)findViewById(R.id.editText4);
        jobTitle =(EditText)findViewById(R.id.editText5);
        company =(EditText)findViewById(R.id.editText6);
        website =(EditText)findViewById(R.id.editText7);
        im =(EditText)findViewById(R.id.editText8);
        
        btn1.setOnClickListener(new Button.OnClickListener(){	
            @Override
            public void onClick(View view) {
            	if(click==false){
					lay =(LinearLayout)findViewById(R.id.layout2);
					lay.setVisibility(View.GONE);
					btn1.setText("Show Additional Information");
					click=true;
            	}
				else{
					lay =(LinearLayout)findViewById(R.id.layout2);
					lay.setVisibility(View.VISIBLE);
					btn1.setText("Hide  Additional Information");
					click=false;
				}	
            }
          });
        
        btn2.setOnClickListener(new Button.OnClickListener(){	
            @Override
            public void onClick(View view) {
            	Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
            	intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            	if (name != null) {
            	  intent.putExtra(ContactsContract.Intents.Insert.NAME, name.getText().toString());
            	}
            	if (phone != null) {
            	  intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone.getText().toString());
            	}
            	if (email != null) {
            	  intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email.getText().toString());
            	}
            	if (address != null) {
            	  intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address.getText().toString());
            	}
            	if (jobTitle != null) {
            	  intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobTitle.getText().toString());
            	}
            	if (company != null) {
            	  intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company.getText().toString());
            	}
            	ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
            	if (website != null) {
            	  ContentValues websiteRow = new ContentValues();
            	  websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
            	  websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website.getText().toString());
            	  contactData.add(websiteRow);
            	}
            	if (im != null) {
            	  ContentValues imRow = new ContentValues();
            	  imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
            	  imRow.put(ContactsContract.CommonDataKinds.Im.DATA, im.getText().toString());
            	  contactData.add(imRow);
            	}
            	intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
            	startActivity(intent);
            	
            	startActivityForResult(intent, R.integer.CONTACTS_MANAGER_REQUEST_CODE);
            }
            
        });
        
        btn3.setOnClickListener(new Button.OnClickListener(){	
            @Override
            public void onClick(View view) {
            	finish();
            }
        });
        
        
        Intent intent = getIntent();
        if (intent != null) {
          String phonestr = intent.getStringExtra("ro.pub.cs.systems.pdsd.lab04.contactsmanager.PHONE_NUMBER_KEY");
          if (phone != null) {
            phone.setText(phonestr);
          } else {
           // Toast.makeText(this, activity.getResources().getString(R.string.phone_error), Toast.LENGTH_LONG).show();
          }
        } 
        

    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	switch(requestCode) {
    	  case R.integer.CONTACTS_MANAGER_REQUEST_CODE:
    	    setResult(resultCode, new Intent());
    	    finish();
    	    break;
    	  }
    	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contacts_manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
