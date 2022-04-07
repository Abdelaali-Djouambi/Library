package com.example.library.config;

import com.example.library.entity.User;
import com.example.library.repository.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationInitPopulate implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepository userRepository;

    public ApplicationInitPopulate(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        userRepository.save(new User("foo", 50000l));
        userRepository.save(new User("faa", 50000l));
    }
}
