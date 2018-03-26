/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvicenie3_jpakontroler;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author illuSion
 */
public class Cvicenie3_jpaKontroler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cvicenie3_jpaKontrolerPU");
        OsobaJpaController dao = new OsobaJpaController(emf);
         
        Osoba p = new Osoba(); //vytvori zaznam v DB (aj nakesuje)
        p.setId(13L);
        p.setMeno("Pipi");
        dao.create(p);
        
        Osoba toEdit = new Osoba(); //namerguje osoby
        toEdit.setId(13L);
        toEdit.setMeno("Pipi");
        toEdit.setVaha(117.0);
        dao.edit(toEdit);
        
        Osoba toFind = dao.findOsoba(13L); //vrati osobu podla id - da sa aj range osob hladat
        System.out.println(toFind.toString());
        
        System.out.println("Get count:" + dao.getOsobaCount());
        
        dao.destroy(13L); //znici zaznam v db -- odstrani az kes? asi nie
        
    }
}
