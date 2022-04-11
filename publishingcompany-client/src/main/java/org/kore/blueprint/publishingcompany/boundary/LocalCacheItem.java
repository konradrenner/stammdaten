/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.kore.blueprint.publishingcompany.boundary;

import java.time.Instant;
import java.util.Objects;

/**
 *
 * @author koni
 */
public final class LocalCacheItem<T> {
    public enum Status{
        NEW, MODIFIED, DELETED, NOT_ALTERED;
    }
    
    private Instant alteringTimestamp;
    private Status status;
    private final T item;
    
    public static final <T> LocalCacheItem<T> localNewItem(T item){
        return new LocalCacheItem(item, Status.NEW);
    }
    
    public static final <T> LocalCacheItem<T> localUpdatedItem(T item){
        return new LocalCacheItem(item, Status.MODIFIED);
    }
    
    public static final <T> LocalCacheItem<T> localDeletedItem(T item){
        return new LocalCacheItem(item, Status.DELETED);
    }
    
    public static final <T> LocalCacheItem<T> fromServerItem(T item){
        return new LocalCacheItem(item, Status.NOT_ALTERED);
    }

    private LocalCacheItem(T item, Status status) {
        this.item = item;
        this.status = status;
        this.alteringTimestamp = Instant.now();
    }

    public Instant getAlteringTimestamp() {
        return alteringTimestamp;
    }

    public Status getStatus() {
        return status;
    }

    public T getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "LocalCacheItem{" + "alteringTimestamp=" + alteringTimestamp + ", status=" + status + ", item=" + item + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.item);
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
        final LocalCacheItem<?> other = (LocalCacheItem<?>) obj;
        return Objects.equals(this.item, other.item);
    }
    
    
}
