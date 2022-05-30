package sem3.exam2022.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sem3.exam2022.entities.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {

    Team findTeamByTeamName(String teamName);
}
