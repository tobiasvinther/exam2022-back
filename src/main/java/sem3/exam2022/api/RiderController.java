package sem3.exam2022.api;

import org.springframework.web.bind.annotation.*;
import sem3.exam2022.dtos.RiderRequest;
import sem3.exam2022.dtos.RiderResponse;
import sem3.exam2022.services.RiderService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/riders")
public class RiderController {

    RiderService riderService;

    public RiderController(RiderService riderService) {
        this.riderService = riderService;
    }

    @GetMapping()
    public List<RiderResponse> getAllRiders() {
        return riderService.getAllRiders();
    }

    @GetMapping("/{id}")
    public RiderResponse getRiderById(@PathVariable int id) throws Exception {
        return riderService.getRiderById(id);
    }

    @GetMapping("/team/{id}")
    public List<RiderResponse> getRidersFromTeam(@PathVariable int id) throws Exception {
        return riderService.getRidersFromTeam(id);
    }

    @PostMapping("/add-rider")
    public RiderResponse addRider(@RequestBody RiderRequest requestBody) {
        return riderService.addRider(requestBody);
    }

    @PatchMapping("/edit/{id}")
    public RiderResponse editCandidate(@PathVariable int id, @RequestBody RiderRequest requestBody) throws Exception {
        return riderService.editRider(id, requestBody);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRider(@PathVariable int id) throws Exception {
        riderService.deleteRider(id);
    }
}
