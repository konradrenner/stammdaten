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

package org.kore.stammdaten.core.adresse;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Konrad Renner
 */
public class AdresseIDTest {

    private AdresseID.Builder builder;

    @Before
    public void setUp() {
        builder = new AdresseID.Builder(new Land("AT"), "Ort", "Musterstrasse", 700, (short) 4);
    }
    
    @Test
    public void testAdressBuilderTuerNull() {
        builder.tuer((short) 2);
        String expected = "AT07000004000002Ort       Musterstr";
        String result = builder.build().getValue();
        assertEquals(expected, result);
    }

    @Test
    public void testAdressBuilderStiegeNull() {
        builder.stiege((short) 5);
        String expected = "AT07000004005000Ort       Musterstr";
        String result = builder.build().getValue();
        assertEquals(expected, result);
    }

    @Test
    public void testAdressBuilderStiegeAndTuerNull() {
        String expected = "AT07000004000000Ort       Musterstr";
        String result = builder.build().getValue();
        assertEquals(expected, result);
    }

    @Test
    public void testAdressBuilderNonNull() {
        builder.stiege((short) 5);
        builder.tuer((short) 2);

        String expected = "AT07000004005002Ort       Musterstr";
        String result = builder.build().getValue();
        assertEquals(expected, result);
    }
}
