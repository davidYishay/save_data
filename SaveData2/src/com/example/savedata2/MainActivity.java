package com.example.savedata2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;


public class MainActivity extends Activity {

    static String saveNum;
    EditText et1;
    EditText et2;
    TextView tv;
    User user;
    ArrayList<User> userArray;      
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		et1 = (EditText)findViewById(R.id.et1);
		et2 =(EditText)findViewById(R.id.et2);
     	tv = (TextView)findViewById(R.id.textViewShowArrayList);
     	
     	tv.setMovementMethod(new ScrollingMovementMethod());
		
		Log.d("save2", "start");
		try{
			FileInputStream fis = openFileInput("david");
			Log.d("save2", "level 2");
			ObjectInputStream is = new ObjectInputStream(fis);	
			Log.d("save2", "level 3");		
		    userArray = (ArrayList<User>)is.readObject();	
		    Log.d("save2", "level 4");
			is.close();		
			String showUser="";
			if(userArray.size()>0)
			{
				int numOfUser=1;
				for(User i: userArray)
				{
					showUser += numOfUser+") "+ i.toString()+"\n";
				}
				
				tv.setText(showUser+" **  "+ userArray.size());
			}
			else 
			 showUser ="the list is emty"; 

			Log.d("save2", "level 5 :try end");
		}
		catch( IOException e)
		{
			Log.e("save2", "error input");
			userArray =new ArrayList<User>();
		} 
		catch(ClassNotFoundException e) 
		{
			userArray =new ArrayList<User>();
		} 
		Button bt, btDelete;
		bt=(Button)findViewById(R.id.buttonShow);
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
		
				try{
					String name,food;
					name=et1.getText().toString();
					food=et2.getText().toString();
					userArray.add(new User(name, 30,food, true));
					////////////////////
					FileOutputStream fos = openFileOutput("david", MODE_PRIVATE);
					ObjectOutputStream os = new ObjectOutputStream(fos);
					os.writeObject(userArray);
					os.close();
					fos.close();
					Toast.makeText(MainActivity.this, "save data", Toast.LENGTH_SHORT).show();
				}
				catch(IOException e)
				{
					Log.e("save2","error output");
				}
			}
		});
		 btDelete =(Button)findViewById(R.id.buttonDelete);
		btDelete.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v) {
				
				String deleteUser = et1.getText().toString();
				
				boolean exist;
				int z=0;
                 for(User i :userArray)
                 { 
                	 if(i.name.equals(deleteUser))
                	 {
                		 
                		 exist=true;
                		 Toast.makeText(MainActivity.this, "delete data of "+i.name, Toast.LENGTH_SHORT).show();
                		
                	 }         	
                	 z++;
                 }
                 if(exist=false)
                 Toast.makeText(MainActivity.this, deleteUser+" was not found in the list", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		
	}
}
