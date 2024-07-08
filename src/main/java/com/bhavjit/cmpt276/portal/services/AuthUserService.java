package com.bhavjit.cmpt276.portal.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthUserService extends OidcUserService {
private static final Logger logger = LoggerFactory.getLogger(AuthUserService.class);

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) {
        
        OidcUser oidcUser = super.loadUser(userRequest);
        logger.info("Loading user...");

        Map<String, Object> attributes = new HashMap<>(oidcUser.getAttributes());
        logger.info("User attributes: {}", attributes);

        String sub = (String) attributes.get("sub");
        if (sub == null) {
            throw new IllegalArgumentException("Missing attribute 'sub' in attributes");
        }

        @SuppressWarnings("unchecked")
        List<String> groups = (List<String>) attributes.get("cognito:groups");
        logger.info("Cognito groups: {}", groups);

        // Convert groups to authorities
        List<GrantedAuthority> authorities = null;
        if(groups != null) {
            authorities = groups.stream()
                .map(group -> new SimpleGrantedAuthority("ROLE_" + group.toUpperCase()))
                .collect(Collectors.toList());
        }
        logger.info("User authorities: {}", authorities);

        OidcIdToken idToken = userRequest.getIdToken();
        return new DefaultOidcUser(authorities, idToken, "sub");
    }
}
