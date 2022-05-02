/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.kore.blueprint.publishingcompany.entity;

/**
 *
 * @author koni
 */
public record Name(String firstname, String lastname) implements Comparable<Name> {

    @Override
    public int compareTo(Name o) {
        int result = 0;
        if(this.equals(o)){
            return result;
        }
        result = lastname().compareTo(o.lastname());
        if(result == 0){
            result = firstname().compareTo(o.firstname());
        }
        return result;
    }
}
