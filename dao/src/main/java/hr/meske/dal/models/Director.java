/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.meske.dal.models;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 505wL
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Director {

    private int id;
    private String directorName;

    public Director() {
    }

    public Director(int id, String directorName) {
        this.id = id;
        this.directorName = directorName;
    }

    public Director(String directorName) {
        this.directorName = directorName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Director director = (Director) o;
        return id == director.id && Objects.equals(directorName, director.directorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, directorName);
    }
}
