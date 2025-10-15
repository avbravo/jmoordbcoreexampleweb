/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fish.payara.model;

/**
 *
 * @author avbravo
 */


public class Producto {
    private String idproducto;
    private String producto; // Nombre del producto (será la etiqueta X)
    private Double precio;
    private Integer cantidad; // Cantidad vendida (será el valor Y)

    public Producto(String idproducto, String producto, Double precio, Integer cantidad) {
        this.idproducto = idproducto;
        this.producto = producto;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public String getIdproducto() { return idproducto; }
    public void setIdproducto(String idproducto) { this.idproducto = idproducto; }
    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}