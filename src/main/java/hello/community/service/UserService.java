package hello.community.service;

import hello.community.domain.User.User;
import hello.community.dto.SignupDto;
import hello.community.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void save(SignupDto signupDto) {
        User user = new User(signupDto.getLoginId(), signupDto.getPassword());
        userRepository.save(user);
    }
}
