package uz.brogrammers.clinic.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.brogrammers.clinic.user.model.entity.Role;
import uz.brogrammers.clinic.user.model.enums.RoleName;
import uz.brogrammers.clinic.user.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role findByName(RoleName name) {
        return roleRepository.findByName(name.name())
                .orElseThrow(() -> new RuntimeException("role by name " + name.name() + "not found"));
    }

}
