/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.kore.blueprint.publishingcompany;

import java.util.TimerTask;
import org.kore.blueprint.publishingcompany.boundary.jaxrs.AllAuthorGetTask;
import org.kore.blueprint.publishingcompany.entity.Author;

/**
 * 
 * Separate Factory class, so that Java FX classes don't depend on e.g. JAX RS classes
 * 
 * @author koni
 */
public class TimerFactory {
    
    private TimerFactory(){
        //factory
    }
    
    public static final TimerTask createSyncFromRemoteTask(SyncConsumer<Author> consumer){
        return new AllAuthorGetTask(consumer);
    }
}
