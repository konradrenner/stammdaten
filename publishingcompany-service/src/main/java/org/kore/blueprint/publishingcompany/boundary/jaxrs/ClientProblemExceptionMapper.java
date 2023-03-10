package org.kore.blueprint.publishingcompany.boundary.jaxrs;

import javax.ws.rs.ext.Provider;

/**
 *
 * Default exception mapper for Client Problems (http status codes 400 - 499)
 *
 * @author koni
 */
@Provider
public class ClientProblemExceptionMapper extends BaseExceptionMapper<Exception> {
// nothing special here, just need because Exception mapper class needs concrete type
}
