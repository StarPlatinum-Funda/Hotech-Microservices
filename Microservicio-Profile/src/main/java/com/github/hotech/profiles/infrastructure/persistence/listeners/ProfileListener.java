package com.github.hotech.profiles.infrastructure.persistence.listeners;

import com.github.hotech.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProfileListener {

    private final ProfileRepository profileRepository;

    public ProfileListener(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    private Map<String, Object> item;

    /*
    {

    }
    */
    @KafkaListener(topics = "profile-topic", groupId = "profile", containerFactory = "kafkaListenerContainerFactory")
    public void reciveItem(Map<String, Object> val){
        item = val;
        System.out.println(item.toString());

    }
}
