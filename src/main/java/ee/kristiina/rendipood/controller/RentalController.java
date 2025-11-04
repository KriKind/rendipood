package ee.kristiina.rendipood.controller;

import ee.kristiina.rendipood.entity.Film;
import ee.kristiina.rendipood.entity.Rental;
import ee.kristiina.rendipood.model.RentalFilm;
import ee.kristiina.rendipood.repository.FilmRepository;
import ee.kristiina.rendipood.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RentalController {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping("rentals")
    public List<Rental> findAll() {
        return rentalRepository.findAll();
    }

    @PostMapping("start-rental")
    public Rental startRental(@RequestBody List<RentalFilm> rentalFilms) {
        Rental rental = new Rental();
        //Rental dbRental = rentalRepository.save(rental); siin on Rentali id

        List<Film> films = new ArrayList<>();
        for (RentalFilm rentalFilm : rentalFilms) {
            if (rentalFilm.getRentedDays() <= 0 ){
                throw new RuntimeException("Rental days must be greater than 0");
            }
            Film film = filmRepository.findById(rentalFilm.getFilmId()).orElseThrow();
            if (film.getDays() > 0 ){
                throw new RuntimeException("Film already rented");
            }
            // film.setRental(dbRental)
            film.setDays(rentalFilm.getRentedDays());
            film.setRental(rental);
            films.add(film);
        }

        rental.setFilms(films);
        double sum = 0;
        //TODO: tsükli sees suurendame summat
        rental.setInitialFee(sum);
        return rentalRepository.save(rental);
    }
}