package com.kryspinmusiol.monumentinventoryapp.bootstrap;

import com.kryspinmusiol.monumentinventoryapp.model.Accessibility;
import com.kryspinmusiol.monumentinventoryapp.model.Monument;
import com.kryspinmusiol.monumentinventoryapp.repository.MonumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@Profile({"prod", "dev"})
public class MysqlBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private static final Accessibility MONUMENT_ACCESSIBILITY= Accessibility.PERMITTED;


    private final MonumentRepository monumentRepository;

    public MysqlBootstrap(MonumentRepository monumentRepository) {
        this.monumentRepository = monumentRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("Loading bootstrap data");
        monumentRepository.saveAll(getMonuments());
    }

    private List<Monument> getMonuments() {

        List<Monument> monuments = new ArrayList<>();

        return monuments;


    }
}
