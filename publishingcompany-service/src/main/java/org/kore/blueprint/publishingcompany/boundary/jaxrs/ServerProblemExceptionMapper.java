package org.kore.blueprint.publishingcompany.boundary.jaxrs;

import javax.ws.rs.ext.Provider;

/**
 *
 * Default exception mapper for Server Problems
 *
 * @author koni
 */
@Provider
public class ServerProblemExceptionMapper extends BaseExceptionMapper<Throwable> {
// nothing special here, just need because Exception mapper class needs concrete type
}
