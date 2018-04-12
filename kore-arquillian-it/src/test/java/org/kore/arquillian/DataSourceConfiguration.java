/*
 * Copyright (C) 2018 koni.
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
package org.kore.arquillian;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author koni
 */
@Startup
@Singleton
// PostgreSQL config with postgres docker image at 172.17.02
//@DataSourceDefinition(name = "java:app/datasource",
//    className = "org.postgresql.ds.PGPoolingDataSource",
//    user = "postgres",
//    password = "***",
//    databaseName = "postgres",
//    portNumber = 5432,
//    serverName = "172.17.0.2")
@DataSourceDefinition(name = "java:app/datasource",
    className = "org.h2.jdbcx.JdbcDataSource",
    url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
    user = "sa",
    password = "sa",
    properties = {"driver=h2"})
public class DataSourceConfiguration {
    //nothing
}
