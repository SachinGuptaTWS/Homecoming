package com.homecoming.backend.common.error;

import com.homecoming.backend.domain.exception.ResourceNotFoundException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GraphQlErrorAdvice {

    @GraphQlExceptionHandler(ResourceNotFoundException.class)
    GraphQLError notFound(ResourceNotFoundException ex, DataFetchingEnvironment environment) {
        return GraphqlErrorBuilder.newError()
                .errorType(ErrorType.NOT_FOUND)
                .message(ex.getMessage())
                .path(environment.getExecutionStepInfo().getPath())
                .location(environment.getField().getSourceLocation())
                .build();
    }

    @GraphQlExceptionHandler(IllegalArgumentException.class)
    GraphQLError badRequest(IllegalArgumentException ex, DataFetchingEnvironment environment) {
        return GraphqlErrorBuilder.newError()
                .errorType(ErrorType.BAD_REQUEST)
                .message(ex.getMessage())
                .path(environment.getExecutionStepInfo().getPath())
                .location(environment.getField().getSourceLocation())
                .build();
    }
}
