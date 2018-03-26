/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvicenie4_01d;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author illuSion
 */
@Entity
public class Vydavatelstvo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String adresa;
    //s anotaciou to dalo do tabulky kniha fk vydavatela
    @OneToMany
    @JoinColumn(name="vydavatel_fk") //bez anotacie to vytvorilo junction table
    private List<Kniha> publikacia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public List<Kniha> getPublikacia() {
        return publikacia;
    }

    public void setPublikacia(List<Kniha> publikacia) {
        this.publikacia = publikacia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vydavatelstvo)) {
            return false;
        }
        Vydavatelstvo other = (Vydavatelstvo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cvicenie4_01d.Vydavatelstvo[ id=" + id + " ]";
    }
    
}
