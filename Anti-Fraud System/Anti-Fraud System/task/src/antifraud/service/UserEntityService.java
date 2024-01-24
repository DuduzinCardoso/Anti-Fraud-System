package antifraud.service;

import antifraud.domain.UserEntity;
import antifraud.dto.UserDto;
import antifraud.dto.UserRoles;
import antifraud.repository.UserEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserEntityService {

    private final UserEntityRepository userEntityRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserEntityService(UserEntityRepository userEntityRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userEntityRepository = userEntityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto registerUser(UserEntity userEntity) {
        if (userEntityRepository.findUserEntityByUsernameIgnoreCase(userEntity.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        setUserRole(userEntity);
        setUserPassword(userEntity);
        userEntityRepository.save(userEntity);
        UserEntity savedUser = userEntityRepository.findUserEntityByUsernameIgnoreCase(userEntity.getUsername()).get();
        return new UserDto(savedUser);
    }

    public void setUserRole(UserEntity user) {
        if (userEntityRepository.findAll().size() == 0) {
            user.setRole(UserRoles.ADMINISTRATOR);
            user.setAccountNonLocked(true);
        } else {
            user.setRole(UserRoles.MERCHANT);
        }
    }

    public void setUserPassword(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    public List<UserDto> showAllUsers() {
        return userEntityRepository.findAll().stream().map(UserDto::new).collect(Collectors.toList());
    }

    public Map<String, String> deleteUser(String username) {
        Optional<UserEntity> user = userEntityRepository.findUserEntityByUsernameIgnoreCase(username);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        userEntityRepository.delete(user.get());
        return Map.of("username", username, "status", "Deleted successfully!");
    }
}


