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
public class Actor {

    private int id;
    private String actorName;

    public Actor() {
    }

    public Actor(String name) {
        actorName = name;
    }

    public Actor(int id, String actorName) {
        this.id = id;
        this.actorName = actorName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Actor actor = (Actor) o;
        return id == actor.id && Objects.equals(actorName, actor.actorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, actorName);
    }
}
