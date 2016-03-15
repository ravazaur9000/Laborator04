
package ro.pub.cs.systems.pdsd.lab03.phonedialer;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class PhoneDialerActivity extends Activity {

	static int buttonids[] = {R.id.button1,R.id.button2,R.id.button3,
							R.id.button4,R.id.button5,R.id.button6,
							R.id.button7,R.id.button8,R.id.button9,
							R.id.button10,R.id.button11,R.id.button12,R.id.button13};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_phone_dialer);
        final EditText edit=(EditText)(findViewById(R.id.editText1));
        final ImageButton backspaceButton = (ImageButton)findViewById(R.id.imageButton1);
        final ImageButton endcallButton = (ImageButton)findViewById(R.id.imageButton2);
        final ImageButton callButton = (ImageButton)findViewById(R.id.imageButton3);
        final Button addButton = (Button)findViewById(R.id.button13);
        
        
        for(int i=0;i<buttonids.length;i++){
        	final Button btn = (Button)findViewById(buttonids[i]);      	
        	btn.setOnClickListener(new OnClickListener() {
        		@Override
				public void onClick(View v) {
					edit.append(btn.getText().toString());
							
				}
			});

        }
        backspaceButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(edit.length()>0){
					edit.setText(edit.getText().subSequence(0, edit.length()-1));
				}
				
			}
		});
        endcallButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
        callButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_CALL);
				intent.setData(Uri.parse("tel:"+edit.getText().toString()));
				startActivity(intent);		
			}
		});
        
        addButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (edit.getText().length() > 0) {
					  Intent intent = new Intent("ro.pub.cs.systems.pdsd.lab04.contactsmanager.intent.action.ContactsManagerActivity");
					  intent.putExtra("ro.pub.cs.systems.pdsd.lab04.contactsmanager.PHONE_NUMBER_KEY", edit.getText().toString());
					  startActivityForResult(intent, R.integer.CONTACTS_MANAGER_REQUEST_CODE);
					} else {
					  Toast.makeText(getApplication(), getResources().getString(R.string.phone_error), Toast.LENGTH_LONG).show();
				}	
			}
		});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.phone_dialer, menu);
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