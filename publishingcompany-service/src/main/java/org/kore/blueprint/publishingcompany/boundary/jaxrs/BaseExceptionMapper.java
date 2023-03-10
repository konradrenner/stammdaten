package org.kore.blueprint.publishingcompany.boundary.jaxrs;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author koni
 */
abstract class BaseExceptionMapper<T extends Exception> implements ExceptionMapper<T> {

    private static final Logger LOG = Logger.getLogger(BaseExceptionMapper.class.getName());
    private static final String RESPONSE_TYPE = "application/problem+json";

    @Context
    UriInfo info;

    @Override
    public final Response toResponse(T e) {

        LOG.log(Level.SEVERE, "A problem occured, so that the request cannot be fulfilled:{0}", e.getMessage());

        ClientProblem problemData = createClientProblem(e);

        LOG.log(Level.INFO, "created problem data:{0}", problemData);

        return Response.status(getStatusCode())
                .entity(problemData)
                .type(RESPONSE_TYPE)
                .build();
    }

    ClientProblem createClientProblem(T exception) {
        return new ClientProblem("Error caused by client",
                getStatusCode(),
                "The request contains bad syntax or cannot be fulfilled",
                info.getPath(),
                getTraceContext());
    }

    int getStatusCode() {
        return Response.Status.BAD_REQUEST.getStatusCode();
    }

    final ClientProblem.TraceContext getTraceContext() {
        Span span = Span.current();

        SpanContext spanContext = span.getSpanContext();

        return new ClientProblem.TraceContext(spanContext.getTraceId(), spanContext.getSpanId());
    }

    final UriInfo getUriInfo() {
        return info;
    }
}
