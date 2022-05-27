
package Model;


public class NotaDeCurso {
    
    private int nota;
    private int idCurso;
    private int idInscrito;
    private int id;
    private int idRegistro;
    private String FechaRegistro;

    public String getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(String FechaRegistro) {
        this.FechaRegistro = FechaRegistro;
    }   
    
    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getId() {
        return id;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }
   
    public int getNota() {
        return nota;
    }

    public int getIdInscrito() {
        return idInscrito;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public void setIdInscrito(int idInscrito) {
        this.idInscrito = idInscrito;
    }
    
    
    
    
}
