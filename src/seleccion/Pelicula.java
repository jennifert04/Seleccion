/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seleccion;

/**
 *
 * @author Jennifer
 */

import java.text.*;
import java.util.Date;
import edu.princeton.cs.algs4.*;
import static java.lang.Float.parseFloat;
import java.util.*;
/**
 *
 * @author Jennifer
 */
public class Pelicula implements Comparable<Pelicula> {

    private static Date parseDate(String field) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private String imdb_title_id;
    private String title;
    private String original_title;
    private int year;
    private Date date_published;
    private String genre;
    private int duration;
    private String country;
    private String language;
    private String director;
    private String writer;
    private String production_company;
    private String actors;
    private String description;
    private float avg_vote;
    private int votes;
    private String budget;
    private String usa_gross_income;
    private String worlwide_gross_income;
    private String metascore;
    private float reviews_from_users;
    private String reviews_from_critics;
    
    public int getYear(){
        return year;
    }
    
    public static Pelicula[] LeerCSV(String ruta) throws ParseException{
        
        List<Pelicula> listaP = new ArrayList<Pelicula>();
        In in = new In(ruta);
        in.readLine(); // ignore first line
        int counter = 0;
        while (!in.isEmpty()) {
            counter++;
            String line = in.readLine();
            // StdOut.println(line);
            try {
            String[] fields = null;
            
                // Solucion para no separar el campo de actores encerrado en comillas:
                // Tomada de: https://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes
                fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                // StdOut.println(fields.length);
                Pelicula p = new Pelicula();
                p.imdb_title_id = fields[0];
                p.title = fields[1];
                p.original_title = fields[2];
                p.year = Integer.valueOf(fields[3]);
                p.date_published = parseDate(fields[4]);
                p.genre = fields[5];
                p.duration = Integer.valueOf(fields[6]);
                p.country = fields[7];
                p.language = fields[8];
                p.director = fields[9];
                p.writer = fields[10];
                p.production_company = fields[11];
                p.actors = fields[12];
                p.description = fields[13];
                p.avg_vote = Float.valueOf(fields[14]);
                p.votes = Integer.valueOf(fields[15]);
                p.budget = fields[16];
                p.usa_gross_income = fields[17];
                p.worlwide_gross_income = fields[18];
                p.metascore = fields[19];
                p.reviews_from_users = parseFloat(fields[20]);
                p.reviews_from_critics = (fields.length>21) ? fields[21] : null;
                listaP.add(p); //añadimos a la lista
            }
            catch(NumberFormatException e) {
                StdOut.println("ERROR: Linea "+counter);
                StdOut.println(line);
                StdOut.println();
                return null;
            }
        }
        Pelicula[] vectorP = new Pelicula[listaP.size()]; //llenamos el vector 
        for(int i = 0; i < listaP.size(); i++){
            vectorP[i] = listaP.get(i);
        }
        return vectorP;
    }
    
    public static BST<Integer, Bag<Pelicula>> clasificarPorAño(Pelicula[] peliculas){
        
        BST<Integer, Bag<Pelicula>> peliculaporaño = new BST<>();
        Bag<Pelicula> bpeliculas = new Bag<>();;
        
        
        for(int i = 0; i < peliculas.length; i++){
            if(!peliculaporaño.contains(peliculas[i].year)){
               peliculaporaño.put(peliculas[i].year, new Bag<Pelicula>());
            }
            peliculaporaño.get(peliculas[i].year).add(peliculas[i]);
        }
        
        return peliculaporaño;
    }
    public static BST<Integer, MinPQ<Pelicula>> topMxAño(BST<Integer, Bag<Pelicula>> pelXaño,int m){
        MinPQ<Pelicula> cola = new MinPQ();
        BST<Integer, MinPQ<Pelicula>> Top = new BST<>();
        
        Iterable<Integer> item = pelXaño.keys();
        
            for(Integer x: item){//recorre todas las llaves
                cola = new MinPQ();
                for(Pelicula p: pelXaño.get(x)){//recorre todas las peliculas
                    cola.insert(p);
                    if(cola.size()==m){
                        if(p.compareTo(cola.min())>0){
                            
                           cola.delMin();
                           cola.insert(p);
                        }
                    }
               }
               Top.put(x, cola);
            }
           
        
        return Top;
    }
    @Override
    public int compareTo(Pelicula o) {
        return Float.compare(this.avg_vote, o.avg_vote);//si es 0 son iguales, si es positivo this es mayor
        //si es negativo o es mayor 
    }

}