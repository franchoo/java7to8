package co.chlg.starwars.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/films", produces = APPLICATION_JSON_VALUE)
public class FilmsController {

  @GetMapping
  private List<Map<String, ?>> getFilms(@RequestParam("film") String film) {
    return null;
  }

  @GetMapping("/{film}/starpilots")
  private List<Map<String, ?>> getFilmStarpilots(@PathVariable("film") String film) {
    return null;
  }

}
