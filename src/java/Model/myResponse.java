/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author javier.tambo
 */
public class myResponse {
    
    private String error;
    private String message;
    private boolean transaccion;
    private boolean informacion;

    public boolean isInformacion() {
        return informacion;
    }

    public void setInformacion(boolean informacion) {
        this.informacion = informacion;
    }
    
    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public boolean isTransaccion() {
        return transaccion;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTransaccion(boolean transaccion) {
        this.transaccion = transaccion;
    }
    
   
    
}
