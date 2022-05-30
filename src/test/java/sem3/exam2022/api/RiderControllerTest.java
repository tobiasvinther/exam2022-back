package sem3.exam2022.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import sem3.exam2022.entities.Rider;
import sem3.exam2022.entities.Team;
import sem3.exam2022.repositories.RiderRepository;
import sem3.exam2022.services.RiderService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest //full application context
@AutoConfigureMockMvc
@ActiveProfiles("test") //so that MakeTestData doesn't create data when we run these tests
class RiderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    RiderRepository riderRepository;

    //@Autowired
    //private ObjectMapper objectMapper;

    static int rider1Id;
    static Team AG2RCitroenTeam;

    @BeforeEach
    void setUp() {
        AG2RCitroenTeam = new Team("AG2R Citroën Team");
        rider1Id = riderRepository.save(new Rider("Tobias Vinther", LocalDate.of(1985,2,1), "Danmark", AG2RCitroenTeam)).getId();

    }

    @AfterEach
    void tearDown() {
        riderRepository.deleteAll();
    }

    @Test
    void getRiderById_test() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/riders/" + rider1Id)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(rider1Id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Tobias Vinther"));
        System.out.println("---End of getRiderById_test---");

    }
}