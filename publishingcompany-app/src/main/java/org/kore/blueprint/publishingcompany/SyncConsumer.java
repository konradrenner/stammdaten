/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.kore.blueprint.publishingcompany;

import java.time.Instant;
import java.util.Set;

/**
 *
 * @author koni
 */
public interface SyncConsumer<T> {
    
    record SyncItem<T>(T item, Instant lastModification){}
    
    void acceptAllFromServer(Set<SyncItem<T>> data);
}
