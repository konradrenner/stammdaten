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
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.MapKeyJoinColumns;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.kore.runtime.specifications.Identifier;

/**
 *
 * @author Konrad Renner
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Lagerraum.findAll", query = "SELECT l FROM Lagerraum l")
})
public class Lagerraum implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LagerraumKey lagerraumPK;
    @Column
    @NotNull
    @Size(min = 1, max = 8)
    private String typ;
    @NotNull
    @Embedded
    private Identifier bezeichnung;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column
    @NotNull
    private BigDecimal volumen;
    @Version
    private int version;
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "volumen_einheit")
    private String volumenEinheit;
//    @JoinColumn(name = "lager_id", insertable = false, updatable = false)
//    @MapsId(value = "lagerId")
//    @ManyToOne
//    private Lager lager;
    @MapKeyJoinColumns({
        @MapKeyJoinColumn(name = "lager_id", referencedColumnName = "lager_id"),
        @MapKeyJoinColumn(name = "raum_id", referencedColumnName = "raum_id")
    })
    @OneToMany(cascade = CascadeType.ALL)
    private Map<VorratKey, Vorrat> vorraete;

    protected Lagerraum() {
    }

    protected Lagerraum(LagerraumKey lagerraumPK, String typ, Identifier bezeichnung, BigDecimal volumen, String volumenEinheit, Map<VorratKey, Vorrat> vorraete) {
        this.lagerraumPK = lagerraumPK;
        this.typ = typ;
        this.bezeichnung = bezeichnung;
        this.volumen = volumen;
        this.volumenEinheit = volumenEinheit;
        this.vorraete = vorraete;
    }

    public Lagerraum(short raumId, short lagerId) {
        this.lagerraumPK = new LagerraumKey(raumId, lagerId);
    }

    public Collection<Vorrat> getVorraete() {
        return Collections.unmodifiableCollection(vorraete.values());
    }

    public BigDecimal getFreiesVolumen() {
        BigDecimal verbrauchtesVolumen = BigDecimal.ZERO;
        for (Vorrat vorrat : vorraete.values()) {
            verbrauchtesVolumen = verbrauchtesVolumen.add(vorrat.getEinheiten().multiply(vorrat.getVolumenVerbrauch()));
        }
        return getVolumen().subtract(verbrauchtesVolumen);
    }

    public LagerraumKey getLagerraumPK() {
        return lagerraumPK;
    }

    public String getTyp() {
        return typ;
    }

    public Identifier getBezeichnung() {
        return bezeichnung;
    }

    public BigDecimal getVolumen() {
        return volumen;
    }

    public int getVersion() {
        return version;
    }

    public String getVolumenEinheit() {
        return volumenEinheit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lagerraumPK != null ? lagerraumPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lagerraum)) {
            return false;
        }
        Lagerraum other = (Lagerraum) object;
        if ((this.lagerraumPK == null && other.lagerraumPK != null) || (this.lagerraumPK != null && !this.lagerraumPK.equals(other.lagerraumPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.kore.stammdaten.lager.domain.lager.Lagerraum[ lagerraumPK=" + lagerraumPK + " ]";
    }

}
