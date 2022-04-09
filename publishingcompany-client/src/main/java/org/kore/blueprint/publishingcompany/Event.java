/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.kore.blueprint.publishingcompany;

/**
 *
 * @author koni
 */
public record Event<T>(T entity, Type type) {
    public enum Type{
        NEW, UPDATE, DELETE;
    }
}
