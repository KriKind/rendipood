package ee.kristiina.rendipood.controller;

import ee.kristiina.rendipood.entity.Film;
import ee.kristiina.rendipood.entity.Rental;
import ee.kristiina.rendipood.model.RentalFilm;
import ee.kristiina.rendipood.repository.FilmRepository;
import ee.kristiina.rendipood.repository.RentalRepository;
import ee.kristiina.rendipood.service.RentalService;
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
    private RentalService rentalService;

    @GetMapping("rentals")
    public List<Rental> findAll() {
        return rentalRepository.findAll();
    }

    @PostMapping("start-rental")
    public Rental startRental(@RequestBody List<RentalFilm> rentalFilms) {
        Rental rental = new Rental();
        //Rental dbRental = rentalRepository.save(rental); siin on Rentali id

        List<Film> films = new ArrayList<>();

        double sum = rentalService.getSumAndAddRental(rentalFilms, rental, films);
        rental.setInitialFee(sum);
        rental.setFilms(films);

        rental.setInitialFee(sum);
        return rentalRepository.save(rental);
    }

    @PostMapping("end-rental")
    public Rental endRental(@RequestBody List<RentalFilm> rentalFilms) {

//        List<Film> films = rentalService.getAllFilmsFromDB(rentalFilms);
//
//        Rental rental = rentalService.checkIfAllFilmsFromSameRental(films);

        Rental rental = rentalService.calculateLateFee(rentalFilms);

        return rentalRepository.save(rental);
    }


}