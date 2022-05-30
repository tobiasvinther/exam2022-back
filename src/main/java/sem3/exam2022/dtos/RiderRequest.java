package sem3.exam2022.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sem3.exam2022.entities.Team;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RiderRequest {
    private String name;
    private String country;
    private String teamName; //using this on frontend
    private Team team;

    public RiderRequest(String name, String country, String teamName) {
        this.name = name;
        this.country = country;
        this.teamName = teamName;
    }

    public RiderRequest(String name, String country, Team team) {
        this.name = name;
        this.country = country;
        this.team = team;
    }
}
