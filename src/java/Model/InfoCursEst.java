/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author javier.tambo
 */
public class InfoCursEst {
   private InfoCurso infocurso=new InfoCurso();
   private NotaDeCurso notacurso=new NotaDeCurso();
   private myResponse myresponse=new myResponse();

    public myResponse getMyresponse() {
        return myresponse;
    }

    public void setMyresponse(myResponse myresponse) {
        this.myresponse = myresponse;
    }
   

    public InfoCurso getInfocurso() {
        return infocurso;
    }

    public NotaDeCurso getNotacurso() {
        return notacurso;
    }

    public void setInfocurso(InfoCurso infocurso) {
        this.infocurso = infocurso;
    }

    public void setNotacurso(NotaDeCurso notacurso) {
        this.notacurso = notacurso;
    }
   
   
}
