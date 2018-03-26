/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvicenie2_04;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 *
 * @author illuSion
 */
public class Cvicenie2_04 {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cvicenie2_04PU");
        EntityManager em = emf.createEntityManager();
        /*mozno by sa hodilo najprv nacitat vsetko co uz je v db do setu...*/
        Set<String> nazvyKnih = new HashSet();
        String line;
        BufferedReader br = new BufferedReader(new FileReader("data1.csv"));
        em.getTransaction().begin();
        while ((line = br.readLine()) != null) {
            String s[] = line.split(";");
            if (s.length < 3) {
                System.out.println("kratky riadok");
                return;
            }
            
            String nazov = s[0].trim();
            
            if(!nazvyKnih.contains(nazov) && (nazov != null)) {
                System.out.println(line);
                String autor = s[1].trim();
                int pocet;
                if(!s[2].trim().equals("") && s[2].trim() != null ) {
                    pocet = Integer.parseInt(s[2].trim());
                } else {
                    pocet = 0;
                }
                Kniha toAdd = new Kniha();
                toAdd.setAutor(autor);
                toAdd.setNazov(nazov);
                toAdd.setPocet(pocet);
                //System.out.println(nazov + " " + autor + " " + pocet);
                em.persist(toAdd);
                nazvyKnih.add(nazov);
            }
        }
        em.getTransaction().commit();
    }
}
