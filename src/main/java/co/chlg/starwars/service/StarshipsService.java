package co.chlg.starwars.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

@Service
public class StarshipsService implements StarWarsService {

  private RestOperations rest;
  private static Collection<String> removables = Arrays
      .asList("created", "edited", "films", "pilots", "url");

  public StarshipsService(@Value("${api.starwars.base-url}") String rootUri,
      RestTemplateBuilder builder) {
    rest = builder.rootUri(rootUri + "/starships").build();
  }

  @Override
  public RestOperations rest() {
    return rest;
  }

  @Override
  public Collection<String> removables() {
    return removables;
  }

  public static Predicate<Map<String, ?>> hyperdriveRatingAbove(float value) {
    return starship ->
        Optional.ofNullable((String) starship.get("hyperdrive_rating"))
            .map(Float::valueOf)
            .filter(rating -> rating > value)
            .isPresent();
  }

}
