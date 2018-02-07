package co.chlg.starwars.controller;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import co.chlg.starwars.service.FilmsService;
import co.chlg.starwars.service.PeopleService;
import co.chlg.starwars.service.StarshipsService;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/films", produces = APPLICATION_JSON_VALUE)
public class FilmsController {

  @Autowired
  private FilmsService filmsService;
  @Autowired
  private StarshipsService starshipsService;
  @Autowired
  private PeopleService peopleService;

  @GetMapping
  private List<Map<String, ?>> getFilms(@RequestParam("q") String search) {
    return filmsService.get(search);
  }

  @GetMapping("/{film}/starpilots")
  private List<Map<String, ?>> getFilmStarpilots(@PathVariable("film") String film) {
    final List<?> starships = filmsService.getAttributeValues(film, "starships");
    final Set<?> pilots = starships.stream().map(String::valueOf)
        .map(starshipsService::getByUrl).filter(StarshipsService.hyperdriveRatingAbove(1))
        .map(starship -> (List<?>) starship.get("pilots")).flatMap(List::stream).collect(toSet());
    return pilots.stream().map(String::valueOf).map(peopleService::getEntity).collect(toList());
  }

}
