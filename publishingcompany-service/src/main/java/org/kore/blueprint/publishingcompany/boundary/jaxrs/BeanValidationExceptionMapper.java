package org.kore.blueprint.publishingcompany.boundary.jaxrs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author koni
 */
@Provider
public class BeanValidationExceptionMapper extends BaseExceptionMapper<ConstraintViolationException> {

    private static final Logger LOG = Logger.getLogger(BeanValidationExceptionMapper.class.getName());

    private static final String PROBLEM_TYPE = "validation-problem";

    @Override
    ClientProblem createClientProblem(ConstraintViolationException exception) {

        LOG.log(Level.SEVERE, "ConstraintViolationException occured:{0}", exception.getMessage());

        UriInfo uriInfo = getUriInfo();

        String type = uriInfo.getBaseUriBuilder().path(PROBLEM_TYPE).build().toString();

        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();

        Collection<ClientProblem.InvalidParam> invalidParams = new ArrayList<>(constraintViolations.size());

        constraintViolations.stream().map(cv -> {
            String property = cv.getPropertyPath().toString();
            String invalidValue = Optional.ofNullable(cv.getInvalidValue()).map(value -> value.toString()).orElse(null);
            String reason = cv.getMessage();

            return new ClientProblem.InvalidParam(property, reason, invalidValue);
        }).forEachOrdered(invalid -> invalidParams.add(invalid));

        return new ClientProblem(type,
                "Validation problems occured",
                getStatusCode(),
                "Request, query or body parameters did not validate",
                getUriInfo().getPath(),
                getTraceContext(),
                invalidParams);
    }

}
