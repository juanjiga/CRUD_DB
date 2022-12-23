package com.juanjiga.crud_db;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //TextView textID;
    EditText textID, textName, textUser, textPassword;
    Button ButtonInsert, ButtonFind, ButtonUpdate, ButtonDelete;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textID=(EditText) findViewById(R.id.editTextID);
        textName=(EditText) findViewById(R.id.editTextName);
        textUser=(EditText) findViewById(R.id.editTextUser);
        textPassword=(EditText) findViewById(R.id.editTextPasswd);

        ButtonInsert=(Button) findViewById(R.id.buttonInsert);
        ButtonFind=(Button) findViewById(R.id.buttonFind);
        ButtonUpdate=(Button) findViewById(R.id.buttonUpdate);
        ButtonDelete=(Button) findViewById(R.id.buttonDelete);

        Helper_db helper = new Helper_db(this);

        //metodo para no repetir Values en los metodos "CRUD"
        /*private ContentValues valores(Pojo pojo) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Estructura_db.C_NAME, pojo.getName());
            contentValues.put(Estructura_db.C_USER, pojo.getUser());
            contentValues.put(Estructura_db.C_PASSWORD, pojo.getPassword());
            return contentValues;
        }*/

        //Botones
        ButtonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Gets the data repository in write mode
                SQLiteDatabase db = helper.getWritableDatabase();

                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                //values.put(Estructura_db._ID, textID.getText().toString());
                values.put(Estructura_db.C_NAME, textName.getText().toString());
                values.put(Estructura_db.C_USER, textUser.getText().toString());
                values.put(Estructura_db.C_PASSWORD, textPassword.getText().toString());

                // Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(Estructura_db.TABLE_NAME, null, values);

                Toast.makeText(getApplicationContext(), "Se guardó el registro con ID " + newRowId,
                        Toast.LENGTH_LONG).show();

                textID.setText("");
                textName.setText("");
                textUser.setText("");
                textPassword.setText("");

            }
        });

        ButtonFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Gets the data repository in read mode
                SQLiteDatabase db = helper.getReadableDatabase();

                // Define a projection that specifies which columns from the database
                // you will actually use after this query.
                String[] projection = {
                        //BaseColumns._ID,
                        Estructura_db._ID,
                        Estructura_db.C_NAME,
                        Estructura_db.C_USER,
                        Estructura_db.C_PASSWORD
                };

                // Filter results WHERE "title" = 'My Title'
                String selection = Estructura_db.C_NAME + " = ?";
                String[] selectionArgs = {textName.getText().toString()  };

                // How you want the results sorted in the resulting Cursor
                /*String sortOrder =
                        FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";*/
                try {
                    Cursor cursor = db.query(
                            Estructura_db.TABLE_NAME,   // The table to query
                            projection,             // The array of columns to return (pass null to get all)
                            selection,              // The columns for the WHERE clause
                            selectionArgs,          // The values for the WHERE clause
                            null,                   // don't group the rows
                            null,                   // don't filter by row groups
                            null                    // The sort order (sortOrder)
                    );

                    cursor.moveToFirst();
                    textID.setText(cursor.getString(0));
                    //textName.setText(cursor.getString(1));
                    textUser.setText(cursor.getString(2));
                    textPassword.setText(cursor.getString(3));

                } catch(Exception e) {
                    Toast.makeText(getApplicationContext(), "No se encontró registro",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        ButtonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Gets the data repository in write mode
                SQLiteDatabase db = helper.getWritableDatabase();

                // New value for one column
                //String title = "MyNewTitle";
                ContentValues values = new ContentValues();
                values.put(Estructura_db.C_NAME, textName.getText().toString());
                values.put(Estructura_db.C_USER, textUser.getText().toString());
                values.put(Estructura_db.C_PASSWORD, textPassword.getText().toString());

                // Which row to update, based on the title
                String selection = Estructura_db._ID + " LIKE ?";
                String[] selectionArgs = { textID.getText().toString() };

                int count = db.update(
                        Estructura_db.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs);

                Toast.makeText(getApplicationContext(), "No se actualizó el registro con ID " +
                        textID.getText().toString(), Toast.LENGTH_LONG).show();


            }
        });

        ButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Gets the data repository in write mode
                SQLiteDatabase db = helper.getWritableDatabase();

                // Define 'where' part of query.
                String selection = Estructura_db._ID + " LIKE ?";

                // Specify arguments in placeholder order.
                String[] selectionArgs = {textID.getText().toString() };

                // Issue SQL statement.
                int deletedRows = db.delete(Estructura_db.TABLE_NAME, selection, selectionArgs);

                Toast.makeText(getApplicationContext(), "Se Borró el registro con ID " +
                        textID.getText().toString(), Toast.LENGTH_LONG).show();

                textID.setText("");
                textName.setText("");
                textUser.setText("");
                textPassword.setText("");



            }
        });

    }
}