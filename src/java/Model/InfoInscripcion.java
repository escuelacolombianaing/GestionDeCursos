/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author javier.tambo
 */
public class InfoInscripcion {
    
    private int id;
    private int idCur;
    private int docInscr;
    private boolean est;
    private String fecreg;
    private String nombre;

    public int getId() {
        return id;
    }

    public int getIdCur() {
        return idCur;
    }

    public int getDocInscr() {
        return docInscr;
    }

    public boolean getEst() {
        return est;
    }

    public String getFecreg() {
        return fecreg;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdCur(int idCur) {
        this.idCur = idCur;
    }

    public void setDocInscr(int docInscr) {
        this.docInscr = docInscr;
    }

    public void setEst(boolean est) {
        this.est = est;
    }

    public void setFecreg(String fecreg) {
        this.fecreg = fecreg;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
