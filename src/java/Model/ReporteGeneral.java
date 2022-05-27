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
public class ReporteGeneral {

    public myResponse _myresResponse = new myResponse();
    public List<DatosReporte> Datos= new ArrayList<DatosReporte>();
    
    public static class DatosReporte{
    private int id;
    private String NombreCurso;
    private int valor;
    private String publico;
    private String periodo;
    private String inicio;
    private String fin;
    private int horas;
    private int cupoMax;
    private String lugar;
    private String Dias;
    private String linea;
    private String estado;
    private String modalidad;
    private String unidad;
    private String profesor;
    private String segundoProfesor;
    private String proExterno;
    private String descripcion;
    private String tipoCurso;
    private String docInscr;
    private String estadoIns;
    private String fecreg;
    private String nombreCompletoIns;
    private String nota;
    private String idRegistro;
    private String fechaRegistro;

    public int getId() {
        return id;
    }

    public String getNombreCurso() {
        return NombreCurso;
    }

    public int getValor() {
        return valor;
    }

    public String getPublico() {
        return publico;
    }

    public String getPeriodo() {
        return periodo;
    }

    public String getInicio() {
        return inicio;
    }

    public String getFin() {
        return fin;
    }

    public int getHoras() {
        return horas;
    }

    public int getCupoMax() {
        return cupoMax;
    }

    public String getLugar() {
        return lugar;
    }

    public String getDesde() {
        return Dias;
    }

    public String getLinea() {
        return linea;
    }

    public String getEstado() {
        return estado;
    }

    public String getModalidad() {
        return modalidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public String getProfesor() {
        return profesor;
    }

    public String getSegundoProfesor() {
        return segundoProfesor;
    }

    public String getProExterno() {
        return proExterno;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipoCurso() {
        return tipoCurso;
    }

    public String getDocInscr() {
        return docInscr;
    }

    public String getEstadoIns() {
        return estadoIns;
    }

    public String getFecreg() {
        return fecreg;
    }

    public String getNombreCompletoIns() {
        return nombreCompletoIns;
    }

    public String getNota() {
        return nota;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombreCurso(String NombreCurso) {
        this.NombreCurso = NombreCurso;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setPublico(String publico) {
        this.publico = publico;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public void setCupoMax(int cupoMax) {
        this.cupoMax = cupoMax;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setDias(String Dias) {
        this.Dias = Dias;
    }
    
    public void setLinea(String linea) {
        this.linea = linea;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public void setSegundoProfesor(String segundoProfesor) {
        this.segundoProfesor = segundoProfesor;
    }

    public void setProExterno(String proExterno) {
        this.proExterno = proExterno;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTipoCurso(String tipoCurso) {
        this.tipoCurso = tipoCurso;
    }

    public void setDocInscr(String docInscr) {
        this.docInscr = docInscr;
    }

    public void setEstadoIns(String estadoIns) {
        this.estadoIns = estadoIns;
    }

    public void setFecreg(String fecreg) {
        this.fecreg = fecreg;
    }

    public void setNombreCompletoIns(String nombreCompletoIns) {
        this.nombreCompletoIns = nombreCompletoIns;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    }    
}
