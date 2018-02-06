package co.chlg.starwars.service;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

@Service
public class PeopleService implements StarWarsService {

  private RestOperations rest;

  public PeopleService(@Value("${api.starwars.base-url}") String rootUri,
      RestTemplateBuilder builder) {
    rest = builder.rootUri(rootUri + "/people").build();
  }

  @Override
  public RestOperations rest() {
    return rest;
  }

  @Override
  public BiFunction<String, ?, ?> replacing() {
    List<String> keep = Arrays
        .asList("name", "birth_year", "eye_color", "gender", "hair_color", "height", "skin_color");
    return (key, value) -> keep.contains(key) ? value : null;
  }

}
