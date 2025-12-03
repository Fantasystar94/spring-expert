package org.example.expert;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.expert.config.PasswordEncoder;
import org.example.expert.domain.comment.repository.CommentRepository;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.todo.repository.TodoRepository;
import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.enums.UserRole;
import org.example.expert.domain.user.repository.UserRepository;
import org.example.expert.domain.user.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class InitData {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;


    @PostConstruct
    @Transactional
    public void init() {
        List<User> userList = List.of(
                new User("test1@gmail.com",passwordEncoder.encode("1234"), UserRole.USER),
                new User("test2@gmail.com",passwordEncoder.encode("1234"), UserRole.ADMIN),
                new User("test3@gmail.com",passwordEncoder.encode("1234"), UserRole.USER),
                new User("test4@gmail.com",passwordEncoder.encode("1234"), UserRole.ADMIN),
                new User("test5@gmail.com",passwordEncoder.encode("1234"), UserRole.USER)
        );

//        List<Todo> todoList = List.of(
//                new Todo("투두 1번 제목", "투두 1번 내용", "맑음", userRepository.findById(0L).orElseThrow()),
//                new Todo("투두 2번 제목", "투두 2번 내용", "흐림",userRepository.findById(1L).orElseThrow()),
//                new Todo("투두 3번 제목", "투두 3번 내용", "비", userRepository.findById(2L).orElseThrow()),
//                new Todo("투두 4번 제목", "투두 4번 내용", "눈", userRepository.findById(3L).orElseThrow()),
//                new Todo("투두 5번 제목", "투두 5번 내용", "바람", userRepository.findById(4L).orElseThrow())
//        );


        userRepository.saveAll(userList);
//        todoRepository.saveAll(todoList);
    }
}
