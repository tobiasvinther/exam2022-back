package sem3.exam2022.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sem3.exam2022.entities.Country;

public interface CountryRepository extends JpaRepository<Country, String> {
}
