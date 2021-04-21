package com.example.contentprov;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn= findViewById( R.id.button2);
        text=findViewById(R.id.textView2);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtener();
            }
        });
    }


    public void obtener(){
        String [] projecion= new String[] {ContactsContract.Data._ID,ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.TYPE};
        String seleccion = ContactsContract.Data.MIMETYPE + " 'AND" + ContactsContract.CommonDataKinds.Phone.NUMBER + "ITS NOT NULL";
        String sort=ContactsContract.Data.DISPLAY_NAME + "ASC";

        Cursor c= getContentResolver().query(ContactsContract.Data.CONTENT_URI, projecion, seleccion, null, sort);
        text.setText("");

        while (c.moveToNext()){
            text.append("Identificador: "+ c.getString(0)+ " Nombre: "+c.getString(1)+" Número: "+c.getString(3)+" Tipo: "+c.getString(4)+"\n");
            c.close();
        }
    }
}