package co.chlg.starwars.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/planets", produces = APPLICATION_JSON_VALUE)
public class PlanetsController {

  @GetMapping
  private List<Map<String, ?>> getPlanets(@RequestParam("planet") String planet) {
    return null;
  }

}
