package ee.kristiina.rendipood.repository;

import ee.kristiina.rendipood.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
