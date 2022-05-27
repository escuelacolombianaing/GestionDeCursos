/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionBD;

import Controllers.NuevaInscripcion;
import java.util.Vector;
import Model.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.StyledEditorKit;

/**
 *
 * @author javier.tambo
 */
public class Componentes extends Configuracion {

    String consulta;
    myResponse _myResponse;

    public myResponse InsertNuevoCurso(InfoCurso nuevoCurso) {
        _myResponse = new myResponse();
        try {

            consulta = new String("insert into gescur.Cursos values("
                    + "'" + nuevoCurso.NombreCurso + "'"
                    + ",'" + nuevoCurso.Valor + "'"
                    + ",'" + nuevoCurso.Publico + "'"
                    + ",'" + nuevoCurso.Periodo + "'"
                    + ",'" + nuevoCurso.Inicio + "'"
                    + ",'" + nuevoCurso.Fin + "'"
                    + ",'" + nuevoCurso.Horas + "'"
                    + ",'" + nuevoCurso.CupoMax + "'"
                    + ",'" + nuevoCurso.Lugar + "'"
                    + ",'" + nuevoCurso.LunesIni + "'"
                    + ",'" + nuevoCurso.LunesFin + "'"
                    + ",'" + nuevoCurso.MartesIni + "'"
                    + ",'" + nuevoCurso.MartesFin + "'"
                    + ",'" + nuevoCurso.MiercolesIni + "'"
                    + ",'" + nuevoCurso.MiercolesFin + "'"
                    + ",'" + nuevoCurso.JuevesIni + "'"
                    + ",'" + nuevoCurso.JuevesFin + "'"
                    + ",'" + nuevoCurso.ViernesIni + "'"
                    + ",'" + nuevoCurso.ViernesFin + "'"
                    + ",'" + nuevoCurso.SabadoIni + "'"
                    + ",'" + nuevoCurso.SabadoFin + "'"
                    + ",'" + nuevoCurso.DomingoIni + "'"
                    + ",'" + nuevoCurso.DomingoFin + "'"
                    + ",'" + nuevoCurso.Linea + "'"
                    + ",'" + nuevoCurso.Estado + "'"
                    + ",'" + nuevoCurso.Modalidad + "'"
                    + ",'" + nuevoCurso.Unidad + "'"
                    + ",'" + nuevoCurso.Profesor + "'"
                    + ",'" + nuevoCurso.SegundoPro + "'"
                    + ",'" + nuevoCurso.ProExterno + "'"
                    + ",'" + nuevoCurso.Descripcion + "'"
                    + ",'" + nuevoCurso.IdTipoCurso + "'"
                    + ")");

            conectarBD();
            int respuesta = actualizar(consulta);
            desconectarBD();
            if (respuesta == 0) {
                _myResponse.setTransaccion(false);
                _myResponse.setError("¡Error al crear curso!");
            } else {
                _myResponse.setTransaccion(true);
                _myResponse.setMessage("¡Curso creado correctamente!");
            }


        } catch (Exception e) {
            _myResponse.setTransaccion(false);
            _myResponse.setError(e.getMessage());
        }

        return _myResponse;

    }

    public myResponse InsertNuevaInscripcion(InfoInscripcion nuevaInscripcion) {


        _myResponse = new myResponse();
        try {
            if (ValidarExistencia(nuevaInscripcion.getDocInscr(), nuevaInscripcion.getIdCur(), "gescur.Inscripciones")) {
                consulta = new String("update gescur.Inscripciones set est='" + nuevaInscripcion.getEst() + "' where idcur='" + nuevaInscripcion.getIdCur() + "' and docInscr='" + nuevaInscripcion.getDocInscr() + "'");
                _myResponse.setTransaccion(true);
                conectarBD();
                int respuesta = actualizar(consulta);
                desconectarBD();



            } else {
                consulta = new String("insert into gescur.Inscripciones values("
                        + "'" + nuevaInscripcion.getIdCur() + "'"
                        + ",'" + nuevaInscripcion.getDocInscr() + "'"
                        + ",'" + nuevaInscripcion.getEst() + "'"
                        + ",getdate()"
                        + ")");
                _myResponse.setTransaccion(true);
                conectarBD();
                int respuesta = actualizar(consulta);
                desconectarBD();
            }
        } catch (Exception e) {
            _myResponse.setTransaccion(false);
            _myResponse.setError(e.getMessage());
        }

        return _myResponse;

    }

    public boolean ValidarExistencia(int docInscr, int idcurso, String table) {
        Vector retorno = new Vector();
        String consulta = new String();
        consulta = new String("select 1 from " + table + " where idCur=" + idcurso + " and docInscr=" + docInscr);
        try {
            conectarBD();
            retorno = consultar(consulta, 1, 1);
            for (int ii = 0; ii < retorno.size(); ii++) {

                Vector infusu = new Vector();
                infusu = (Vector) retorno.elementAt(ii);
                if (Integer.parseInt(infusu.elementAt(0).toString()) == 1) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (Exception ex) {
        } finally {
            desconectarBD();
        }
        return false;
    }

    public myResponse UpdateInfoCurso(InfoCurso Curso) {
        _myResponse = new myResponse();
        try {

            consulta = new String("update gescur.Cursos\n"
                    + "set NombreCurso='" + Curso.NombreCurso + "',Valor='" + Curso.Valor + "',Publico='" + Curso.Publico + "',Periodo='" + Curso.Periodo + "',\n"
                    + "Inicio='" + Curso.Inicio + "',Fin='" + Curso.Fin + "',Horas='" + Curso.Horas + "',CupoMax='" + Curso.CupoMax + "',Lugar='" + Curso.Lugar + "',\n"
                    + "LunesIni='" + Curso.LunesIni + "',LunesFin='" + Curso.LunesFin + "',MartesIni='" + Curso.MartesIni + "',MartesFin='" + Curso.MartesFin + "',\n"
                    + "MiercolesIni='" + Curso.MiercolesIni + "',MiercolesFin='" + Curso.MiercolesFin + "',JuevesIni='" + Curso.JuevesIni + "',JuevesFin='" + Curso.JuevesFin + "',\n"
                    + "ViernesIni='" + Curso.ViernesIni + "',ViernesFin='" + Curso.ViernesFin + "',SabadoIni='" + Curso.SabadoIni + "',SabadoFin='" + Curso.SabadoFin + "',\n"
                    +"DomingoIni='" + Curso.DomingoIni+"',DomingoFin='" + Curso.DomingoFin + "',Linea='" + Curso.Linea + "',\n"
                    + "Estado='" + Curso.Estado + "',Modalidad='" + Curso.Modalidad + "',Unidad='" + Curso.Unidad + "',Profesor='" + Curso.Profesor + "',SegundoPro='" + Curso.SegundoPro + "',\n"
                    + "ProExterno='" + Curso.ProExterno + "',Descripcion='" + Curso.Descripcion + "',IdTipoCurso='" + Curso.IdTipoCurso + "'where id='" + Curso.id + "'");

            conectarBD();
            int respuesta = actualizar(consulta);
            desconectarBD();
            _myResponse.setTransaccion(true);
            _myResponse.setMessage("¡Información del curso actualizada correctamente!");
        } catch (Exception e) {
            _myResponse.setTransaccion(false);
            _myResponse.setError(e.getMessage());
        }

        return _myResponse;

    }

    public InfoCurso InformacionCurso(InfoCurso Curso) {


        Vector retorno = new Vector();
        String consulta = new String();
        consulta = new String("select * from gescur.Cursos where id=" + Curso.id);
        try {
            conectarBD();
            retorno = consultar(consulta, 33, 1);
            Curso.response.setInformacion(false);
            for (int ii = 0; ii < retorno.size(); ii++) {
                Curso.response.setInformacion(true);
                Vector infusu = new Vector();
                infusu = (Vector) retorno.elementAt(ii);
                Curso.NombreCurso = infusu.elementAt(1).toString();
                Curso.Valor = Integer.parseInt(infusu.elementAt(2).toString());
                Curso.Publico = Integer.parseInt(infusu.elementAt(3).toString());
                Curso.Periodo = infusu.elementAt(4).toString();
                Curso.Inicio = infusu.elementAt(5).toString();
                Curso.Fin = infusu.elementAt(6).toString();
                Curso.Horas = Integer.parseInt(infusu.elementAt(7).toString());
                Curso.CupoMax = Integer.parseInt(infusu.elementAt(8).toString());
                Curso.Lugar = infusu.elementAt(9).toString();
                Curso.LunesIni = infusu.elementAt(10).toString();
                Curso.LunesFin = infusu.elementAt(11).toString();
                Curso.MartesIni = infusu.elementAt(12).toString();
                Curso.MartesFin = infusu.elementAt(13).toString();
                Curso.MiercolesIni = infusu.elementAt(14).toString();
                Curso.MiercolesFin = infusu.elementAt(15).toString();
                Curso.JuevesIni = infusu.elementAt(16).toString();
                Curso.JuevesFin = infusu.elementAt(17).toString();
                Curso.ViernesIni = infusu.elementAt(18).toString();
                Curso.ViernesFin = infusu.elementAt(19).toString();
                Curso.SabadoIni = infusu.elementAt(20).toString();
                Curso.SabadoFin = infusu.elementAt(21).toString();
                Curso.DomingoIni = infusu.elementAt(22).toString();
                Curso.DomingoFin = infusu.elementAt(23).toString();
                Curso.Linea = Integer.parseInt(infusu.elementAt(24).toString());
                Curso.Estado = Integer.parseInt(infusu.elementAt(25).toString());
                Curso.Modalidad = Integer.parseInt(infusu.elementAt(26).toString());
                Curso.Unidad = Integer.parseInt(infusu.elementAt(27).toString());
                Curso.Profesor = infusu.elementAt(28).toString();
                Curso.SegundoPro = infusu.elementAt(29).toString();
                Curso.ProExterno = infusu.elementAt(30).toString();
                Curso.Descripcion = infusu.elementAt(31).toString();
                Curso.IdTipoCurso = Integer.parseInt(infusu.elementAt(32).toString());
            }

            Curso.response.setTransaccion(true);
        } catch (Exception ex) {
            Curso.response.setError(ex.getMessage());
            Curso.response.setTransaccion(false);
        }
        desconectarBD();
        return Curso;

    }

    public List<InfoCursEst> InformacionAllCursos(int id) {

        List<InfoCursEst> listacursos = new ArrayList<InfoCursEst>();
        InfoCursEst infoCursos;
        Vector retorno = new Vector();
        String consulta = new String();
        consulta = new String("SELECT distinct Cur.id,Cur.NombreCurso,Cur.Valor,Cur.Publico,Cur.Periodo,Cur.Inicio,\n" +
"Cur.Fin,Cur.Horas,Cur.CupoMax,Cur.Lugar,cur.lunesini,cur.lunesfin,cur.martesini,cur.martesfin,cur.MiercolesIni\n" +
",cur.Miercolesfin,cur.JuevesIni,cur.JuevesFin,cur.ViernesIni,cur.ViernesFin,cur.SabadoIni,cur.SabadoFin\n" +
",cur.DomingoIni,cur.DomingoFin,Cur.Linea,Cur.Estado,Modalidad,Cur.Unidad,\n" +
"(case when Cur.Profesor=25 then ''else emp.ap1_emp+' '+emp.ap2_emp+' '+emp.nom_emp end),\n" +
"(case when Cur.SegundoPro=25 then ''else emp2.ap1_emp+' '+emp2.ap2_emp+' '+emp2.nom_emp end),\n" +
"Cur.ProExterno,Cur.Descripcion,isnull((case Ins.est when 1 then 'true' else 'false' end),\n" +
"convert(bit,0))as Inscrito,isnull(Cur.IdTipoCurso,0)as tipocurso,isnull((case when Cupos.Can >= cur.CupoMax then 0 else 1 end ),1)as Disponibilidad,isnull(nota.Nota,0)\n" +
"FROM gescur.cursos Cur\n" +
"left join gescur.Inscripciones Ins ON Ins.idCur=Cur.id and Ins.docInscr="+id+" \n" +
"left join (select idCur,count(docInscr)as Can from gescur.Inscripciones where est=1 group by idcur)as Cupos on Cur.id=Cupos.idCur\n" +
"left join gescur.Notas nota on nota.idInscrito=Ins.docInscr and nota.idCurso=Cur.id \n" +
"left join Novasoft.dbo.rhh_emplea emp on convert(varchar(15),emp.cod_emp)=convert(varchar(15),Cur.Profesor)\n" +
"left join Novasoft.dbo.rhh_emplea emp2 on convert(varchar(15),emp2.cod_emp)=convert(varchar(15),Cur.SegundoPro)\n" +
"where Estado=15 and convert(date,cur.fin) >= convert(date,getdate()) order by 1 desc");
        myResponse myresponse = new myResponse();
        try {
            conectarBD();
            retorno = consultar(consulta, 36, 1);


            for (int ii = 0; ii < retorno.size(); ii++) {
                infoCursos = new InfoCursEst();
                myresponse.setTransaccion(true);
                infoCursos.setMyresponse(myresponse);
                Vector infusu = new Vector();
                infusu = (Vector) retorno.elementAt(ii);
                InfoCurso nuevocurso = new InfoCurso(
                        Integer.parseInt(infusu.elementAt(0).toString()),
                        infusu.elementAt(1).toString(),
                        Integer.parseInt(infusu.elementAt(2).toString()),
                        Integer.parseInt(infusu.elementAt(3).toString()),
                        infusu.elementAt(4).toString(),
                        infusu.elementAt(5).toString(),
                        infusu.elementAt(6).toString(),
                        Integer.parseInt(infusu.elementAt(7).toString()),
                        Integer.parseInt(infusu.elementAt(8).toString()),
                        infusu.elementAt(9).toString(),
                        infusu.elementAt(10).toString(),
                        infusu.elementAt(11).toString(),
                        infusu.elementAt(12).toString(),
                        infusu.elementAt(13).toString(),
                        infusu.elementAt(14).toString(),
                        infusu.elementAt(15).toString(),
                        infusu.elementAt(16).toString(),
                        infusu.elementAt(17).toString(),
                        infusu.elementAt(18).toString(),
                        infusu.elementAt(19).toString(),
                        infusu.elementAt(20).toString(),
                        infusu.elementAt(21).toString(),
                        infusu.elementAt(22).toString(),
                        infusu.elementAt(23).toString(),
                        Integer.parseInt(infusu.elementAt(24).toString()),
                        Integer.parseInt(infusu.elementAt(25).toString()),
                        Integer.parseInt(infusu.elementAt(26).toString()),
                        Integer.parseInt(infusu.elementAt(27).toString()),
                        infusu.elementAt(28).toString(),
                        infusu.elementAt(29).toString(),
                        infusu.elementAt(30).toString(),
                        infusu.elementAt(31).toString(),
                        Boolean.parseBoolean(infusu.elementAt(32).toString()),
                        Integer.parseInt(infusu.elementAt(33).toString()),
                        Integer.parseInt(infusu.elementAt(34).toString()));
                infoCursos.setInfocurso(nuevocurso);
                NotaDeCurso nuevanota = new NotaDeCurso();
                nuevanota.setNota(Integer.parseInt(infusu.elementAt(35).toString()));
                infoCursos.setNotacurso(nuevanota);

                listacursos.add(infoCursos);

            }

        } catch (Exception ex) {
            infoCursos = new InfoCursEst();
            myresponse.setError(ex.getMessage());
            myresponse.setInformacion(false);
            infoCursos.setMyresponse(myresponse);
            listacursos.add(infoCursos);
        }
        desconectarBD();
        return listacursos;

    
    }
    
    public List<InfoCursEst> InformacionAllCursosCert(int id) {

        List<InfoCursEst> listacursos = new ArrayList<InfoCursEst>();
        InfoCursEst infoCursos;
        Vector retorno = new Vector();
        String consulta = new String();
        consulta = new String("SELECT distinct Cur.id,Cur.NombreCurso,Cur.Valor,Cur.Publico,Cur.Periodo,Cur.Inicio,\n" +
"Cur.Fin,Cur.Horas,Cur.CupoMax,Cur.Lugar,cur.lunesini,cur.lunesfin,cur.martesini,cur.martesfin,cur.MiercolesIni\n" +
",cur.Miercolesfin,cur.JuevesIni,cur.JuevesFin,cur.ViernesIni,cur.ViernesFin,cur.SabadoIni,cur.SabadoFin\n" +
",cur.DomingoIni,cur.DomingoFin,Cur.Linea,Cur.Estado,Modalidad,Cur.Unidad,\n" +
"(case when Cur.Profesor=25 then ''else emp.ap1_emp+' '+emp.ap2_emp+' '+emp.nom_emp end),\n" +
"(case when Cur.SegundoPro=25 then ''else emp2.ap1_emp+' '+emp2.ap2_emp+' '+emp2.nom_emp end),\n" +
"Cur.ProExterno,Cur.Descripcion,isnull((case Ins.est when 1 then 'true' else 'false' end),\n" +
"convert(bit,0))as Inscrito,isnull(Cur.IdTipoCurso,0)as tipocurso,isnull((case when Cupos.Can >= cur.CupoMax then 0 else 1 end ),1)as Disponibilidad,isnull(nota.Nota,0)\n" +
"FROM gescur.cursos Cur\n" +
"left join gescur.Inscripciones Ins ON Ins.idCur=Cur.id and Ins.docInscr="+id+" \n" +
"left join (select idCur,count(docInscr)as Can from gescur.Inscripciones where est=1 group by idcur)as Cupos on Cur.id=Cupos.idCur\n" +
"left join gescur.Notas nota on nota.idInscrito=Ins.docInscr and nota.idCurso=Cur.id \n" +
"left join Novasoft.dbo.rhh_emplea emp on convert(varchar(15),emp.cod_emp)=convert(varchar(15),Cur.Profesor)\n" +
"left join Novasoft.dbo.rhh_emplea emp2 on convert(varchar(15),emp2.cod_emp)=convert(varchar(15),Cur.SegundoPro)\n" +
"where Estado=15 order by 1 desc");
        myResponse myresponse = new myResponse();
        try {
            conectarBD();
            retorno = consultar(consulta, 36, 1);


            for (int ii = 0; ii < retorno.size(); ii++) {
                infoCursos = new InfoCursEst();
                myresponse.setTransaccion(true);
                infoCursos.setMyresponse(myresponse);
                Vector infusu = new Vector();
                infusu = (Vector) retorno.elementAt(ii);
                InfoCurso nuevocurso = new InfoCurso(
                        Integer.parseInt(infusu.elementAt(0).toString()),
                        infusu.elementAt(1).toString(),
                        Integer.parseInt(infusu.elementAt(2).toString()),
                        Integer.parseInt(infusu.elementAt(3).toString()),
                        infusu.elementAt(4).toString(),
                        infusu.elementAt(5).toString(),
                        infusu.elementAt(6).toString(),
                        Integer.parseInt(infusu.elementAt(7).toString()),
                        Integer.parseInt(infusu.elementAt(8).toString()),
                        infusu.elementAt(9).toString(),
                        infusu.elementAt(10).toString(),
                        infusu.elementAt(11).toString(),
                        infusu.elementAt(12).toString(),
                        infusu.elementAt(13).toString(),
                        infusu.elementAt(14).toString(),
                        infusu.elementAt(15).toString(),
                        infusu.elementAt(16).toString(),
                        infusu.elementAt(17).toString(),
                        infusu.elementAt(18).toString(),
                        infusu.elementAt(19).toString(),
                        infusu.elementAt(20).toString(),
                        infusu.elementAt(21).toString(),
                        infusu.elementAt(22).toString(),
                        infusu.elementAt(23).toString(),
                        Integer.parseInt(infusu.elementAt(24).toString()),
                        Integer.parseInt(infusu.elementAt(25).toString()),
                        Integer.parseInt(infusu.elementAt(26).toString()),
                        Integer.parseInt(infusu.elementAt(27).toString()),
                        infusu.elementAt(28).toString(),
                        infusu.elementAt(29).toString(),
                        infusu.elementAt(30).toString(),
                        infusu.elementAt(31).toString(),
                        Boolean.parseBoolean(infusu.elementAt(32).toString()),
                        Integer.parseInt(infusu.elementAt(33).toString()),
                        Integer.parseInt(infusu.elementAt(34).toString()));
                infoCursos.setInfocurso(nuevocurso);
                NotaDeCurso nuevanota = new NotaDeCurso();
                nuevanota.setNota(Integer.parseInt(infusu.elementAt(35).toString()));
                infoCursos.setNotacurso(nuevanota);

                listacursos.add(infoCursos);

            }

        } catch (Exception ex) {
            infoCursos = new InfoCursEst();
            myresponse.setError(ex.getMessage());
            myresponse.setInformacion(false);
            infoCursos.setMyresponse(myresponse);
            listacursos.add(infoCursos);
        }
        desconectarBD();
        return listacursos;

    }
        
    

    public List<InfoCurso> InformacionAllCursosGes() {

        List<InfoCurso> listacursos = new ArrayList<InfoCurso>();
        InfoCurso NuevoCurso = new InfoCurso();
        Vector retorno = new Vector();
        String consulta = new String();
        consulta = new String("SELECT Cur.id,Cur.NombreCurso,Cur.Valor,Cur.Publico,Cur.Periodo,Cur.Inicio,\n" +
"Cur.Fin,Cur.Horas,Cur.CupoMax,Cur.Lugar,cur.LunesIni,cur.LunesFin,cur.MartesIni,cur.MartesFin,\n" +
"cur.MiercolesIni,cur.MiercolesFin,cur.JuevesIni,cur.JuevesFin,cur.ViernesIni,cur.ViernesFin\n" +
",cur.SabadoIni,cur.SabadoFin,cur.DomingoIni,cur.DomingoFin,Cur.Linea,Cur.Estado,Modalidad,Cur.Unidad,\n" +
"(case when Cur.Profesor=25 then ''else emp.ap1_emp+' '+emp.ap2_emp+' '+emp.nom_emp end),\n" +
"(case when Cur.SegundoPro=25 then ''else emp2.ap1_emp+' '+emp2.ap2_emp+' '+emp2.nom_emp end),\n" +
"Cur.ProExterno,Cur.Descripcion,'true'as Est,isnull(Cur.IdTipoCurso,0)as tipocurso\n" +
",1 as Disponibilidad FROM gescur.cursos Cur \n" +
"left join Novasoft.dbo.rhh_emplea emp on convert(varchar(15),emp.cod_emp)=convert(varchar(15),Cur.Profesor)\n" +
"left join Novasoft.dbo.rhh_emplea emp2 on convert(varchar(15),emp2.cod_emp)=convert(varchar(15),Cur.SegundoPro)\n" +
"order by 1 desc");
        try {
            conectarBD();
            retorno = consultar(consulta, 35, 1);
            NuevoCurso.response.setInformacion(true);
            for (int ii = 0; ii < retorno.size(); ii++) {

                Vector infusu = new Vector();
                infusu = (Vector) retorno.elementAt(ii);

                listacursos.add(new InfoCurso(
                        Integer.parseInt(infusu.elementAt(0).toString()),
                        infusu.elementAt(1).toString(),
                        Integer.parseInt(infusu.elementAt(2).toString()),
                        Integer.parseInt(infusu.elementAt(3).toString()),
                        infusu.elementAt(4).toString(),
                        infusu.elementAt(5).toString(),
                        infusu.elementAt(6).toString(),
                        Integer.parseInt(infusu.elementAt(7).toString()),
                        Integer.parseInt(infusu.elementAt(8).toString()),
                       infusu.elementAt(9).toString(),
                        infusu.elementAt(10).toString(),
                        infusu.elementAt(11).toString(),
                        infusu.elementAt(12).toString(),
                        infusu.elementAt(13).toString(),
                        infusu.elementAt(14).toString(),
                        infusu.elementAt(15).toString(),
                        infusu.elementAt(16).toString(),
                        infusu.elementAt(17).toString(),
                        infusu.elementAt(18).toString(),
                        infusu.elementAt(19).toString(),
                        infusu.elementAt(20).toString(),
                        infusu.elementAt(21).toString(),
                        infusu.elementAt(22).toString(),
                        infusu.elementAt(23).toString(),
                        Integer.parseInt(infusu.elementAt(24).toString()),
                        Integer.parseInt(infusu.elementAt(25).toString()),
                        Integer.parseInt(infusu.elementAt(26).toString()),
                        Integer.parseInt(infusu.elementAt(27).toString()),
                        infusu.elementAt(28).toString(),
                        infusu.elementAt(29).toString(),
                        infusu.elementAt(30).toString(),
                        infusu.elementAt(31).toString(),
                        Boolean.parseBoolean(infusu.elementAt(32).toString()),
                        Integer.parseInt(infusu.elementAt(33).toString()),
                        Integer.parseInt(infusu.elementAt(34).toString())));
            }

            NuevoCurso.response.setMessage("EXITO");
        } catch (Exception ex) {
            NuevoCurso.response.setInformacion(true);
            NuevoCurso.response.setError(ex.getMessage());
        }
        desconectarBD();
        return listacursos;

    }

    public List<InfoListInscritos> InformacionAllInscritos(String Num_Doc) {
        List<InfoListInscritos> listaInscritos = new ArrayList<InfoListInscritos>();
        try {


            InfoCurso curso = new InfoCurso();


            myResponse _myResponse = new myResponse();
            Vector retorno = new Vector();
            String consulta = new String();
            consulta = new String("select distinct Cur.*,Ins.*,nova.nom1_emp+' '+nova.nom2_emp+' '+nova.ap1_emp+' '+nova.ap2_emp as nombreCompleto,\n"
                    + "isnull(Nota.id,0)as id, isnull(nota.idcurso,0)as idcurso,isnull(nota.idinscrito,0)as idinscrito, isnull(nota.nota,0)as nota,isnull(nota.idregistro,0)as idregistro,isnull(nota.fecharegistro,'19000101')as fecharegistro \n"
                    + "from gescur.Inscripciones Ins\n"
                    + "inner join gescur.Cursos Cur ON Cur.id=Ins.idCur\n"
                    + "inner join Novasoft.dbo.rhh_emplea Nova ON Nova.cod_emp=convert(varchar(15),Ins.docInscr)\n"
                    + "Left join gescur.Notas Nota ON Nota.idcurso=Cur.id and Nota.IdInscrito=Ins.docInscr\n"
                    + "where Ins.est=1 and Cur.Profesor like'%" + Num_Doc + "%' or Cur.SegundoPro like'%" + Num_Doc + "%'");

            conectarBD();
            retorno = consultar(consulta, 45, 1);
            _myResponse.setInformacion(true);
            for (int ii = 0; ii < retorno.size(); ii++) {
                InfoInscripcion inscripcion = new InfoInscripcion();
                NotaDeCurso Notas = new NotaDeCurso();
                Vector infusu = new Vector();
                infusu = (Vector) retorno.elementAt(ii);
                InfoListInscritos infoInscritos = new InfoListInscritos();
                infoInscritos.setCurso(new InfoCurso(
                        Integer.parseInt(infusu.elementAt(0).toString()),
                        infusu.elementAt(1).toString(),
                        Integer.parseInt(infusu.elementAt(2).toString()),
                        Integer.parseInt(infusu.elementAt(3).toString()),
                        infusu.elementAt(4).toString(),
                        infusu.elementAt(5).toString(),
                        infusu.elementAt(6).toString(),
                        Integer.parseInt(infusu.elementAt(7).toString()),
                        Integer.parseInt(infusu.elementAt(8).toString()),
                        infusu.elementAt(9).toString(),
                       infusu.elementAt(10).toString(),
                        infusu.elementAt(11).toString(),
                        infusu.elementAt(12).toString(),
                        infusu.elementAt(13).toString(),
                        infusu.elementAt(14).toString(),
                        infusu.elementAt(15).toString(),
                        infusu.elementAt(16).toString(),
                        infusu.elementAt(17).toString(),
                        infusu.elementAt(18).toString(),
                        infusu.elementAt(19).toString(),
                        infusu.elementAt(20).toString(),
                        infusu.elementAt(21).toString(),
                        infusu.elementAt(22).toString(),
                        infusu.elementAt(23).toString(),
                        Integer.parseInt(infusu.elementAt(24).toString()),
                        Integer.parseInt(infusu.elementAt(25).toString()),
                        Integer.parseInt(infusu.elementAt(26).toString()),
                        Integer.parseInt(infusu.elementAt(27).toString()),
                        infusu.elementAt(28).toString(),
                        infusu.elementAt(29).toString(),
                        infusu.elementAt(30).toString(),
                        infusu.elementAt(31).toString(),
                        false,
                        Integer.parseInt(infusu.elementAt(32).toString()), 
                        0));

                inscripcion.setId(Integer.parseInt(infusu.elementAt(33).toString()));
                inscripcion.setIdCur(Integer.parseInt(infusu.elementAt(34).toString()));
                inscripcion.setDocInscr(Integer.parseInt(infusu.elementAt(35).toString()));
                inscripcion.setEst(Boolean.parseBoolean(infusu.elementAt(36).toString()));
                inscripcion.setFecreg(infusu.elementAt(37).toString());

                inscripcion.setNombre(infusu.elementAt(38).toString());
                Notas.setId(Integer.parseInt(infusu.elementAt(39).toString()));
                Notas.setIdCurso(Integer.parseInt(infusu.elementAt(40).toString()));
                Notas.setIdInscrito(Integer.parseInt(infusu.elementAt(41).toString()));
                Notas.setNota(Integer.parseInt(infusu.elementAt(42).toString()));
                Notas.setIdRegistro(Integer.parseInt(infusu.elementAt(43).toString()));
                Notas.setFechaRegistro(infusu.elementAt(44).toString());
                infoInscritos.setInscrito(inscripcion);
                infoInscritos.setNota(Notas);
                listaInscritos.add(infoInscritos);


            }

        } catch (Exception e) {
            _myResponse.setError("Error: " + e.getMessage());
            _myResponse.setTransaccion(false);
            _myResponse.setInformacion(false);
            InfoListInscritos infoInscritos = new InfoListInscritos();
            infoInscritos.setMyresResponse(_myResponse);
            listaInscritos.add(infoInscritos);
        }
        desconectarBD();
        return listaInscritos;
    }

    public ReporteGeneral InformacionReporteGeneral(FiltrosDinamicos Filtros) {


        ReporteGeneral Registro = new ReporteGeneral();

        try {


            InfoCurso curso = new InfoCurso();


            myResponse _myResponse = new myResponse();
            Vector retorno = new Vector();
            String consulta = new String();
            consulta = new String("with pre as(\n" +
"select Cur.id,Cur.NombreCurso,Cur.Valor,Lis2.Especificacion as Publico,Cur.Periodo,Cur.Inicio,Cur.Fin,Cur.Horas,Cur.CupoMax,Cur.Lugar,\n" +
"CONCAT(\n" +
"		(CASE LunesIni when ''then '' else 'Lunes: '+LunesIni+'-'+LunesFin+'</br>'end),\n" +
"		(CASE MartesIni WHEN '' THEN '' ELSE 'Martes: '+MartesIni+'-'+MartesFin+'</br>'  END),\n" +
"		(CASE MiercolesIni WHEN '' THEN '' ELSE 'Miercoles: '+MiercolesIni+'-'+MiercolesFin+'</br>'END),\n" +
"		(CASE JuevesIni WHEN '' THEN '' ELSE 'Jueves: '+JuevesIni+'-'+JuevesFin+'</br>'END),\n" +
"		(CASE ViernesIni WHEN '' THEN '' ELSE 'Viernes: '+ViernesIni+'-'+ViernesFin+'</br>'END),\n" +
"		(CASE SabadoIni WHEN '' THEN '' ELSE 'Sabado: '+SabadoIni+'-'+SabadoFin+'</br>'END),\n" +
"		(CASE DomingoIni WHEN '' THEN '' ELSE 'Domingo: '+DomingoIni+'-'+DomingoFin+'</br>'END)\n" +
"		)as Dias,Lis5.Especificacion as linea,Lis6.Especificacion as estado,Lis7.Especificacion as modalidad,Lis8.nombre as Unidad,Lis9.Descripcion as profesor,Lis13.Descripcion as segundoprofesor,\n" +
"                    Cur.Proexterno,Cur.Descripcion,Lis12.Especificacion as TipoCurso,---hasta aqui datos de curso\n" +
"                    Ins.docInscr,(case Ins.est when 1 then 'INSCRITO'when 0 then 'CANCELADO'ELSE 'N/A'end)as EstadoIns,\n" +
"                    Ins.fecreg,nova.nom1_emp +' ' + nova.nom2_emp + ' ' + nova.ap1_emp + ' ' + nova.ap2_emp as nombreCompleto,\n" +
"                    isnull(Lis11.Especificacion,0)as nota,isnull(nota.idregistro,0)as idregistro,isnull(nota.fecharegistro,'19000101')as fecharegistro \n" +
"                    from gescur.Cursos Cur\n" +
"                    left join gescur.Inscripciones Ins ON Cur.id=Ins.idCur\n" +
"                    left join Novasoft.dbo.rhh_emplea Nova ON convert(varchar(15),Nova.cod_emp)=convert(varchar(15),Ins.docInscr)\n" +
"                    Left JOIN gescur.Notas Nota ON Nota.idcurso=Cur.id and Nota.IdInscrito=Ins.docInscr\n" +
"                    LEFT JOIN gescur.Listas Lis2 on Lis2.id=Cur.Publico\n" +
"                    LEFT JOIN gescur.Listas Lis5 on Lis5.id=Cur.Linea\n" +
"                    LEFT JOIN gescur.Listas Lis6 on Lis6.id=Cur.Estado\n" +
"                    LEFT JOIN gescur.Listas Lis7 on Lis7.id=Cur.Modalidad\n" +
"                    LEFT JOIN (select codigo,nombre,6 as TipoDescripcion from Novasoft.dbo.gen_clasif1 where codigo in\n" +
"                              ('1201000000','1205010000','1206010000','1207010000','1208010000','1209010000','1210010000','1211010000','1212010000',\n" +
"                              '1213010000','1219000000','1217010000','1220010000','1101000000','1201000000','1216010000','1601010000','1301000000','1105005000','1214010000','1215010000','1216010000'))as Lis8 on Lis8.codigo=Cur.Unidad--falta LEFT JOIN (SELECT convert(varchar(15),e.cod_emp) as valor,\n" +
"                   LEFT JOIN(SELECT convert(varchar(15),e.cod_emp) as valor,\n" +
"                                                                      rtrim(ltrim(e.ap1_emp COLLATE Modern_Spanish_CI_AS)) +' '+ltrim(rtrim(e.ap2_emp ))+' '+ltrim(rtrim(e.nom_emp )) as Descripcion,\n" +
"                                                                      5 as Tipo \n" +
"                                                                      FROM Novasoft.dbo.rhh_emplea e where est_lab in (1,2,3,4)  )as Lis9 on Lis9.valor=convert(varchar(15),Cur.Profesor)\n" +
"                    												 LEFT JOIN (SELECT convert(varchar(15),e.cod_emp) as valor,\n" +
"                                                                      rtrim(ltrim(e.ap1_emp COLLATE Modern_Spanish_CI_AS)) +' '+ltrim(rtrim(e.ap2_emp ))+' '+ltrim(rtrim(e.nom_emp )) as Descripcion,\n" +
"                                                                      5 as Tipo \n" +
"                                                                      FROM Novasoft.dbo.rhh_emplea e where est_lab in (1,2,3,4)  )as Lis13 on Lis13.valor=convert(varchar(15),Cur.SegundoPro)\n" +
"												  LEFT JOIN gescur.Listas Lis10 on Lis10.id=Cur.Publico--falta\n" +
"                    LEFT JOIN gescur.Listas Lis12 on Lis12.id=Cur.IdtipoCurso\n" +
"                    LEFT JOIN gescur.Listas Lis11 on Lis11.id=Nota.nota"
                    + "					where Cur.Estado=coalesce(" + Filtros.getEstado() + ",Cur.Estado) --estado\n"
                    + "                    and Cur.IdTipoCurso=coalesce(" + Filtros.getTipoCurso() + ",Cur.IdTipoCurso)--tipo de curso\n"
                    + "                    and Cur.id=coalesce(" + Filtros.getNombreCurso()+ ",Cur.id)--curso\n"
                    + "                    and Cur.publico=coalesce(" + Filtros.getPublico() + ",Cur.publico)--tipo de publico\n"
                    + "                    and Cur.Unidad=coalesce(" + Filtros.getUnidad() + ",Cur.Unidad)--Unidad\n"
                    + "                    and (Cur.Profesor=coalesce("+Filtros.getProfesor()+" ,Cur.Profesor) \n"
                    + "			   or Cur.SegundoPro=coalesce("+Filtros.getSegProfesor()+" ,Cur.SegundoPro))--profesores\n"
                    + "					)\n"
                    + "					select * from pre where nombreCompleto like'%" + Filtros.getNombreEstu() + "%' ");

            conectarBD();
            retorno = consultar(consulta, 27, 1);
            _myResponse.setInformacion(true);
            for (int ii = 0; ii < retorno.size(); ii++) {
                ReporteGeneral.DatosReporte misDatos = new ReporteGeneral.DatosReporte();
                Vector infusu = new Vector();
                infusu = (Vector) retorno.elementAt(ii);
                misDatos.setId(Integer.parseInt(infusu.elementAt(0).toString()));
                misDatos.setNombreCurso(infusu.elementAt(1).toString());
                misDatos.setValor(Integer.parseInt(infusu.elementAt(2).toString()));
                misDatos.setPublico(infusu.elementAt(3).toString());
                misDatos.setPeriodo(infusu.elementAt(4).toString());
                misDatos.setInicio(infusu.elementAt(5).toString());
                misDatos.setFin(infusu.elementAt(6).toString());
                misDatos.setHoras(Integer.parseInt(infusu.elementAt(7).toString()));
                misDatos.setCupoMax(Integer.parseInt(infusu.elementAt(8).toString()));
                misDatos.setLugar(infusu.elementAt(9).toString());
                misDatos.setDias(infusu.elementAt(10).toString());
                misDatos.setLinea(infusu.elementAt(11).toString());
                misDatos.setEstado(infusu.elementAt(12).toString());
                misDatos.setModalidad(infusu.elementAt(13).toString());
                misDatos.setUnidad(infusu.elementAt(14).toString());
                misDatos.setProfesor(infusu.elementAt(15).toString());
                misDatos.setSegundoProfesor(infusu.elementAt(16).toString());
                misDatos.setProExterno(infusu.elementAt(17).toString());
                misDatos.setDescripcion(infusu.elementAt(18).toString());
                misDatos.setTipoCurso(infusu.elementAt(19).toString());
                misDatos.setDocInscr(infusu.elementAt(20).toString());
                misDatos.setEstadoIns(infusu.elementAt(21).toString());
                misDatos.setFecreg(infusu.elementAt(22).toString());
                misDatos.setNombreCompletoIns(infusu.elementAt(23).toString());
                misDatos.setNota(infusu.elementAt(24).toString());
                misDatos.setIdRegistro(infusu.elementAt(25).toString());
                misDatos.setFechaRegistro(infusu.elementAt(26).toString());

                Registro.Datos.add(misDatos);

            }

        } catch (Exception e) {
            Registro._myresResponse.setError("Error: " + e.getMessage());
            Registro._myresResponse.setTransaccion(false);
            Registro._myresResponse.setInformacion(false);
        }
        desconectarBD();
        return Registro;
    }

    public boolean ValidarExistenciaNota(NotaDeCurso id) {
        Vector retorno = new Vector();
        String consulta = new String();
        consulta = new String("select 1 from gescur.Notas where idcurso=" + id.getIdCurso() + " and idInscrito=" + id.getIdInscrito());
        try {
            conectarBD();
            retorno = consultar(consulta, 1, 1);
            for (int ii = 0; ii < retorno.size(); ii++) {

                Vector infusu = new Vector();
                infusu = (Vector) retorno.elementAt(ii);
                if (Integer.parseInt(infusu.elementAt(0).toString()) == 1) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (Exception ex) {
        } finally {
            desconectarBD();
        }
        return false;
    }

    public myResponse InsertarNuevaNota(NotaDeCurso NuevaNota) {
        _myResponse = new myResponse();

        try {

            consulta = new String("insert into gescur.Notas values("
                    + "'" + NuevaNota.getIdCurso() + "'"
                    + ",'" + NuevaNota.getIdInscrito() + "'"
                    + ",'" + NuevaNota.getNota() + "'"
                    + ",'" + NuevaNota.getIdRegistro() + "'"
                    + ",GETDATE()"
                    + ")");

            conectarBD();
            int respuesta = actualizar(consulta);
            desconectarBD();
            _myResponse.setTransaccion(true);
            _myResponse.setMessage("¡Nota registrada correctamente!");
        } catch (Exception e) {
            _myResponse.setTransaccion(false);
            _myResponse.setError(e.getMessage());
        }

        return _myResponse;
    }

    public myResponse UpdateNuevaNota(NotaDeCurso UpdateNota) {
        _myResponse = new myResponse();

        try {

            consulta = new String("update gescur.Notas set "
                    + "nota='" + UpdateNota.getNota() + "'"
                    + ",idregistro='" + UpdateNota.getIdRegistro() + "'"
                    + ",FechaRegistro=GETDATE() where idCurso=" + UpdateNota.getIdCurso() + " and idInscrito=" + UpdateNota.getIdInscrito());

            conectarBD();
            int respuesta = actualizar(consulta);
            desconectarBD();
            _myResponse.setTransaccion(true);
            _myResponse.setMessage("¡Nota actualizada correctamente!");
        } catch (Exception e) {
            _myResponse.setTransaccion(false);
            _myResponse.setError(e.getMessage());
        }

        return _myResponse;
    }

    public boolean ValidatToken(int id_emp, String cadena) {
        Vector retorno = new Vector();
        String consulta = new String();
        consulta = new String("declare @id_persona varchar(15)\n"
                + "                declare @cadena_encriptada varchar(50)\n"
                + "                set @id_persona='" + id_emp + "'\n"
                + "                set @cadena_encriptada='" + cadena + "'\n"
                + "                if(select flag_sesion from registro.token_gestor_cursos\n"
                + "                where id_persona=@id_persona and cadena_encriptada=@cadena_encriptada and flag_sesion=1)=1\n"
                + "                begin\n"
                + "                	--update registro.token_gestor_cursos set flag_sesion=1 where id_persona=@id_persona and cadena_llave=@cadena_encriptada;\n"
                + "                	select 'true';\n"
                + "                end\n"
                + "                else\n"
                + "                begin\n"
                + "                	select 'false';\n"
                + "                end");
        try {
            conectarBD();
            retorno = consultar(consulta, 1, 1);
            for (int ii = 0; ii < retorno.size(); ii++) {

                Vector infusu = new Vector();
                infusu = (Vector) retorno.elementAt(ii);
                if (Boolean.parseBoolean(infusu.elementAt(0).toString()) == true) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (Exception ex) {
        } finally {
            desconectarBD();
        }
        return false;
    }

    public InfoUsuario ObtenerInfUsu(int id_usu) {
        Vector retorno = new Vector();
        String consulta = new String();
        InfoUsuario infoUsuario = new InfoUsuario();
        consulta = new String("WITH PRE AS( \n" +
"select distinct cod_emp as id_emp,rtrim(ltrim(ap1_emp)) +' '+ltrim(rtrim(ap2_emp ))+' '+ltrim(rtrim(nom_emp )) as nombre, \n" +
"3 as rol  from Novasoft.dbo.rhh_emplea emp\n" +
"inner join Novasoft.dbo.rhh_cargos car on car.cod_car=emp.cod_car\n" +
"inner join registro.gescur.Cursos cur on convert(varchar(15),Cur.Profesor)=convert(varchar(15),rtrim(emp.cod_emp)) \n" +
" or convert(varchar(15),Cur.SegundoPro)=convert(varchar(15),rtrim(emp.cod_emp))\n" +
"where car.nom_car not like'%profesor%' and emp.est_lab in ('01','02','04','03')\n" +
"UNION\n" +
"select distinct\n" +
"cod_emp as id_emp,rtrim(ltrim(ap1_emp)) +' '+ltrim(rtrim(ap2_emp ))+' '+ltrim(rtrim(nom_emp )) as nombre, \n" +
"4 as rol  from Novasoft.dbo.rhh_emplea emp\n" +
"inner join Novasoft.dbo.rhh_cargos car on car.cod_car=emp.cod_car\n" +
"where car.nom_car not like'%profesor%' and emp.est_lab in ('01','02','04','03')\n" +
"UNION \n" +
"select distinct\n" +
"cod_emp as id_emp,rtrim(ltrim(ap1_emp)) +' '+ltrim(rtrim(ap2_emp ))+' '+ltrim(rtrim(nom_emp )) as nombre, \n" +
"2 as rol  from Novasoft.dbo.rhh_emplea emp\n" +
"inner join Novasoft.dbo.rhh_cargos car on car.cod_car=emp.cod_car\n" +
"where car.nom_car like'%profesor%' and emp.est_lab in ('01','02','04','03')\n" +
" UNION\n" +
"select cod_emp as id_emp, \n" +
"rtrim(ltrim(ap1_emp)) +' '+ltrim(rtrim(ap2_emp ))+' '+ltrim(rtrim(nom_emp )) as nombre, \n" +
"1 as rol  from Novasoft.dbo.rhh_emplea where cod_emp in ('35416070','52717602') \n" +
") \n" +
"SELECT top 1 * FROM PRE  \n" +
"where id_emp='"+id_usu+"' \n" +
"order by 3");
        try {
            conectarBD();
            retorno = consultar(consulta, 3, 1);
            for (int ii = 0; ii < retorno.size(); ii++) {

                Vector infusu = new Vector();
                infusu = (Vector) retorno.elementAt(ii);
                infoUsuario.setId_usu(id_usu);
                infoUsuario.setNombre(infusu.elementAt(1).toString());
                infoUsuario.setRol(Integer.parseInt(infusu.elementAt(2).toString()));
            }

        } catch (Exception ex) {
        } finally {
            desconectarBD();
        }
        return infoUsuario;
    }

    public String LlaveSecreta(int idusu) {
        Vector retorno = new Vector();
        String consulta = new String();
        String respuesta = new String();
        consulta = new String("select cadena_llave from registro.token_gestor_cursos "
                + "where  id_persona='" + idusu + "'");
        try {
            conectarBD();
            retorno = consultar(consulta, 1, 1);
            for (int ii = 0; ii < retorno.size(); ii++) {

                Vector infusu = new Vector();
                infusu = (Vector) retorno.elementAt(ii);
                respuesta = infusu.elementAt(0).toString();
            }

        } catch (Exception ex) {
        } finally {
            desconectarBD();
        }
        return respuesta;

    }
}
