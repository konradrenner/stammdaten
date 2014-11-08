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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Konrad Renner
 */
@Embeddable
public class LagerraumKey implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "raum_id")
    private short raumId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lager_id")
    private short lagerId;

    protected LagerraumKey() {
    }

    protected LagerraumKey(short raumId, short lagerId) {
        this.raumId = raumId;
        this.lagerId = lagerId;
    }

    public short getRaumId() {
        return raumId;
    }

    public short getLagerId() {
        return lagerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) raumId;
        hash += (int) lagerId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LagerraumKey)) {
            return false;
        }
        LagerraumKey other = (LagerraumKey) object;
        if (this.raumId != other.raumId) {
            return false;
        }
        if (this.lagerId != other.lagerId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.kore.stammdaten.lager.domain.lager.LagerraumPK[ raumId=" + raumId + ", lagerId=" + lagerId + " ]";
    }

}
