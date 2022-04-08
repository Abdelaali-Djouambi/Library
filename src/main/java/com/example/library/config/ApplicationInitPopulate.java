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
        userRepository.save(User.builder().userName("foo").cashBalance(1000l).build());
        userRepository.save(User.builder().userName("faa").cashBalance(1000l).build());
    }
}
