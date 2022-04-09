/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.kore.blueprint.publishingcompany.boundary.jaxrs;

import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;
import javax.annotation.PostConstruct;
import org.kore.blueprint.publishingcompany.Event;
import org.kore.blueprint.publishingcompany.entity.Author;
import org.kore.blueprint.publishingcompany.entity.AuthorRepository;
import org.kore.blueprint.publishingcompany.entity.Name;

/**
 * @author koni
 */
public class JaxRsAuthorRepository implements AuthorRepository{
    
    private Map<Name, Author> authors;
    private Queue<Consumer<Event<Author>>> consumers;
    
    @PostConstruct
    void init(){
        consumers = new ConcurrentLinkedQueue<>();
        authors = new ConcurrentHashMap<>();
        
        Author luke = new Author(new Name("Luke", "Skywalker"));
        Author palpatine = new Author(new Name("Sheev", "Palpatine"));
        
        addElement(luke);
        addElement(palpatine);
        
        // TODO implement TimerTask to periodically sync elements
    }

    @Override
    public Set<Author> findAll() {
        TreeSet<Author> ret = new TreeSet<>();
        authors.values().stream().forEach(ret::add);
        return ret;
    }
    
    public void addElementObserver(Consumer<Event<Author>> consumer){
        consumers.add(consumer);
        authors.values().stream().forEach(this::fireNewEvent);
    }
    
    
    void addElement(Author auth){
        authors.put(auth.getName(), auth);
        fireNewEvent(auth);
    }
    
    void fireNewEvent(Author newElement){
        final Event<Author> event = new Event(newElement, Event.Type.NEW);
        consumers.stream().forEach(consumer -> consumer.accept(event));
    }
}
