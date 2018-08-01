package com.example.hdsolujtepec.animals.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hdsolujtepec.animals.Model.AdminSQLiteOpenHelper;
import com.example.hdsolujtepec.animals.R;

public class FormAgregarAnimal extends AppCompatActivity {

    private EditText nombre;
    private EditText habitad;
    private EditText photoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_agregar_animal);

        nombre = (EditText) findViewById(R.id.editTextNombre);
        habitad = (EditText) findViewById(R.id.editTextNombre);
        photoId = (EditText) findViewById(R.id.editTextNombre);
    }

    public void agregarAnimal(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,

                "animals_db", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        String nombre = this.nombre.getText().toString();
        String habitad = this.habitad.getText().toString();
        String photo = this.photoId.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("id", 1);
        registro.put("nombre", nombre);
        registro.put("habitad", habitad);
        registro.put("photoId", photo);

        // los inserto en la base de datos
        bd.insert("usuario", null, registro);

        bd.close();

        // ponemos los campos a vacío para insertar el siguiente usuario
        this.nombre.setText(""); this.habitad.setText(""); this.photoId.setText("");

        Intent main = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(main);

        Toast.makeText(this, "Datos del usuario cargados", Toast.LENGTH_SHORT).show();

    }
}
