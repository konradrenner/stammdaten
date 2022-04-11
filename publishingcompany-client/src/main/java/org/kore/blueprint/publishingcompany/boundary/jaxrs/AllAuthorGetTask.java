/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.kore.blueprint.publishingcompany.boundary.jaxrs;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.TimerTask;
import java.util.function.Consumer;
import org.kore.blueprint.publishingcompany.SyncConsumer;
import org.kore.blueprint.publishingcompany.entity.Author;
import org.kore.blueprint.publishingcompany.entity.Name;

/**
 *
 * @author koni
 */
public class AllAuthorGetTask extends TimerTask{
    
    private final SyncConsumer<Author> syncConsumer;

    public AllAuthorGetTask(SyncConsumer<Author> syncConsumer) {
        this.syncConsumer = syncConsumer;
    }
    
    @Override
    public void run() {
        //TODO jax rs call
        
        Author luke = new Author(new Name("Luke", "Skywalker"));
        Author palpatine = new Author(new Name("Sheev", "Palpatine"));
        Author solo = new Author(new Name("Han", "Solo"));
        
        HashSet<SyncConsumer.SyncItem<Author>> authors = new HashSet<>();
        authors.add(new SyncConsumer.SyncItem<>(luke,Instant.now()));
        authors.add(new SyncConsumer.SyncItem<>(palpatine,Instant.now()));
        authors.add(new SyncConsumer.SyncItem<>(solo,Instant.now()));
        
        syncConsumer.acceptAllFromServer(authors);
    }
    
}
