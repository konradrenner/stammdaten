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

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Konrad Renner
 */
@RunWith(MockitoJUnitRunner.class)
public class LagerraumTest {

    @Mock
    private Vorrat vorratArtikel1;
    @Mock
    private Vorrat vorratArtikel2;

    private Map<VorratKey, Vorrat> vorraete;

    @Before
    public void setUp() {
        VorratKey key1 = new VorratKey(1, (short) 1, (short) 1);
        when(vorratArtikel1.getVorratPK()).thenReturn(key1);
        when(vorratArtikel1.getEinheiten()).thenReturn(BigDecimal.valueOf(2));
        when(vorratArtikel1.getVolumenVerbrauch()).thenReturn(BigDecimal.valueOf(5));

        VorratKey key2 = new VorratKey(2, (short) 1, (short) 1);
        when(vorratArtikel2.getVorratPK()).thenReturn(key2);
        when(vorratArtikel2.getEinheiten()).thenReturn(BigDecimal.valueOf(5));
        when(vorratArtikel2.getVolumenVerbrauch()).thenReturn(BigDecimal.valueOf(1));

        vorraete = new HashMap<>();
        vorraete.put(key1, vorratArtikel1);
        vorraete.put(key2, vorratArtikel2);
    }

    @Test
    public void testOffenesVolumen() {
        BigDecimal gesamtVolumen = BigDecimal.valueOf(20);
        BigDecimal expected = BigDecimal.valueOf(5);

        LagerraumKey lagerraumKey = new LagerraumKey((short) 1, (short) 1);
        Lagerraum raum = new Lagerraum(lagerraumKey, null, null, gesamtVolumen, null, vorraete);

        assertThat(raum.getFreiesVolumen(), is(expected));
    }

    @Test
    public void testOffenesVolumenNichtsBelegt() {
        vorraete = new HashMap<>();
        BigDecimal gesamtVolumen = BigDecimal.valueOf(20);

        LagerraumKey lagerraumKey = new LagerraumKey((short) 1, (short) 1);
        Lagerraum raum = new Lagerraum(lagerraumKey, null, null, gesamtVolumen, null, vorraete);

        assertThat(raum.getFreiesVolumen(), is(gesamtVolumen));
    }

}
