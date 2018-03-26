/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv3swingapp_2;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "PERSON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")})
public class Person implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "FIRSTNAME")
    private String firstName;
    @Column(name = "LASTNAME")
    private String lastName;
    @Column(name = "BIRTHYEAR")
    private int birthYear;
    @Column(name = "EMPLOYED")
    private boolean employed;

    public Person() { }

    public Person(String firstName, String lastName, int birthYear, boolean employed) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.employed = employed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter methods.
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public boolean isEmployed() {
        return employed;
    }

    // Setter methods.
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public void setEmployed(boolean employed) {
        this.employed = employed;
    }

    // toString() method for a textual description.
    public String toString() {
        return firstName + " " + lastName + ", " + birthYear + " (" + (employed ? "" : "not ") + "employed)";
    }

    // clone() method to create a copy of the object.
    public Object clone() {
        // int/boolean are primitive, String is immutable. For this reasons
        // it's sufficient to clone all fields with clone() of Object!
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            // Should NOT happen, this class implements Cloneable!
            throw new Error("Internal error!");
        }
    }
}