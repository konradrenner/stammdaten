/*
 * Copyright (C) 2014 Konrad Renner.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package org.kore.stammdaten.lager.domain.lager;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import org.kore.runtime.specifications.Identifier;

/**
 *
 * @author Konrad Renner
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Lagerraum.findAll", query = "SELECT l FROM Lagerraum l")
})
@IdClass(LagerraumKey.class)
public class Lagerraum implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum Typ {

        KUEHL, LAGER, BUERO, KONTROLL;
    }

    @Id
    @Column(name = "raum_id")
    private short raumId;
    @Id
    @JoinColumn(name = "lager_id", referencedColumnName = "lager_id")
    @ManyToOne
    private Lager lager;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Typ typ;
    @NotNull
    @Embedded
    private Identifier bezeichnung;
    @NotNull
    @Embedded
    private Volumen volumen;
    @Version
    private int version;
    @Transient
    private RaumId id;
    
    @JoinColumns({
        @JoinColumn(name = "lager_id", referencedColumnName = "lager_id"),
        @JoinColumn(name = "raum_id", referencedColumnName = "raum_id")
    })
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Vorrat> vorraete;

    protected Lagerraum() {
    }

    protected Lagerraum(Lager lager, RaumId raumId, Typ typ, Identifier bezeichnung, Volumen volumen) {
        this(lager, raumId, typ, bezeichnung, volumen, new ArrayList<>());
    }

    protected Lagerraum(Lager lager, RaumId raumId, Typ typ, Identifier bezeichnung, Volumen volumen, Collection<Vorrat> vorraete) {
        this.lager = lager;
        this.raumId = raumId.getValue();
        this.id = raumId;
        this.typ = typ;
        this.bezeichnung = bezeichnung;
        this.volumen = volumen;
        this.vorraete = vorraete;
    }

    @PostLoad
    protected void initId() {
        this.id = new RaumId(raumId);
    }

    public Collection<Vorrat> getVorraete() {
        return Collections.unmodifiableCollection(vorraete);
    }

    public BigDecimal getFreiesVolumen() {
        BigDecimal verbrauchtesVolumen = BigDecimal.ZERO;
        for (Vorrat vorrat : vorraete) {
            verbrauchtesVolumen = verbrauchtesVolumen.add(vorrat.getEinheiten().multiply(vorrat.getVolumenVerbrauch()));
        }
        return getVolumen().getGroesse().subtract(verbrauchtesVolumen);
    }

    Lager getLager() {
        return lager;
    }

    public RaumId getRaumId() {
        return this.id;
    }

    public Typ getTyp() {
        return typ;
    }

    public Identifier getBezeichnung() {
        return bezeichnung;
    }

    public Volumen getVolumen() {
        return volumen;
    }

    int getVersion() {
        return version;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lager != null ? lager.hashCode() : 0);
        hash += this.raumId * 31;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lagerraum)) {
            return false;
        }
        Lagerraum other = (Lagerraum) object;
        if ((this.lager == null && other.lager != null) || (this.lager != null && !this.lager.equals(other.lager))) {
            return false;
        }
        return this.raumId == other.raumId;
    }

    @Override
    public String toString() {
        return "Lagerraum{" + "raumId=" + raumId + ", lager=" + lager + ", typ=" + typ + ", bezeichnung=" + bezeichnung + ", volumen=" + volumen + ", version=" + version + ", lager=" + lager + ", vorraete=" + vorraete + '}';
    }


}
