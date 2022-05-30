package sem3.exam2022.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String teamName;
    private int teamRank;
    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set<Rider> teamMembers = new HashSet<>();

    public Team(String teamName) {
        this.teamName = teamName;

        this.teamRank = 0;
    }

    public void addRiderToTeam(Rider rider) {
        teamMembers.add(rider);
        rider.setTeam(this); //for en sikkerheds skyld sætter vi også kandidatens parti til dette parti
    }

}
