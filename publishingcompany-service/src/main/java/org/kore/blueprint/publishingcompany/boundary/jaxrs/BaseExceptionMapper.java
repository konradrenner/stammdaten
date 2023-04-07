package org.kore.blueprint.publishingcompany.boundary.jaxrs;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author koni
 */
abstract class BaseExceptionMapper<T extends Throwable> implements ExceptionMapper<T> {

    private static final Logger LOG = Logger.getLogger(BaseExceptionMapper.class.getName());
    private static final String RESPONSE_TYPE = "application/problem+json";

    @Context
    UriInfo info;

    @Override
    public final Response toResponse(T e) {

        LOG.log(Level.SEVERE, "A problem occured, so that the request cannot be fulfilled:{0}", e.getMessage());

        Problem problemData = createClientProblem(e);

        LOG.log(Level.INFO, "created problem data:{0}", problemData);

        return Response.status(getStatusCode())
                .entity(problemData)
                .type(RESPONSE_TYPE)
                .build();
    }

    Problem createClientProblem(T exception) {
        return new Problem("The server failed to fulfill the request",
                getStatusCode(),
                "An unexpected condition was encoutered",
                info.getPath(),
                getTraceContext());
    }

    int getStatusCode() {
        return Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
    }

    final Problem.TraceContext getTraceContext() {
        Span span = Span.current();

        SpanContext spanContext = span.getSpanContext();

        return new Problem.TraceContext(spanContext.getTraceId(), spanContext.getSpanId());
    }

    final UriInfo getUriInfo() {
        return info;
    }
}
