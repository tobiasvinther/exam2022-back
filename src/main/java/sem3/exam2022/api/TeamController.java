package sem3.exam2022.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sem3.exam2022.dtos.TeamResponse;
import sem3.exam2022.entities.Team;
import sem3.exam2022.repositories.TeamRepository;
import sem3.exam2022.services.TeamService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/teams")
public class TeamController {

    TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping()
    public List<TeamResponse> getAllTeams() {
        return teamService.getAllTeams();
    }

}
