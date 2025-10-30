package ee.kristiina.rendipood.controller;

import ee.kristiina.rendipood.entity.Film;
import ee.kristiina.rendipood.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FilmController {
    @Autowired
    private FilmRepository filmRepository;

    // add a film
    @PostMapping("Film")
    public List<Film> addFilm(@RequestBody Film film){
        filmRepository.save(film);
        return filmRepository.findAll();
    }

    // remove a film
    @DeleteMapping
    public void deleteFilm(@RequestBody Film film){
        filmRepository.delete(film);

    }

    // Change the type of film
    @PutMapping("Film")
    public List<Film> changeTypeOfFilm(@RequestBody Film film){
        filmRepository.findFilmById(film.getId());
        return filmRepository.findAll();
    }

    // list all films
    @GetMapping("Film")
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    // List all films in store (e.g. not rented at the moment)
    public Film findFilmByInStore(boolean inStore){
        return filmRepository.findFilmByInStore(inStore);
    }
}
