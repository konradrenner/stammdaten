package org.kore.blueprint.publishingcompany.boundary.jaxrs;

import java.util.Collection;
import java.util.Collections;

/**
 * RFC7807 Problem Detail
 *
 * @author koni
 */
public record Problem(String type, String title, int status, String detail, String instance, TraceContext traceContext, Collection<InvalidParam> invalidParams) {

    public Problem(String title, int status, String detail, String instance, TraceContext traceContext) {
        this("about:blank", title, status, detail, instance, traceContext, Collections.emptyList());
    }

    public static record InvalidParam(String name, String reason, String invalidValue) {

    }

    public static record TraceContext(String traceId, String spanId) {

    }
}
