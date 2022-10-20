/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seleccion;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.MinPQ;
import java.text.ParseException;

/**
 *
 * @author Jennifer
 */
public class Taller4 {
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
        Pelicula[] peliculas = Pelicula.LeerCSV("C:\\Users\\Jennifer\\OneDrive - UPB\\Documentos\\SEMESTRE IV\\ESTRUCTURAS DE DATOS\\IMDb movies.csv");
        BST<Integer, MinPQ<Pelicula>> TopM = new BST<>();
        
        TopM=Pelicula.topMxAño(Pelicula.clasificarPorAño(peliculas), 10);
        
        for(Integer x: TopM.keys()){
            System.out.println(x.toString() + ": ");
            for(Pelicula p: Pelicula.clasificarPorAño(peliculas).get(x)){
                System.out.println(p.toString());
            }
        }
    }
}
