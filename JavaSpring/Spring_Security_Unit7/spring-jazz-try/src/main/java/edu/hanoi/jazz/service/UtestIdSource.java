package edu.hanoi.jazz.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.UserIdSource;

public class UtestIdSource implements UserIdSource {
    @Override
    public String getUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
