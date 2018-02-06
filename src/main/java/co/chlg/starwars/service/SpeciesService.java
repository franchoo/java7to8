package co.chlg.starwars.service;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

@Service
public class SpeciesService implements StarWarsService {

  private RestOperations rest;

  public SpeciesService(@Value("${api.starwars.base-url}") String rootUri,
      RestTemplateBuilder builder) {
    rest = builder.rootUri(rootUri + "/species").build();
  }

  @Override
  public RestOperations rest() {
    return rest;
  }

  @Override
  public BiFunction<String, ?, ?> replacing() {
    final List<String> keep = Arrays
        .asList("name", "classification", "designation", "average_height", "average_lifespan",
            "eye_colors", "hair_colors", "skin_colors", "language");
    return (key, value) -> keep.contains(key) ? value : null;
  }

}
