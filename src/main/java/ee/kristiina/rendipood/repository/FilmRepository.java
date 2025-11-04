package ee.kristiina.rendipood.repository;

import ee.kristiina.rendipood.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film,Long> {
    //SELECT * FROM films WERE days = 0
    List<Film> findByDays(int days);

    //@Query("select * from films")
    //List<Film> leiapaevadejargi(int days);
}
