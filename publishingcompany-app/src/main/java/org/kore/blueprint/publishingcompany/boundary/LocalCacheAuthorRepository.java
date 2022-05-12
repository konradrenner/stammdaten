/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.kore.blueprint.publishingcompany.boundary;

import java.time.Instant;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.kore.blueprint.publishingcompany.Event;
import org.kore.blueprint.publishingcompany.SyncConsumer;
import org.kore.blueprint.publishingcompany.TimerFactory;
import org.kore.blueprint.publishingcompany.entity.Author;
import org.kore.blueprint.publishingcompany.entity.AuthorRepository;
import org.kore.blueprint.publishingcompany.entity.Name;

/**
 * @author koni
 */
public class LocalCacheAuthorRepository implements AuthorRepository, SyncConsumer<Author>{
    
    private Map<Name, LocalCacheItem<Author>> authors;
    private Queue<Consumer<Event<Author>>> consumers;
    private Optional<Timer> timer;
    
    @PostConstruct
    void init(){
        consumers = new ConcurrentLinkedQueue<>();
        authors = new ConcurrentHashMap<>();
    }
    
    public void enableAutoSync(){
        TimerTask syncTask = TimerFactory.createSyncFromRemoteTask(this);
        syncTask.run();
        
        Timer concreteTimer = new Timer();
        
        concreteTimer.schedule(syncTask, 0, 1000);
        
        timer = Optional.of(concreteTimer);
    }
    
    public void disableAutoSync(){
        timer.ifPresent(timer -> timer.cancel());
    }
    
    @Override
    public Set<Author> findAll() {
        TreeSet<Author> ret = new TreeSet<>();
        authors.values().stream().map(item -> item.getItem()).forEach(ret::add);
        return ret;
    }

    @Override
    public void acceptAllFromServer(Set<SyncItem<Author>> data) {
        clearNotAlteredItems();
        
        //conflict management: server or last update timestamp wins :-)
        clearItemsWhichAreNotInSetFromServer(data);
        Set<SyncItem<Author>> filtered = filterSyncItemsWhereLocalOnesAreNewer(data);
        applySyncItems(filtered);
    }
    
    void applySyncItems(Set<SyncItem<Author>> items){
        items.stream()
                .map(item -> LocalCacheItem.fromServerItem(item.item()))
                .sorted()
                .forEach(cacheItem -> {
                    authors.put(cacheItem.getItem().getName(), cacheItem);
                    this.fireEvent(cacheItem);
                   });
    }
    
    void clearNotAlteredItems(){
        authors.values()
                .stream()
                .filter(item -> item.getStatus().equals(LocalCacheItem.Status.NOT_ALTERED))
                .collect(Collectors.toSet())
                .stream()
                .forEach(item -> authors.remove(item.getItem().getName()));
    }
    
    Set<SyncItem<Author>> filterSyncItemsWhereLocalOnesAreNewer(Set<SyncItem<Author>> data){
        HashSet<SyncItem<Author>> copied = new HashSet<>(data);
        
        Set<SyncItem<Author>> localOnesAreNewer = data.stream()
                .filter(item -> authors.get(item.item().getName()) != null)
                .filter(item -> authors.get(item.item().getName()).getAlteringTimestamp().isAfter(item.lastModification()))
                .collect(Collectors.toSet());
        
        copied.removeAll(localOnesAreNewer);
        return copied;
    }
    
    void clearItemsWhichAreNotInSetFromServer(Set<SyncItem<Author>> data){
        authors.values()
                .stream()
                .filter(item -> item.getStatus().equals(LocalCacheItem.Status.NEW)) // new ones are normally not on the server
                .filter(item -> !data.contains(new SyncItem(item.getItem(), Instant.now())))
                .collect(Collectors.toSet())
                .stream()
                .forEach(item -> authors.remove(item.getItem().getName()));
    }
    
    public void addElementObserver(Consumer<Event<Author>> consumer){
        consumers.add(consumer);
        authors.values().stream().forEach(this::fireEvent);
    }
    
    void fireEvent(LocalCacheItem<Author> newElement){
        Event.Type type;
        switch(newElement.getStatus()){
            case NEW:
                type = Event.Type.NEW;
                break;
            case DELETED:
                type = Event.Type.DELETE;
                break;
            default:
                type =  Event.Type.UPDATE;
        }
        
        
        final Event<Author> event = new Event(newElement.getItem(), type, newElement.getAlteringTimestamp());
        consumers.stream().forEach(consumer -> consumer.accept(event));
    }
}
