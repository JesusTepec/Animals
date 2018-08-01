package com.example.hdsolujtepec.animals.Model;

public class Animal {

    private String nombre;
    private String habitad;
    private int photo;

    public Animal(String _nombre, String _habitad, int _photo){
        nombre = _nombre;
        habitad = _habitad;
        photo = _photo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHabitad() {
        return habitad;
    }

    public int getPhoto() {
        return photo;
    }
}
