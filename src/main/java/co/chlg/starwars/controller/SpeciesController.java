package co.chlg.starwars.controller;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import co.chlg.starwars.service.PeopleService;
import co.chlg.starwars.service.PlanetsService;
import co.chlg.starwars.service.SpeciesService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/species", produces = APPLICATION_JSON_VALUE)
public class SpeciesController {

  @Autowired
  private SpeciesService speciesService;
  @Autowired
  private PlanetsService planetsService;
  @Autowired
  private PeopleService peopleService;

  @GetMapping
  private List<Map<String, ?>> getSpecies(@RequestParam("q") String search) throws Exception {
    return speciesService.get(search);
  }

  @GetMapping("/{species}/in/{planet}")
  private List<Map<String, ?>> getSpeciesPeopleInPlanet(@PathVariable("species") String species,
      @PathVariable("planet") String planet) throws Exception {
    final List<?> people = speciesService.getAttributeValues(species, "people");
    final List<?> residents = planetsService.getAttributeValues(planet, "residents");
    return people.stream().filter(residents::contains).map(String::valueOf)
        .map(peopleService::getEntity).collect(toList());
  }

}
