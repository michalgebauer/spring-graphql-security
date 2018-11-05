package com.mi3o.springgraphqlsecurity.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
public class ResourceResolver implements GraphQLQueryResolver {
    public String securedResource() {
        return "Secured resource";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String securedResourceAdmin() {
        return "Secured resource Admin";
    }

    public String unsecuredResource() {
        return "Unsecured resource";
    }

}
