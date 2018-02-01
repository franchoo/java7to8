package co.chlg.starwars.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/{species}", produces = APPLICATION_JSON_VALUE)
public class SpeciesController {

  @GetMapping
  private Map<String, ?> getSpecies(@PathVariable("species") String species) {
    return null;
  }

  @GetMapping("/in/{planet}")
  private List<?> getSpeciesInPlanet(@PathVariable("species") String species,
      @PathVariable("planet") String planet) {
    return null;
  }

}
