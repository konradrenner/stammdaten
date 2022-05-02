/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.kore.blueprint.publishingcompany.entity;

import java.util.Set;

/**
 *
 * @author koni
 */
public interface AuthorRepository {
    Set<Author> findAll();
    
}
