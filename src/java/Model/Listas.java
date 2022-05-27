/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author javier.tambo
 */
public class Listas {
    
       
       private int Value;
       private String Especificacion;
       private int tipo;
       private int DependenciaTipo;
    
        public Listas(int Value,String Especificaciones, int tipo, int DependenciaTipo){
            this.Value=Value;
            this.Especificacion=Especificaciones;
            this.tipo=tipo;
            this.DependenciaTipo=DependenciaTipo;
        }
}
