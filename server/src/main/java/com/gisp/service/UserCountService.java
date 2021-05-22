package com.gisp.service;

import com.gisp.domain.UserCount;
import com.gisp.repository.UserCountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCountService {

    private final UserCountRepository userCountRepository;

    public UserCountService(UserCountRepository userCountRepository) {
        this.userCountRepository = userCountRepository;
    }

    public List<UserCount> findAll(){
        return userCountRepository.findAll();
    }

    public UserCount findByInn(String inn){
        return userCountRepository.findByInn(inn);
    }
}
