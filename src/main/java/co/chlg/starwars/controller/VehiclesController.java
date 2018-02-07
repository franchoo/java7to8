package co.chlg.starwars.controller;

import static java.util.Collections.max;
import static java.util.Collections.min;
import static java.util.stream.Collectors.toSet;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import co.chlg.starwars.service.FilmsService;
import co.chlg.starwars.service.VehiclesService;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Has use example for {@link LocalDate} and {@link Period}
 */
@RestController
@RequestMapping(path = "/vehicles", produces = APPLICATION_JSON_VALUE)
public class VehiclesController {

  @Autowired
  private VehiclesService vehiclesService;
  @Autowired
  private FilmsService filmsService;

  @GetMapping
  private List<Map<String, ?>> getVehicles(@RequestParam("q") String search) {
    return vehiclesService.get(search);
  }

  @GetMapping("/{vehicle}/lifespan")
  private Period getVehicleLifespan(@PathVariable("vehicle") String vehicle) {
    final Set<LocalDate> dates = vehiclesService.getAttributeValues(vehicle, "films").stream()
        .map(String::valueOf).map(filmsService::getEntity)
        .map(film -> (String) film.get("release_date"))
        .map(LocalDate::parse).collect(toSet());
    return Period.between(min(dates), max(dates));
  }

}
