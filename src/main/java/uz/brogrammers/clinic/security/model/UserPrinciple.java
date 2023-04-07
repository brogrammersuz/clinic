package uz.brogrammers.clinic.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.brogrammers.clinic.user.model.entity.User;
import uz.brogrammers.clinic.util.Utils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Builder
public class UserPrinciple implements UserDetails {

    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Long departmentId;
    private boolean isAdmin;
    private Collection authorities;

    public static UserPrinciple build(User user) {

        List authorities = user.getRoles().stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName().name())
                ).toList();

        return UserPrinciple.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .departmentId(user.getDepartmentId())
                .isAdmin(Utils.isAdmin(Optional.of(user)))
                .authorities(authorities)
                .build();
    }

    public boolean isAdmin() {
        return isAdmin;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserPrinciple that)) return false;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
