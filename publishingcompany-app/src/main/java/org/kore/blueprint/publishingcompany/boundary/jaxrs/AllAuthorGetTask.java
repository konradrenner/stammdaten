/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.kore.blueprint.publishingcompany.boundary.jaxrs;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;
import java.util.stream.Collectors;
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
        try{
            List<JsonAuthor> jsonAuthors = new AuthorsClient().getAll();
            
            final Instant syncTime = Instant.now();
            
            Set<SyncConsumer.SyncItem<Author>> authors = jsonAuthors.stream()
                    .map(json -> json.toEntity())
                    .map(author -> new SyncConsumer.SyncItem<>(author,syncTime))
                    .collect(Collectors.toSet());
            
            syncConsumer.acceptAllFromServer(authors);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
