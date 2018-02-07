package co.chlg.starwars.service;

import java.util.Arrays;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

@Service
public class SpeciesService implements StarWarsService {

  private RestOperations rest;
  private static Collection<String> removables = Arrays
      .asList("created", "edited", "films", "homeworld", "people", "url");

  public SpeciesService(@Value("${api.starwars.base-url}") String rootUri,
      RestTemplateBuilder builder) {
    rest = builder.rootUri(rootUri + "/species").build();
  }

  @Override
  public RestOperations rest() {
    return rest;
  }

  @Override
  public Collection<String> removables() {
    return removables;
  }

}
