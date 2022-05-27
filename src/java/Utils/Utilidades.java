/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author javier.tambo
 */
public class Utilidades {

    public String ConsumoREST(String url, String method) throws Exception {


        StringBuilder resultado = new StringBuilder();
        try {

            URL miurl = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) miurl.openConnection();

            connection.setRequestMethod(method);

            BufferedReader Breader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String linea = null;

            while ((linea = Breader.readLine()) != null) {
                resultado.append(linea);

            }

            Breader.close();

        } catch (Exception e) {
            return "" + e.getMessage();
        }

        return resultado.toString();
    }

    public String httpServletRequestToString(HttpServletRequest request) throws Exception {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            sb.append(line + "\n");
            line = reader.readLine();
        }
        reader.close();
        String params = sb.toString();
        String[] _params = params.split("&");
        for (String param : _params) {
            System.out.println("params(POST)-->" + param);
        }
        return params;
    }
}
