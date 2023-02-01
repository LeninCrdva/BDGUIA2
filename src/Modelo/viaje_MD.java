/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author VICO5
 */
public class viaje_MD {
    private int via;
    private int ca;
    private int cam;
    private int pro;
    private String fecha_conduccion;
    private String fecha_llegada;

    public viaje_MD() {
    }

    public viaje_MD(int via, int ca, int cam, int pro, String fecha_conduccion, String fecha_llegada) {
        this.via = via;
        this.ca = ca;
        this.cam = cam;
        this.pro = pro;
        this.fecha_conduccion = fecha_conduccion;
        this.fecha_llegada = fecha_llegada;
    }

    public int getVia() {
        return via;
    }

    public void setVia(int via) {
        this.via = via;
    }

    public int getCa() {
        return ca;
    }

    public void setCa(int ca) {
        this.ca = ca;
    }

    public int getCam() {
        return cam;
    }

    public void setCam(int cam) {
        this.cam = cam;
    }

    public int getPro() {
        return pro;
    }

    public void setPro(int pro) {
        this.pro = pro;
    }

    public String getFecha_conduccion() {
        return fecha_conduccion;
    }

    public void setFecha_conduccion(String fecha_conduccion) {
        this.fecha_conduccion = fecha_conduccion;
    }

    public String getFecha_llegada() {
        return fecha_llegada;
    }

    public void setFecha_llegada(String fecha_llegada) {
        this.fecha_llegada = fecha_llegada;
    }
    
    
}
