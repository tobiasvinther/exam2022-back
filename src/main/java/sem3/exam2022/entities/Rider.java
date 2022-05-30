package sem3.exam2022.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sem3.exam2022.dtos.RiderRequest;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDate birthDay;
    private String country; //change this to Country entity with country name and flag perhaps?
    private LocalTime rideTime;
    private int sprintPoints;
    private int mountainPoints;
    @ManyToOne()
    private Team team;

    public Rider(String name, LocalDate birthDay, String country, Team team) {
        this.name = name;
        this.birthDay = birthDay;
        this.country = country;
        this.team = team;

        this.rideTime = LocalTime.of(0,0,0);
        this.sprintPoints = 0;
        this.mountainPoints = 0;

        this.team.addRiderToTeam(this);
    }

    //constructor without input for birthday, because dates are messy. Birthday is instead set to 01/01/2000
    //delete if this is not used
    public Rider(String name, String country, Team team) {
        this.name = name;
        this.country = country;
        this.team = team;

        this.birthDay = LocalDate.of(2000, 1, 1);
        this.rideTime = LocalTime.of(0,0,0);
        this.sprintPoints = 0;
        this.mountainPoints = 0;

        this.team.addRiderToTeam(this);
    }

    //AUTO SETTING DEFAULT BIRTHDAY AT THIS POINT
    //todo: let user set birthday?
    public Rider(RiderRequest riderRequest) {
        this.name = riderRequest.getName();
        this.country = riderRequest.getCountry();
        this.team = riderRequest.getTeam();

        this.birthDay = LocalDate.of(2000, 1, 1);
        this.rideTime = LocalTime.of(0,0,0);
        this.sprintPoints = 0;
        this.mountainPoints = 0;

        this.team.addRiderToTeam(this);
    }

    /*
    public void setTeam(Team newTeam) {
        this.team.getTeamMembers().remove(this); //remove rider from current team's list of riders
        this.team = newTeam; //set team to new team
        newTeam.addRiderToTeam(this); //add rider to the new team's list of riders
    }
     */

}
