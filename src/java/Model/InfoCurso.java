/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author javier.tambo
 */
public class InfoCurso {
    
    
    public myResponse response=new myResponse();    
    public int id;
    public String NombreCurso;
    public int Valor;
    public int Publico;
    public String Periodo;
    public String Inicio;
    public String Fin;
    public int Horas;
    public int CupoMax;
    public String Lugar;
    public String LunesIni;
    public String LunesFin;
    public String MartesIni;
    public String MartesFin;
    public String MiercolesIni;
    public String MiercolesFin;
    public String JuevesIni;
    public String JuevesFin;
    public String ViernesIni;
    public String ViernesFin;
    public String SabadoIni;
    public String SabadoFin;
    public String DomingoIni;
    public String DomingoFin;
    public int Linea;
    public int Estado;
    public int Modalidad;
    public int Unidad;
    public String Profesor;
    public String SegundoPro;
    public String ProExterno;
    public String Descripcion;
    public boolean est;
    public int IdTipoCurso;
    public int Disponible;
    
    public InfoCurso(){}
    
    public InfoCurso(int id,String NombreCurso,int Valor,int Publico,String Periodo,String Inicio,String Fin,
            int Horas,int CupoMax,String Lugar,String LunesIni,String LunesFin,String MartesIni,String MartesFin,
     String MiercolesIni,String MiercolesFin,String JuevesIni,String JuevesFin,String ViernesIni,String ViernesFin,
     String SabadoIni,String SabadoFin,String DomingoIni,String DomingoFin,int Linea,
     int Estado,int Modalidad,int Unidad,String Profesor, String SegundoPro,String ProExterno,String Descripcion,boolean est,
            int IdTipoCurso, int Disponible){
    this.id=id;
this.NombreCurso=NombreCurso;
this.Valor=Valor;
this.Publico=Publico;
this.Periodo=Periodo;
this.Inicio=Inicio;
this.Fin=Fin;
this.Horas=Horas;
this.CupoMax=CupoMax;
this.Lugar=Lugar;
this.LunesIni=LunesIni;
this.LunesFin=LunesFin;
this.MartesIni=MartesIni;
this.MartesFin=MartesFin;
this.MiercolesIni=MiercolesIni;
this.MiercolesFin=MiercolesFin;
this.JuevesIni=JuevesIni;
this.JuevesFin=JuevesFin;
this.ViernesIni=ViernesIni;
this.ViernesFin=ViernesFin;
this.SabadoIni=SabadoIni;
this.SabadoFin=SabadoFin;
this.DomingoIni=DomingoIni;
this.DomingoFin=DomingoFin;
this.Linea=Linea;
this.Estado=Estado;
this.Modalidad=Modalidad;
this.Unidad=Unidad;
this.Profesor=Profesor;
this.SegundoPro=SegundoPro;
this.ProExterno=ProExterno;
this.Descripcion=Descripcion;
this.est=est;
this.IdTipoCurso=IdTipoCurso;
this.Disponible=Disponible;
    }
    
    
}
