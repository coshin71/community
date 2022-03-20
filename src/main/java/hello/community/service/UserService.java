package hello.community.service;

import hello.community.domain.User;
import hello.community.dto.UserSignupDto;
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
    public void signup(UserSignupDto userSignupDto) {
        User user = new User(userSignupDto.getLoginId(), userSignupDto.getPassword());
        userRepository.save(user);
    }

    @Transactional
    public Boolean isDuplicatedLoginId(String loginId) {
        User user = userRepository.findByLoginId(loginId).orElse(new User());
        if (user.getLoginId() != null) {
            return true;
        }
        return false;
    }
}
