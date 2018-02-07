package co.chlg.starwars.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import co.chlg.starwars.service.PlanetsService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/planets", produces = APPLICATION_JSON_VALUE)
public class PlanetsController {

  @Autowired
  private PlanetsService planetsService;

  @GetMapping
  private List<Map<String, ?>> getPlanets(@RequestParam("q") String search) {
    return planetsService.get(search);
  }

}
