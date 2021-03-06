package sem3.exam2022.services;

import org.springframework.stereotype.Service;
import sem3.exam2022.dtos.RiderRequest;
import sem3.exam2022.dtos.RiderResponse;
import sem3.exam2022.entities.Rider;
import sem3.exam2022.entities.Team;
import sem3.exam2022.repositories.RiderRepository;
import sem3.exam2022.repositories.TeamRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RiderService {

    RiderRepository riderRepository;
    TeamRepository teamRepository;

    public RiderService(RiderRepository riderRepository, TeamRepository teamRepository) {
        this.riderRepository = riderRepository;
        this.teamRepository = teamRepository;
    }

    public List<RiderResponse> getAllRiders() {
        List<Rider>riderEntities = riderRepository.findAll();
        return RiderResponse.RiderResponsesFromEntities(riderEntities);
    }

    public RiderResponse getRiderById(int id) throws Exception {
        Rider riderAsEntity = riderRepository.findById(id).orElseThrow(() -> new Exception("No rider with provided id found"));
        return new RiderResponse(riderAsEntity);
    }

    public List<RiderResponse> getRidersFromTeam(int id) throws Exception {
        Team team = teamRepository.findById(id).orElseThrow(()-> new Exception("No team with provided id found"));
        List<Rider>riderEntities = new ArrayList<>(team.getTeamMembers());
        return RiderResponse.RiderResponsesFromEntities(riderEntities);
    }

    public RiderResponse addRider(RiderRequest requestBody) {
        Team team = teamRepository.findTeamByTeamName(requestBody.getTeamName());
        System.out.println("Found team: " + team);
        RiderRequest riderRequest = new RiderRequest(requestBody.getName(), requestBody.getCountry(), team);
        Rider newRider = new Rider(riderRequest);
        riderRepository.save(newRider);
        return new RiderResponse(newRider);
    }

    public RiderResponse editRider(int id, RiderRequest requestBody) throws Exception {
        Rider riderToEdit = riderRepository.findById(id).orElseThrow(() -> new Exception("No rider with this id exists"));
        riderToEdit.setName(requestBody.getName());
        riderToEdit.setCountry(requestBody.getCountry());
        Team team = teamRepository.findTeamByTeamName(requestBody.getTeamName());
        riderToEdit.setTeam(team);
        riderRepository.save(riderToEdit);
        return new RiderResponse(riderToEdit);
    }

    public void deleteRider(int id) throws Exception {
        Rider riderToDelete = riderRepository.findById(id).orElseThrow(() -> new Exception("No rider with this id exists"));
        riderToDelete.getTeam().deleteRiderFromTeam(riderToDelete);
        riderRepository.delete(riderToDelete);
        System.out.println("Rider: " + riderToDelete.getName() + " deleted");
    }

}
