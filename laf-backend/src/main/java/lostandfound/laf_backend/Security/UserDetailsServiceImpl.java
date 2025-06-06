package lostandfound.laf_backend.Security;
import lostandfound.laf_backend.Model.User;
import lostandfound.laf_backend.Model.Enums.Role;
import lostandfound.laf_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // ✅ Ensure role is mapped with "ROLE_" prefix
        String authority = "ROLE_" + user.getRole().name();
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(authority))
        );
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Role role) {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }
}