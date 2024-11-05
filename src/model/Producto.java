package model;

public class Producto {

    private int idProducto;
    private String nombre;
    private double precio;
    private int cantidad; 

    public Producto(){}

    public Producto(int idProducto, String nombre, double precio, int cantidad){
        this.idProducto=idProducto;
        this.nombre=nombre;
        this.precio=precio;
        this.cantidad=cantidad;
    }

    public int getidProducto(){
        return this.idProducto;
    }

    public void setidProducto(int idProducto){
        this.idProducto=idProducto;
    }

    public String getnombre(){
        return this.nombre;
    }

    public void setnombre(String nombre){
        this.nombre=nombre;
    }




    public double getprecio(){
        return this.precio;
    }

    public void setprecio(double precio){
        this.precio=precio;
    }


    public int getCantidad(){
        return this.cantidad;
    }

    public void setCantidad(int cantidad){
        this.cantidad=cantidad;
    }
}
