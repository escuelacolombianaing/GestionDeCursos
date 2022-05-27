/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author javier.tambo
 */
public class InfoListInscritos {
    
    private myResponse myresResponse=new myResponse();
    private InfoCurso curso=new InfoCurso();
    private InfoInscripcion inscrito=new InfoInscripcion();
    private NotaDeCurso nota=new NotaDeCurso();
    
    public NotaDeCurso getNota() {
        return nota;
    }

    public void setNota(NotaDeCurso nota) {
        this.nota = nota;
    }
    
    public myResponse getMyresResponse() {
        return myresResponse;
    }

    public void setMyresResponse(myResponse myresResponse) {
        this.myresResponse = myresResponse;
    }  
    
    public InfoCurso getCurso() {
        return curso;
    }

    public InfoInscripcion getInscrito() {
        return inscrito;
    }

    public void setCurso(InfoCurso curso) {
        this.curso = curso;
    }

    public void setInscrito(InfoInscripcion inscrito) {
        this.inscrito = inscrito;
    }
     
}
