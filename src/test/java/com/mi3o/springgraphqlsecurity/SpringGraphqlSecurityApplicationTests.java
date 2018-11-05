package com.mi3o.springgraphqlsecurity;

import com.mi3o.springgraphqlsecurity.resolver.ResourceResolver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringGraphqlSecurityApplicationTests {

    @Autowired
    private ResourceResolver resourceResolver;

    @Test
    public void unsecured_resource_ok() {
        resourceResolver.unsecuredResource();
    }

    @Test(expected = AccessDeniedException.class)
    public void secured_unauthorized_access_throws_exception() {
        resourceResolver.securedResource();
    }

    @Test
    @WithMockUser(username = "mi3o")
    public void secured_ok() {
        resourceResolver.securedResource();
    }

    @Test(expected = AccessDeniedException.class)
    public void admin_unauthorized_access_throws_exception() {
        resourceResolver.securedResourceAdmin();
    }

    @WithMockUser(username = "mi3o")
    @Test(expected = AccessDeniedException.class)
    public void without_admin_role_throws_exception() {
        resourceResolver.securedResourceAdmin();
    }

    @WithMockUser(username = "admin", roles = "ADMIN")
    @Test
    public void admin_role_ok() {
        resourceResolver.securedResourceAdmin();
    }

}
