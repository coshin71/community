package hello.community.service;

import hello.community.domain.User;
import hello.community.dto.SignupRequestUserDto;
import hello.community.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void signup(SignupRequestUserDto signupRequestUserDto) {
        User user = new User(signupRequestUserDto.getLoginId(), signupRequestUserDto.getPassword());
        userRepository.save(user);
    }

    @Transactional
    public Boolean isDuplicatedLoginId(String loginId) {
        User user = userRepository.findByLoginId(loginId).orElse(new User());
        return user.getLoginId() != null;
    }
}
