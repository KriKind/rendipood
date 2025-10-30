package ee.kristiina.rendipood.repository;

import ee.kristiina.rendipood.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film,Long> {
    void findFilmById(Long id);

    Film findFilmByInStore(boolean inStore);
}
