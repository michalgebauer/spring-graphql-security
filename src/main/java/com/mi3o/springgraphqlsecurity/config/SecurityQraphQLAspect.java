package com.mi3o.springgraphqlsecurity.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class SecurityQraphQLAspect {

    /**
     * All graphQLResolver methods can be called only by authenticated user.
     * Exclusions are named in Pointcut expression.
     */
    @Before("allGraphQLResolverMethods() && isDefinedInApplication() && !isUnsecuredResourceMethod()")
    public void doSecurityCheck() {
        if (SecurityContextHolder.getContext() == null ||
                SecurityContextHolder.getContext().getAuthentication() == null ||
                !SecurityContextHolder.getContext().getAuthentication().isAuthenticated() ||
                AnonymousAuthenticationToken.class.isAssignableFrom(SecurityContextHolder.getContext().getAuthentication().getClass())) {
            throw new AccessDeniedException("User not authenticated");
        }
    }

    /**
     * Matches all beans that implement {@link com.coxautodev.graphql.tools.GraphQLResolver}
     * note: {@code GraphQLMutationResolver}, {@code GraphQLQueryResolver} etc
     * extend base GraphQLResolver interface
     */
    @Pointcut("target(com.coxautodev.graphql.tools.GraphQLResolver)")
    private void allGraphQLResolverMethods() {
    }

    /**
     * Matches all beans in com.mi3o.springgraphqlsecurity package
     * resolvers must be in this package (subpackages)
     */
    @Pointcut("within(com.mi3o.springgraphqlsecurity..*)")
    private void isDefinedInApplication() {
    }

    /**
     * Exact method signature which will be excluded from security check
     */
    @Pointcut("execution(public java.lang.String com.mi3o.springgraphqlsecurity.resolver.ResourceResolver.unsecuredResource())")
    private void isUnsecuredResourceMethod() {
    }
}
