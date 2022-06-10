package com.sparta.test0610;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/read/{id}")
    Optional<Users> getUsers(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @GetMapping("/recommend/{id}")
    List<Users> recommendUsers(@PathVariable Long id) {
        Long gender = userRepository.findById(id).get().getGender();
        if (gender == 1) {
            gender --;
        } else {
            gender ++;
        }
        return userRepository.findAllByGender(gender);
    }

    // 신규 상품 등록
    @PostMapping("/create")
    public Users createUser(@RequestBody UserRequestDto requestDto) {
        Users user = new Users(requestDto);
        return userRepository.save(user);
    }

    @PutMapping("/api/courses/{id}")
    public Long updateCourse(@PathVariable Long id, @RequestBody UserRequestDto requestDto) {
        return userService.update(id, requestDto);
    }

    @DeleteMapping("/delete/{id}")
    public Long deleteUsers(@PathVariable Long id) {
        userRepository.deleteById(id);
        return id;
    }
}
