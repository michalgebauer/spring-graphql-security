package com.mi3o.springgraphqlsecurity.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mi3o.springgraphqlsecurity.config.Unsecured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
public class ResourceResolver implements GraphQLQueryResolver {
    // This method requires authenticated user by default
    public String securedResource() {
        return "Secured resource";
    }

    // This method requires user with role ADMIN
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String securedResourceAdmin() {
        return "Secured resource Admin";
    }

    @Unsecured
    // This method can be called by unauthenticated user
    public String unsecuredResource() {
        return "Unsecured resource";
    }
}
