/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionBD;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import ConexionBD.*;
import Model.*;

/**
 *
 * @author javier.tambo
 */
public class Listas extends Configuracion {

    List<Model.Listas> listas = new ArrayList<Model.Listas>();
    public List<Model.Listas> NuevaLista(FiltrosDinamicos filtros) {

        Vector retorno = new Vector();
        String consulta = new String();
        consulta = new String("select id as valor,Especificacion as Descripcion,Tipo as Tipo, DependenciaTipo as DependenciaTipo from gescur.Listas\n" +
"UNION \n" +
"(select distinct cod_emp,rtrim(ltrim(ap1_emp COLLATE Modern_Spanish_CI_AS)) +' '+ltrim(rtrim(ap2_emp ))+' '+ltrim(rtrim(nom_emp )) as Descripcion,\n" +
" 5 as Tipo,\n" +
"31 as DependenciaTipo\n" +
"from novasoft.dbo.rhh_emplea \n" +
"where est_lab in (1,2,3,4))\n" +
"UNION\n" +
"select codigo,nombre,6,31 as DependenciaTipo from Novasoft.dbo.gen_clasif1 where codigo in\n" +
" (\n" +
" '1201000000','1205010000','1206010000','1207010000','1208010000','1209010000','1210010000','1211010000','1212010000',\n" +
" '1213010000','1219000000','1217010000','1220010000','1101000000','1201000000','1216010000','1601010000','1301000000','1105005000',\n" +
" '1106010000','1214010000','1215010000','1216010000'\n" +
" )\n" +
"UNION\n" +
"select id,concat(id,' - ',NombreCurso),10,31 from gescur.Cursos\n" +
"where Profesor=coalesce("+ filtros.getProfesor()+",Profesor)\n" +
"or SegundoPro=coalesce("+ filtros.getSegProfesor()+",SegundoPro)" +
" 		   order by Tipo,Descripcion ");
        try {
            conectarBD();
            retorno = consultar(consulta, 4, 1);
            for (int ii = 0; ii < retorno.size(); ii++) {
                Vector infusu = new Vector();
                infusu = (Vector) retorno.elementAt(ii);
                listas.add(new Model.Listas(Integer.parseInt(infusu.elementAt(0).toString()), infusu.elementAt(1).toString(), Integer.parseInt(infusu.elementAt(2).toString()),Integer.parseInt(infusu.elementAt(3).toString())));
            }

        } catch (Exception ex) {
            mensaje = new String("Unable to fetch status due to SQLException: " + ex.getMessage());
            listas.clear();
        }
        desconectarBD();
        return listas;

    }
}
