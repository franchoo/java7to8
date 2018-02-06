package co.chlg.starwars.service;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

@Service
public class VehiclesService implements StarWarsService {

  private RestOperations rest;

  public VehiclesService(@Value("${api.starwars.base-url}") String rootUri,
      RestTemplateBuilder builder) {
    rest = builder.rootUri(rootUri + "/vehicles").build();
  }

  @Override
  public RestOperations rest() {
    return rest;
  }

  @Override
  public BiFunction<String, ?, ?> replacing() {
    final List<String> keep = Arrays.asList();
    return (key, value) -> keep.contains(key) ? value : null;
  }

}
