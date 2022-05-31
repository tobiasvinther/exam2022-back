package sem3.exam2022.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sem3.exam2022.repositories.RiderRepository;
import sem3.exam2022.repositories.TeamRepository;

@DataJpaTest
class RiderServiceTest {

    @Autowired
    RiderRepository riderRepository;
    @Autowired
    TeamRepository teamRepository;

    RiderService riderService;

    @BeforeEach
    void setUp() {
        riderService = new RiderService(riderRepository, teamRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getRiderFromTeam_test() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> riderService.getRidersFromTeam(999));

        Assertions.assertEquals("No team with provided id found", exception.getMessage());
    }

    @Test
    void getRiderById_test() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> riderService.getRiderById(999));

        Assertions.assertEquals("No rider with provided id found", exception.getMessage());
    }

}