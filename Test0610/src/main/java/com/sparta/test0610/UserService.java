package com.sparta.test0610;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {
    // 멤버 변수 선언
    private final UserRepository userRepository;

    // 생성자: ProductService() 가 생성될 때 호출됨
    @Autowired
    public UserService(UserRepository userRepository) {
        // 멤버 변수 생성
        this.userRepository = userRepository;
    }

    @Transactional // SQL 쿼리가 일어나야 함을 스프링에게 알려줌
    public Long update(Long id, UserRequestDto requestDto) {
        Users user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        user.update(requestDto);
        return user.getId();
    }
}