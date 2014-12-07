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
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Konrad Renner
 */
@Embeddable
public class Volumen implements Serializable {

    @Column
    @NotNull
    private BigDecimal groesse;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Masseinheit einheit;

    protected Volumen() {
    }

    public Volumen(BigDecimal groesse, Masseinheit einheit) {
        this.groesse = groesse;
        this.einheit = einheit;
    }

    public BigDecimal getGroesse() {
        return groesse;
    }

    public Masseinheit getEinheit() {
        return einheit;
    }

}
