package antifraud.service;

import antifraud.domain.UserEntity;
import antifraud.domain.UserEntityDetails;
import antifraud.exception.UserNotFoundException;
import antifraud.repository.UserEntityRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserEntityDetailsService  implements UserDetailsService {

    private final UserEntityRepository userEntityRepository;

    public UserEntityDetailsService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userEntityRepository.findUserEntityByUsernameIgnoreCase(username);
        return userEntity.map(UserEntityDetails::new)
                .orElseThrow(UserNotFoundException::new);
    }
}
