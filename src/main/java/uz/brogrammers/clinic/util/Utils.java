package uz.brogrammers.clinic.util;

import lombok.experimental.UtilityClass;
import uz.brogrammers.clinic.user.model.entity.User;
import uz.brogrammers.clinic.user.model.enums.RoleName;

import java.util.Optional;

@UtilityClass
public class Utils {

    public static boolean isAdmin(Optional<User> userOptional) {
        return userOptional.get().getRoles().stream()
                .anyMatch(role -> role.getName().name().equals(RoleName.ROLE_ADMIN.name()));
    }

}
