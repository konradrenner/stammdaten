/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.kore.blueprint.publishingcompany.boundary.jaxrs;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * Default exception mapper for Client Problems (http status codes 400 - 499)
 *
 * @author koni
 */
@Provider
public class DefaultExceptionMapper<T extends Exception> implements ExceptionMapper<T> {

    private static final Logger LOG = Logger.getLogger(DefaultExceptionMapper.class.getName());
    private static final String RESPONSE_TYPE = "application/problem+json";

    @Context
    UriInfo info;

    @Inject
    Span span;

    @Override
    public final Response toResponse(T e) {

        LOG.log(Level.SEVERE, "A problem occured, so that the request cannot be fulfilled::{0}", e.getMessage());

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
        SpanContext spanContext = span.getSpanContext();

        return new ClientProblem.TraceContext(spanContext.getTraceId(), spanContext.getSpanId());
    }

    final UriInfo getUriInfo() {
        return info;
    }
}
