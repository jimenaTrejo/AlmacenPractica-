package model;

public class Ventas {
    private int idVent;
    private int idProducto;
    private int cantidad; 


    public Ventas(){}

    public Ventas (int idVent, int idProducto, int cantidad){
        this.idVent=idVent;
        this.idProducto=idProducto;
        this.cantidad=cantidad;
    }


    public int getIdVent(){
        return this.idVent;
    }

    public void setIdVent(int idVent){
        this.idVent=idVent;
    }
    public int getidProducto(){
        return this.idProducto;
    }

    public void setidProducto(int idProducto){
        this.idProducto=idProducto;
    }

    public int getcantidad(){
        return this.cantidad;
    }

    public void setcantidad(int cantidad){
        this.cantidad=cantidad;
    }
}
