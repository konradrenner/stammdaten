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
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Konrad Renner
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Lagerraum.findAll", query = "SELECT l FROM Lagerraum l"),
    @NamedQuery(name = "Lagerraum.findByRaumId", query = "SELECT l FROM Lagerraum l WHERE l.raumId = :raumId"),
    @NamedQuery(name = "Lagerraum.findByTyp", query = "SELECT l FROM Lagerraum l WHERE l.typ = :typ"),
    @NamedQuery(name = "Lagerraum.findByBezeichnung", query = "SELECT l FROM Lagerraum l WHERE l.bezeichnung = :bezeichnung"),
    @NamedQuery(name = "Lagerraum.findByVolumen", query = "SELECT l FROM Lagerraum l WHERE l.volumen = :volumen"),
    @NamedQuery(name = "Lagerraum.findByVersion", query = "SELECT l FROM Lagerraum l WHERE l.version = :version")})
public class Lagerraum implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "raum_id")
    private Short raumId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    private String typ;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String bezeichnung;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    private BigDecimal volumen;
    @Basic(optional = false)
    @NotNull
    private int version;
    @JoinColumn(name = "lager_id", referencedColumnName = "lager_id")
    @ManyToOne(optional = false)
    private Lager lagerId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lagerraum")
    private Collection<ArtikelLagerraum> artikelLagerraumCollection;

    public Lagerraum() {
    }

    public Lagerraum(Short raumId) {
        this.raumId = raumId;
    }

    public Lagerraum(Short raumId, String typ, String bezeichnung, BigDecimal volumen, int version) {
        this.raumId = raumId;
        this.typ = typ;
        this.bezeichnung = bezeichnung;
        this.volumen = volumen;
        this.version = version;
    }

    public Short getRaumId() {
        return raumId;
    }

    public void setRaumId(Short raumId) {
        this.raumId = raumId;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public BigDecimal getVolumen() {
        return volumen;
    }

    public void setVolumen(BigDecimal volumen) {
        this.volumen = volumen;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Lager getLagerId() {
        return lagerId;
    }

    public void setLagerId(Lager lagerId) {
        this.lagerId = lagerId;
    }

    public Collection<ArtikelLagerraum> getArtikelLagerraumCollection() {
        return artikelLagerraumCollection;
    }

    public void setArtikelLagerraumCollection(Collection<ArtikelLagerraum> artikelLagerraumCollection) {
        this.artikelLagerraumCollection = artikelLagerraumCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (raumId != null ? raumId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lagerraum)) {
            return false;
        }
        Lagerraum other = (Lagerraum) object;
        if ((this.raumId == null && other.raumId != null) || (this.raumId != null && !this.raumId.equals(other.raumId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.kore.stammdaten.lager.domain.lager.Lagerraum[ raumId=" + raumId + " ]";
    }

}
