/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;

/**
 *
 * @author javier.tambo
 */
public class FiltrosDinamicos {
    

        private String estado ="null";
        private String tipoCurso="null";
        private String publico="null";
        private String unidad="null";
        private String NombreEstu="";
        private String Profesor="null";
        private String SegProfesor="null";
        private String NombreCurso="null";

    public String getNombreCurso() {
        return NombreCurso;
    }

    public void setNombreCurso(String NombreCurso) {
        this.NombreCurso = NombreCurso;
    }
        
    public String getProfesor() {
        return Profesor;
    }

    public void setProfesor(String Profesor) {
        this.Profesor = Profesor;
    }

    public void setSegProfesor(String SegProfesor) {
        this.SegProfesor = SegProfesor;
    }

    public String getSegProfesor() {
        return SegProfesor;
    }

    public void setNombreEstu(String NombreEstu) {
        this.NombreEstu = NombreEstu;
    }

    public String getNombreEstu() {
        return NombreEstu;
    }


        public String getEstado() {
            return estado;
        }

        public String getTipoCurso() {
            return tipoCurso;
        }

        public String getPublico() {
            return publico;
        }

        public String getUnidad() {
            return unidad;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public void setTipoCurso(String tipoCurso) {
            this.tipoCurso = tipoCurso;
        }

        public void setPublico(String publico) {
            this.publico = publico;
        }

        public void setUnidad(String unidad) {
            this.unidad = unidad;
        }
        
        

    
    
    
}
