package com.example.hdsolujtepec.animals.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hdsolujtepec.animals.Adapters.RecyclerViewAdapter;
import com.example.hdsolujtepec.animals.Model.AdminSQLiteOpenHelper;
import com.example.hdsolujtepec.animals.Model.Animal;
import com.example.hdsolujtepec.animals.R;
import com.example.hdsolujtepec.animals.Utilidades.AnalyticsRegistro;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener {

    RecyclerViewAdapter adapter;
    ArrayList<Animal> animals = new ArrayList<>();
    int seleccionado = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        consultarTodo();
        // configuracion de RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycle_view_animals);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(this, animals);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        seleccionado = position;
    }

    public void onButtonClick(View view) {
        String buttonText = ((Button)view).getText().toString();
        if(buttonText.toLowerCase().contains("agregar".toLowerCase())) {

            Intent formAgregar = new Intent(getApplicationContext(), FormAgregarAnimal.class);
            startActivity(formAgregar);

        }
        if(buttonText.toLowerCase().contains("eliminar".toLowerCase()))
            removeSingleItem();
        if(buttonText.equals(getString(R.string.botonEditar)))
            updateSingleItem();
    }

    /**
     * Insertar después del item seleccionado dado por {@link #onItemClick(View, int)}
     */
    private void insertSingleItem() {
        Animal item = new Animal("Pingüino", "Antartida", R.drawable.pingu);
        animals.add(seleccionado + 1, item);
        adapter.notifyItemInserted(seleccionado + 1);
    }

    private void updateSingleItem() {
        Animal newValue = new Animal("Perro", "Ciudad", R.drawable.zorro);
        (new AnalyticsRegistro(this)).registrarEvento(AnalyticsRegistro.Metrica.NUEVO_REGISTRO);
        animals.set(seleccionado, newValue);
        adapter.notifyItemChanged(seleccionado);
    }

    /**
     * Elimina de la lista el item seleccionado con {@link #onItemClick(View, int)}
     */
    private void removeSingleItem() {
        animals.remove(seleccionado);
        adapter.notifyItemRemoved(seleccionado);
    }

    public void consultarTodo() {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "animals_db", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery(
                "select nombre, habitad, photoId from animal", null);
        if (fila.moveToFirst()) {
            do {
                animals.add(new Animal(fila.getString(0), fila.getString(1), Integer.parseInt(fila.getString(2))));
            } while (fila.moveToNext());
        } else {
            Toast.makeText(this, "No existe ningún registro", Toast.LENGTH_SHORT).show();
            Log.d("TAG", "SQL: No existen registro");
        }
        bd.close();
    }
}
