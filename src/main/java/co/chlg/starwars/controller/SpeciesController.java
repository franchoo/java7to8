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
@RequestMapping(path = "/species", produces = APPLICATION_JSON_VALUE)
public class SpeciesController {

  @GetMapping
  private List<Map<String, ?>> getSpecies(@RequestParam("species") String species) {
    return null;
  }

  @GetMapping("/{species}/in/{planet}")
  private List<Map<String, ?>> getSpeciesPeopleInPlanet(@PathVariable("species") String species,
      @PathVariable("planet") String planet) {
    return null;
  }

}
