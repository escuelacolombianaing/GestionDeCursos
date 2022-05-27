/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author javier.tambo
 */
public class InfoUsuario {
    
    private String nombre;
    private int id_usu;
    private int rol;

    public String getNombre() {
        return nombre;
    }

    public int getId_usu() {
        return id_usu;
    }

    public int getRol() {
        return rol;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId_usu(int id_usu) {
        this.id_usu = id_usu;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }
    
    
    
}
