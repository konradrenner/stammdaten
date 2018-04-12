/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.arquillian;

/**
 *
 * @author koni
 */
public class TestdataEntity implements Testdata{
    private int id;
    private String text;

    public TestdataEntity(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public TestdataEntity() {
        //tool
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
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
        final TestdataEntity other = (TestdataEntity) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TestdataEntity{" + "id=" + id + ", text=" + text + '}';
    }
    
    
}
