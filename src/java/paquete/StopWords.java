/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 *
 * @author Xiao
 */
public class StopWords {
    
    
     // funcion que separa un texto en palabras
    public static ArrayList<String> textoArray(String texto) throws FileNotFoundException, IOException
    {
        
                ArrayList<String> texto_separado = new ArrayList<>();
                StringTokenizer st = new StringTokenizer(texto, " ,.[[//\\']]\n\n|()==[]||{{}}+*:;?¿!¡<>-");
                while(st.hasMoreTokens())
                {
                    String palabra = st.nextToken();
                    texto_separado.add(palabra);
                    //System.out.println(palabra);
                }
               
                
            
            return texto_separado;
            
        }
 
       // funcion que lee el archivo de stopwords y lo pasa a un array
    public static ArrayList StopWordsArray(String archivo) throws FileNotFoundException, IOException {
        ArrayList lista = new ArrayList<>();
        String cadena;
        FileReader f = new FileReader(archivo);
        try (BufferedReader b = new BufferedReader(f)) {
            while((cadena = b.readLine())!=null) {
                
                lista.add(cadena);
                
            }
        }
        return lista;
    }
      
  public static ArrayList quitarStopwords(ArrayList texto,ArrayList listaStopWords) throws IOException
    {
        ArrayList listaIndices = new ArrayList<>();
        
        int tam = texto.size();
        for(int j = 0;j<listaStopWords.size();j++)
        {
            for(int i = 0;i<tam;i++)
            {
                // si la palabra en el array corresponde a una en la lista de stopwords se agrega a la lista de indices para luego borrarlo
                if(((String)texto.get(i)).compareTo((String)listaStopWords.get(j))== 0)
                {
                    //System.out.println("Se ha removido: "+texto.get(i));
                    listaIndices.add(i);
                }
            }
        }
        // se ordena de menor a mayor la lista de indices
        Collections.sort(listaIndices);
        
        // se borran las stopwords del texto que esta en el array
        for(int a = 0;a<listaIndices.size();a++)
        {
            if(a ==0)
            {
                //System.out.println("Se ha removido: "+ texto.get((int)listaIndices.get(a)));
                int r = (int)listaIndices.get(a);
                texto.remove(r);
                
                    
            }
            else
            {
                //System.out.println("Se ha removido: "+ texto.get((int)listaIndices.get(a)));
                int r = ((int)listaIndices.get(a))-a;
                texto.remove(r);
        
            }
        }
        return texto;
        
    }
  
  
  public static void main(String[] args) throws IOException, ClassNotFoundException {
      
      
      
      
  }
    
}
