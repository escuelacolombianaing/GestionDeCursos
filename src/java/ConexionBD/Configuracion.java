/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionBD;
import java.rmi.RemoteException;
import javax.ejb.*;
import java.util.Properties;
import java.sql.*;
import java.util.Vector;
import javax.sql.*;
import javax.naming.*;
/**
 *
 * @author javier.tambo
 */
public class Configuracion {
    
      private Connection con;
    private boolean conectado;
    protected String sPath; /* Path de backup y correo*/
    protected String textoCorreo; /* Sesion del texto de correo*/
    protected String mensaje;    
    //private static final String dbName = "java:comp/env/jdbc/registro";
    private static final String dbName = "jdbc/registro";
    /** Creates a new instance of BaseDatos */
    public Configuracion() {
        mensaje = "";
    }
    
     public int actualizar(java.lang.String consulta)  {
    	int resCons;
    	PreparedStatement sentenciaConsulta;
    	try {
            sentenciaConsulta = con.prepareStatement(consulta) ;
            resCons = sentenciaConsulta.executeUpdate();
            sentenciaConsulta.close();
    	} catch (Exception e) {
            mensaje = "Error Actualizar: " + e.getMessage();
            return 0 ;
    	}
    	return resCons ;
    }

    public String getMensajeBD() {
            return mensaje;
    }

    public Vector consultar(String consulta, int nroCampos, int preg) throws SQLException, Exception {
	int cont ;
        String valor, nd = new String("No disponible");
        Vector info = new Vector() ;
        Vector lista = new Vector() ;
        PreparedStatement prepStmt = con.prepareStatement(consulta);
        ResultSet rs = prepStmt.executeQuery();
        try {
            while (rs.next()) {
                Vector  obj = new Vector() ;
                for (cont = 1; cont <= nroCampos; cont++) {
                    valor = rs.getString(cont) ;
                    if (rs.wasNull())
                        obj.addElement(nd) ;
                    else
                        obj.addElement(valor) ;
                }
                lista.addElement(obj) ;
            }
        } catch (SQLException e) {
            mensaje = "La sentencia preparada no se ha podido cerrar correctamente " + e.getMessage();
        }
        prepStmt.close();
        if(lista.size() > 0 && preg == 0)
            info = (Vector)lista.elementAt(0);
        else if(preg == 1)
            return lista;
        return info;
    }

    /*private DataSource getJdbcDefinitivo() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/jdbc/definitivo");
    }*/

    public void conectarBD(){
        try {
            //InitialContext ic = new InitialContext();
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup(dbName);
            con = ds.getConnection();
            conectado = true;
        } catch (Exception ex) {
            conectado = false;
            mensaje = "No se puede hacer la conexion con la base de datos. " +
                ex.getMessage();
        }
    }

   /* public boolean desconectarBD() {
        try {
            con.close();
            conectado = false;
            return true;
        } catch (Exception e) {
            this.mensaje = e.getMessage();
            return false;
        }
    }*/


  public void desconectarBD(){
        try {
            con.close();
        } catch (SQLException ex) {
            mensaje= new String("Desconectar: " + ex.getMessage());
        }
    }
}
