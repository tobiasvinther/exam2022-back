package sem3.exam2022.services;

import org.springframework.stereotype.Service;
import sem3.exam2022.dtos.TeamResponse;
import sem3.exam2022.entities.Rider;
import sem3.exam2022.entities.Team;
import sem3.exam2022.repositories.TeamRepository;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TeamService {

    TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<TeamResponse> getAllTeams() {
        List<Team> teamsAsEntities = teamRepository.findAll();
        return TeamResponse.teamResponsesFromEntities(teamsAsEntities);
    }

    public static Duration GetTotalRideTime(Team team) {
        Duration totalRideTime = Duration.of(0, ChronoUnit.SECONDS);
        for (Rider rider : team.getTeamMembers()) {
            totalRideTime = totalRideTime.plus(rider.getRideTime());
        }
        return totalRideTime;
    }
}
