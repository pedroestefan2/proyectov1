/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectov1.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pedro
 */
public class Usuario {

    private int id;
    private String nombreUsuario;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String correo;
    private String contraseña;
    private String imagen;

    //monedas ficticias
    private int oro;
    private int hierro;
    private int suministros;

    //lista de barcos y lista en su mazo
    ArrayList<Barco> listabarcos = new ArrayList<>();
    ArrayList<Barco> listabarcosMazo = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(String nombreusuario, String nombre, String apellidos, String telefono, String correo, String contraseña) {
        //id autgenera mysql
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
        this.contraseña = contraseña;// cofificar contraseña futuro con BCrypt
    }

    public Usuario(int id, String nombreUsuario, String nombre, String apellidos, String telefono, String correo, String contraseña, String imagen, int oro, int hierro, int suministros) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
        this.contraseña = contraseña;
        this.imagen = imagen;
        this.oro = oro;
        this.hierro = hierro;
        this.suministros = suministros;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    public int getHierro() {
        return hierro;
    }

    public void setHierro(int hierro) {
        this.hierro = hierro;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getSuministros() {
        return suministros;
    }

    public void setSuministros(int suministros) {
        this.suministros = suministros;
    }

    //barcos añadir quitar y lime de 3 barcos por usuario
    public ArrayList<Barco> getListabarcos() {
        return listabarcos;
    }

    public void setListabarcos(ArrayList<Barco> listabarcos) {

        this.listabarcos = listabarcos;

    }

    public void eliminarBarco(Barco barco) {
        for (Barco b : this.listabarcos) {
            if (b.getId() == barco.getId()) {
                this.listabarcos.remove(b);
            }
        }
    }

    public void añadirBarco(Barco barco) {
        this.listabarcos.add(barco);
    }

    public ArrayList<Barco> getListabarcosMazo() {
        return listabarcosMazo;
    }

    public void setListabarcosMazo(ArrayList<Barco> listabarcosMazo) {

        this.listabarcos = listabarcos;

    }

    public void eliminarBarcoMazo(Barco barco) {
        for (Barco b : this.listabarcos) {
            if (b.getId() == barco.getId()) {
                this.listabarcos.remove(b);
            }
        }
    }

    public void añadirBarcoMazo(Barco barco) {
        if (listabarcos.size() < 3) {
            this.listabarcos.add(barco);
        }
    }

}
