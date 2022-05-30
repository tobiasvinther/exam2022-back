package sem3.exam2022.dtos;

import lombok.Getter;
import lombok.Setter;
import sem3.exam2022.entities.Rider;
import sem3.exam2022.entities.Team;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static sem3.exam2022.services.TeamService.GetTotalRideTime;
import static sem3.exam2022.services.UtilityService.StringifyRideTime;

@Getter
@Setter
public class TeamResponse {

    private int id;
    private String teamName;
    private int teamRank;
    private List<RiderResponse> ridersOnTeam;
    private Duration teamRideTime;
    private String teamRideString;
    private long teamRideTimeLong;

    public TeamResponse(Team teamEntity) {
        this.id = teamEntity.getId();
        this.teamName = teamEntity.getTeamName();
        this.teamRank = teamEntity.getTeamRank();
        this.ridersOnTeam = RiderResponse.RiderResponsesFromEntities(teamEntity.getTeamMembers());
        this.teamRideTime = GetTotalRideTime(teamEntity);
        this.teamRideString = StringifyRideTime(GetTotalRideTime(teamEntity));
        this.teamRideTimeLong = GetTotalRideTime(teamEntity).toHours() + GetTotalRideTime(teamEntity).toMinutes() + GetTotalRideTime(teamEntity).toSeconds();
    }

    public static List<TeamResponse> teamResponsesFromEntities(List<Team> teamsAsEntities){
        return teamsAsEntities.stream().map(team -> new TeamResponse(team)).collect(Collectors.toList());
    }

}
