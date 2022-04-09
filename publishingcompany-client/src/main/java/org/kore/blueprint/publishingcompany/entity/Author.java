/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.kore.blueprint.publishingcompany.entity;

import java.util.Objects;

/**
 *
 * @author koni
 */
// Author is an entity because of that no usage of record
public class Author implements Comparable<Author>{
    private final Name name;

    public Author(Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    @Override
    public int compareTo(Author o) {
        return name.compareTo(o.getName());
    }
    
    

    @Override
    public String toString() {
        return "Author{" + "name=" + name + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Author other = (Author) obj;
        return Objects.equals(this.name, other.name);
    }
    
    
}
