package co.chlg.starwars.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/vehicles", produces = APPLICATION_JSON_VALUE)
public class VehiclesController {

  @GetMapping
  private List<Map<String, ?>> getVehicles(@RequestParam("q") String search) {
    return null;
  }

  @GetMapping("/{vehicle}/lifespan")
  private Duration getVehicleLifespan(@PathVariable("vehicle") String vehicle) {
    return null;
  }

}
