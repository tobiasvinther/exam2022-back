package sem3.exam2022.configuration;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import sem3.exam2022.entities.Country;
import sem3.exam2022.entities.Rider;
import sem3.exam2022.entities.Team;
import sem3.exam2022.repositories.CountryRepository;
import sem3.exam2022.repositories.RiderRepository;
import sem3.exam2022.repositories.TeamRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@Profile("!test")
public class CreateTestData implements ApplicationRunner {

    TeamRepository teamRepository;
    RiderRepository riderRepository;
    CountryRepository countryRepository;

    Team AG2RCitroenTeam;
    Team EFEducationEasyPost;
    Team LottoSoudal;

    Country France;
    Country Columbia;
    Country Spain;

    public CreateTestData(TeamRepository teamRepository, RiderRepository riderRepository, CountryRepository countryRepository) {
        this.teamRepository = teamRepository;
        this.riderRepository = riderRepository;
        this.countryRepository = countryRepository;
    }

    public void createTeams() {
        AG2RCitroenTeam = new Team("AG2R Citroën Team");
        EFEducationEasyPost = new Team("EF Education Easy Post");
        LottoSoudal = new Team("Lotto-Soudal");
        teamRepository.save(AG2RCitroenTeam);
        teamRepository.save(EFEducationEasyPost);
        teamRepository.save(LottoSoudal);
    }

    public void createCountries() {
        France = new Country("Frankrig", "https://flagcdn.com/h20/fr.png");
        Columbia = new Country("Columbia", "https://flagcdn.com/h20/co.png");
        Spain = new Country("Spanien", "https://flagcdn.com/h20/es.png");
    }

    public void createRiders() {
        Rider rider1 = new Rider("Clément Berthet", LocalDate.of(1997,8,2), "Frankrig", AG2RCitroenTeam);
        //rider1.setRideTime(LocalTime.of(1, 15, 12));
        rider1.setRideTime(Duration.ofSeconds(7699));
        riderRepository.save(rider1);
        Rider rider4 = new Rider("Geoffrey Bouchard", LocalDate.of(1992,4,1), "Frankrig", AG2RCitroenTeam);
        //rider1.setRideTime(LocalTime.of(1, 15, 12));
        rider4.setRideTime(Duration.ofSeconds(9276));
        riderRepository.save(rider4);

        Rider rider2 = new Rider("Daniel Arroyave Cañas", LocalDate.of(2000,6,19), "Columbia", EFEducationEasyPost);
        //rider2.setRideTime(LocalTime.of(1, 20, 0));
        rider2.setRideTime(Duration.ofSeconds(8234));
        riderRepository.save(rider2);

        Rider rider3 = new Rider("Carlos Barbero", LocalDate.of(1991,4,29), "Spanien", LottoSoudal);
        //rider3.setRideTime(LocalTime.of(1, 45, 23));
        rider3.setRideTime(Duration.ofSeconds(9987));
        riderRepository.save(rider3);

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        teamRepository.deleteAll();
        riderRepository.deleteAll();

        createTeams();
        createRiders();
    }
}
