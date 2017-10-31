/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;

/**
 *
 * @author nakamura
 */
public class GetJSON {
    private Document doc;
    private DocumentBuilder db;

    public String openURL(String uri) {
        String content = null;
        try {
            URL url = new URL(uri);

            //HTTP connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            int code = conn.getResponseCode();

            if(code==407){
                System.out.println("Falha na autenticação do proxy");
            }else if (code == 200) {

                //Data reading
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                //String creation from result
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }
                rd.close();

                //Close connection
                conn.disconnect();

                //String with it's content
                content = sb.toString();

            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(GetJSON.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GetJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return content;
    }
}
