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
package org.kore.stammdaten.lager.jpa;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.spi.PersistenceUnitTransactionType;
import org.junit.After;
import org.junit.Before;

/**
 *
 * @author Konrad Renner
 */
public abstract class EntityTest {
    private EntityManagerFactory emf;

    private EntityManager em;

    @Before
    public void createFactory() {
        Map<String, String> properties = new HashMap<>();
        // Ensure RESOURCE_LOCAL transactions is used.
        properties.put("javax.persistence.transactionType", PersistenceUnitTransactionType.RESOURCE_LOCAL.name());
        properties.put("javax.persistence.jtaDataSource", null);

        // Configure the internal connection pool
        properties.put("javax.persistence.jdbc.driver", "org.apache.derby.jdbc.EmbeddedDriver");
        properties.put("javax.persistence.jdbc.url", "jdbc:derby:memory:STAMMDATEN;create=true");

        properties.put("javax.persistence.schema-generation.database.action", "drop-and-create");
        properties.put("javax.persistence.sql-load-script-source", "testdaten.sql");
//        properties.put(JDBC_URL, "jdbc:derby://localhost:1527/STAMMDATEN;create=true");
//        properties.put(JDBC_USER, "stammdaten");
//        properties.put(JDBC_PASSWORD, "stammdaten");


        emf = Persistence.createEntityManagerFactory("lager", properties);
    }

    /**
     * Liefert den EntityManager und attached die Testdaten von den Methoden die
     * mit @TestdatenFactory markiert worden sind
     *
     * @return EntityManager
     */
    public EntityManager getEntityManager() {
        em = emf.createEntityManager();

        return em;
    }

    @After
    public void closeFactory() {

        if (emf != null) {
            emf.close();
        }
    }

}
