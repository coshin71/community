package hello.community.service;

import hello.community.domain.User;
import hello.community.dto.LoginRequestUserDto;
import hello.community.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public User login(LoginRequestUserDto loginRequestUserDto) {
        return userRepository.findByLoginId(loginRequestUserDto.getLoginId())
                .filter(u -> u.getPassword().equals(loginRequestUserDto.getPassword()))
                .orElse(null);
    }

}
