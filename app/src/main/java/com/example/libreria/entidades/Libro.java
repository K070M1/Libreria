package com.example.libreria.entidades;

public class Libro {

    private int idlibro;
    private String titulo;
    private String autor;
    private String editorial;
    private int añopublicacion;
    private String lugarpublicacion;
    private int npaginas;
    private double precio;

    public int getIdlibro() {
        return idlibro;
    }

    public void setIdlibro(int idlibro) {
        this.idlibro = idlibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        editorial = editorial;
    }

    public int getAñopublicacion() {
        return añopublicacion;
    }

    public void setAñopublicacion(int añopublicacion) {
        this.añopublicacion = añopublicacion;
    }

    public String getLugarpublicacion() {
        return lugarpublicacion;
    }

    public void setLugarpublicacion(String lugarpublicacion) {
        this.lugarpublicacion = lugarpublicacion;
    }

    public int getNpaginas() {
        return npaginas;
    }

    public void setNpaginas(int npaginas) {
        this.npaginas = npaginas;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    //CONSTRUCTOR
    public Libro() {
    }

    public Libro(int idlibro, String titulo, String autor, String editorial, int añopublicacion, String lugarpublicacion, int npaginas, double precio) {
        this.idlibro = idlibro;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.añopublicacion = añopublicacion;
        this.lugarpublicacion = lugarpublicacion;
        this.npaginas = npaginas;
        this.precio = precio;
    }
}
