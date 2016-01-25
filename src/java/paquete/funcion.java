/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;
import static paquete.StopWords.StopWordsArray;
import static paquete.StopWords.quitarStopwords;
import static paquete.StopWords.textoArray;

/**
 *
 * @author Xiao
 */
@Path("funcionREST")
public class funcion {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String busqueda(@QueryParam("string_busqueda") String b) throws ClassNotFoundException, IOException {
        
        /*ArrayList<String> stop = new ArrayList<>();
        ArrayList<String> busqueda = new ArrayList();
        // leo el txt de stopwords
        stop = StopWordsArray("stop.txt");
        //convierto el string busqueda en arreglo
        busqueda = textoArray(b);
        
        busqueda = quitarStopwords(stop,busqueda);*/
       
        
        
        
        
        
        
        
        
      StringTokenizer st = new StringTokenizer(b);
        String g = st.nextToken();
        
        String msj2 = null;
        JSONObject json = new JSONObject();
        json.put("busqueda", g);
        json.put("actualizacion", 0);
        String estado = new String();
        ArrayList<String> lista_textos = new ArrayList<>();
        String resultado = new String();
        String resultado2 = new String();
        String respuesta_index = new String();
         int comp = 0 ;

        
        try {

            Socket cliente = new Socket("localhost", 4500); // nos conectamos con el servidor
            ObjectOutputStream mensaje = new ObjectOutputStream(cliente.getOutputStream()); // get al output del servidor, que es cliente : socket del cliente q se conecto al server
            mensaje.writeObject(json); // envio el msj al servidor

            //RECIVIR MENSAJE DESDE EL SERVIDOR
            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream()); // queda en pausa hasta que llegue un objeto nuevo
            json = (JSONObject) entrada.readObject();// cuando reciva el msj lo lee
            estado = (String) json.get("estado");
            System.out.println(estado);

            cliente.close();
            entrada.close();

            //Si no se encuentra en cache se pasa al INDEX
            comp = estado.compareTo("miss");
            //********************Si es MISS**************************
            if (comp == 0) {

                Socket cliente_Index = new Socket("localhost", 4545); // nos conectamos con el servidor
                ObjectOutputStream mensaje_Index = new ObjectOutputStream(cliente_Index.getOutputStream()); // get al output del servidor, que es cliente : socket del cliente q se conecto al server
                mensaje_Index.writeObject(json); // envio el msj al servidor

                //recivir
                ObjectInputStream entrada2 = new ObjectInputStream(cliente_Index.getInputStream()); // queda en pausa hasta que llegue un objeto nuevo
                lista_textos.clear();
                respuesta_index = (String) entrada2.readObject();// cuando reciva el msj lo lee
                System.out.println("fgfg");

               

            }
            

        } catch (UnknownHostException ex)// exepcion cuando no se reconoce el servidor
        {
            Logger.getLogger(funcion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(funcion.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        if(comp == 0)
        {
            return "La busqueda es \n" + respuesta_index;//json.toString()  ;
        }
        else
        {
            return (String)json.get("respuesta");
        }
        
    
     // return busqueda.get(0);
    }
}
