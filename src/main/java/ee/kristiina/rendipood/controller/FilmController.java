package ee.kristiina.rendipood.controller;

import ee.kristiina.rendipood.entity.Film;
import ee.kristiina.rendipood.entity.FilmType;
import ee.kristiina.rendipood.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FilmController {
    @Autowired
    private FilmRepository filmRepository;

    // list all films
    @GetMapping("films")
    public List<Film> getFilm(){
        return filmRepository.findAll();
    }
    // add a film
    @PostMapping("film")
    public List<Film> addFilm(@RequestBody Film film){
        film.setDays(0);
        film.setRental(null);
        filmRepository.save(film);
        return filmRepository.findAll();
    }

    // variant 1
    //localhost:8080/films
    @DeleteMapping("films")
    public List<Film> deleteFilm(@RequestParam Long id){
        filmRepository.deleteById(id);
        return filmRepository.findAll();
    }

    // variant 2
    //localhost:8080/films2/2
    @DeleteMapping("films2/{id}")
    public List<Film> deleteProduct2(@PathVariable Long id){
        filmRepository.deleteById(id);
        return filmRepository.findAll();
    }

    // Change the type of film
//    @PutMapping("films")
//    public List<Film> updateFilm(@RequestBody Film film){
//        filmRepository.save(film);
//        return filmRepository.findAll();
//    }

    // localhost:8080/change-type?id=1&newType=Old
    @PatchMapping("change-type")
    public List<Film> changeFilmType(@RequestParam Long id, @RequestParam FilmType newType){
        Film film = filmRepository.findById(id).orElseThrow();
        film.setType(newType);
        filmRepository.save(film);
        return filmRepository.findAll();
    }

    // ühe võtmine
    //localhost:8080/products/3
    @GetMapping("products/{id}")
    public Film getFilm(@PathVariable Long id){

        //return productRepository.findById(id).get();
        //return productRepository.findById(id).orElse(null);
        return filmRepository.findById(id).orElseThrow();
    }

//    public List<Film> changeTypeOfFilm(@RequestBody Film film){
//        filmRepository.findFilmById(film.getId());
//        return filmRepository.findAll();
//    }



//    // List all films in store (e.g. not rented at the moment)
//    public Film findFilmByInStore(boolean inStore){
//        return filmRepository.findFilmByInStore(inStore);
//    }
// list all films
@GetMapping("available-films")
public List<Film> availableFilms(){
    return filmRepository.findByDays(0);
}

}
