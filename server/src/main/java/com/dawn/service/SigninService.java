package com.dawn.service;

import com.dawn.dto.SignupForm;
import com.dawn.model.User;
import com.dawn.model.UserRole;
import com.dawn.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("SinginService")
@RequiredArgsConstructor
public class SigninService {

    private static final Logger logger = LoggerFactory.getLogger(SigninService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public List<GrantedAuthority> getAuthorities(final String loginId) {
        return null;
    }

    @Transactional
    public void SignUp(SignupForm signupForm) {
        String loginId= signupForm.getLoginId();
        signupForm.setPassword(passwordEncoder.encode(signupForm.getPassword()));
        System.out.println("암호화된 패스워드 = " + signupForm.getPassword());
        if(userRepository.findUserByLoginId(loginId) != null) {
            throw new IllegalArgumentException("중복되는 아이디명입니다.");
        }

        /*
        if(allowCodeRepository.findByAllowCode(signupForm.getAllowCode()) == null) {
            throw DCloudException.ofIllegalArgumentException("유효하지 않은 허가 코드입니다.");
        }
        */

        User user = signupForm.toUserEntity();
        User newUser = userRepository.save(user);
        List<UserRole> userRoleList = new ArrayList<>();
        //userRoleList.add(new UserRole(newUser, UserRole.USER_ROLE_MEMBER));
        //userRoleList = userRoleRepository.saveAll(userRoleList);
        newUser.setUserRoles(userRoleList);
        userRepository.save(newUser);

    }

}
