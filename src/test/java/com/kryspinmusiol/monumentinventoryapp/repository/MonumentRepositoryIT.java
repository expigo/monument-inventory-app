package com.kryspinmusiol.monumentinventoryapp.repository;

import com.kryspinmusiol.monumentinventoryapp.model.Monument;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MonumentRepositoryIT {

    @Autowired
    MonumentRepository monumentRepository;

    @Autowired
    TestEntityManager tem;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void findByDesc() throws Exception {

        // given
        Monument mon = new Monument();
        mon.setName("itTest");
        mon.setDescription("desc");
        tem.persist(mon);
        tem.flush();

        // when
        Optional<Monument> monumentOptional = monumentRepository.findByDescription("desc");

        // then
        assertEquals("desc", monumentOptional.get().getDescription());
    }

    @Test
    public void findByName() throws Exception {
        // given
        Monument mon = new Monument();
        mon.setName("itTest");
        tem.persist(mon);
        tem.flush();

        // when
        Optional<Monument> monumentOptional = monumentRepository.findByName("itTest");

        // then
        assertEquals("itTest", monumentOptional.get().getName());
    }
}
