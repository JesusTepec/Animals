package com.example.hdsolujtepec.animals.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hdsolujtepec.animals.Adapters.PhotosSpinnerAdapter;
import com.example.hdsolujtepec.animals.Model.AdminSQLiteOpenHelper;
import com.example.hdsolujtepec.animals.R;
import com.example.hdsolujtepec.animals.Utilidades.AnalyticsRegistro;

public class FormAgregarAnimal extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText nombre;
    private EditText habitad;
    private Spinner photoId;
    private int[] images = {R.drawable.leon, R.drawable.zorro, R.drawable.pingu};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_agregar_animal);

        nombre = (EditText) findViewById(R.id.editTextNombre);
        habitad = (EditText) findViewById(R.id.editTextHabitad);

        photoId = (Spinner) findViewById(R.id.editTextPhotoId);
        photoId.setAdapter(new PhotosSpinnerAdapter(this, images));
        photoId.setOnItemSelectedListener( this);

    }

    public void agregarAnimal(View view){
        AdminSQLiteOpenHelper admin =
                new AdminSQLiteOpenHelper(this,"animals_db", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String nombre = this.nombre.getText().toString();
        String habitad = this.habitad.getText().toString();
        String photo = this.photoId.getSelectedItem().toString();

        ContentValues registro = new ContentValues();

        registro.put("id", 2);
        registro.put("nombre", nombre);
        registro.put("habitad", habitad);
        registro.put("photoId", photo);

        bd.insert("animal", null, registro);

        bd.close();

        Intent main = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(main);

        (new AnalyticsRegistro(this)).registrarEvento(AnalyticsRegistro.Metrica.NUEVO_REGISTRO);

        Toast.makeText(this, "Nuevo animal agregado", Toast.LENGTH_SHORT).show();
    }

    public void cancelar(View view){
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
