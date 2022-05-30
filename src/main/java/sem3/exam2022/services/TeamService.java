package sem3.exam2022.services;

import org.springframework.stereotype.Service;
import sem3.exam2022.dtos.TeamResponse;
import sem3.exam2022.entities.Team;
import sem3.exam2022.repositories.TeamRepository;

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
}
