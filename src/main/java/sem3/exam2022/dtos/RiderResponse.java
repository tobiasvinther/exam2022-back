package sem3.exam2022.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sem3.exam2022.entities.Rider;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static sem3.exam2022.services.UtilityService.StringifyRideTime;

@Getter
@Setter
@NoArgsConstructor
public class RiderResponse {
    private int id;
    private String name;
    private LocalDate birthDay;
    private String country;
    //private LocalTime rideTime;
    private Duration rideTime;
    private String rideTimeString;
    private long rideTimeLong;
    private int sprintPoints;
    private int mountainPoints;
    private String teamName;
    private int teamId;

    public RiderResponse(Rider riderAsEntity) {
        this.id = riderAsEntity.getId();
        this.name = riderAsEntity.getName();
        this.birthDay = riderAsEntity.getBirthDay();
        this.country = riderAsEntity.getCountry();
        this.rideTime = riderAsEntity.getRideTime();
        this.sprintPoints = riderAsEntity.getSprintPoints();
        this.mountainPoints = riderAsEntity.getMountainPoints();
        this.teamName = riderAsEntity.getTeam().getTeamName();
        this.teamId = riderAsEntity.getTeam().getId();
        this.rideTimeString = StringifyRideTime(this.rideTime);
        this.rideTimeLong = riderAsEntity.getRideTime().toHours() + riderAsEntity.getRideTime().toMinutes() + riderAsEntity.getRideTime().toSeconds();
    }

    public static List<RiderResponse> RiderResponsesFromEntities(List<Rider> ridersAsEntities){
        return ridersAsEntities.stream().map(rider -> new RiderResponse(rider)).collect(Collectors.toList());
    }

    public static List<RiderResponse> RiderResponsesFromEntities(Set<Rider> ridersAsEntities){
        return ridersAsEntities.stream().map(rider -> new RiderResponse(rider)).collect(Collectors.toList());
    }
}
