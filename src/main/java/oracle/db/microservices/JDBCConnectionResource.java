/*
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package oracle.db.microservices;

import java.sql.Connection;
import java.sql.SQLException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@ApplicationScoped
public class JDBCConnectionResource {

  @Inject
  @Named("atp1")
  private DataSource dataSource;

  Connection connection;

  @Path("/getConnectionMetaData")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response getConnectionMetaData() throws SQLException {
    final Response returnValue = Response.ok()
            .entity("Connection obtained successfully metadata:" + getConnection().getMetaData())
            .build();
    return returnValue;
  }


  private Connection getConnection() throws SQLException {
    connection = connection != null? connection :this.dataSource.getConnection();
    System.out.println("--->connection:" + connection);
    System.out.println("--->connection.getMetaData():" + connection.getMetaData());
    return connection;
  }

}
