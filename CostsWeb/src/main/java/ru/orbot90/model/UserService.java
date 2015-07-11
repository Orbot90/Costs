package ru.orbot90.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.orbot90.user.User;

import java.util.Collections;

/**
 * Created by orbot on 11.07.15.
 */
@Scope("session")
public class UserService implements UserDetailsService {

    public User curUser;
    @Autowired
    UserRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return createUser(accountRepository.getUserByUserName(userName));
    }

    public void signIn(User user) {
        this.curUser = user;
        SecurityContextHolder.getContext().setAuthentication(authenticate(user));
    }

    public org.springframework.security.core.userdetails.User createUser(User user) {
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                Collections.singleton(createAuthority(user)));
    }

    public GrantedAuthority createAuthority(User user) {
        return new SimpleGrantedAuthority(user.getRole());
    }

    public Authentication authenticate(User user) {
        return new UsernamePasswordAuthenticationToken(createUser(user), null, Collections.singleton(createAuthority(user)));
    }
}
