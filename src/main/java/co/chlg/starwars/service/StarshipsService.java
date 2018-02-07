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

/**
 * Has use example for {@link Optional} and its methods
 */
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

  /**
   * Devuelve una función que valida si la starship que se provee,
   * posee un hyperdrive_rating mayor al {@code value} especificado.
   *
   * @param value umbral a ser superado en validación
   * @return función predicado validadora del hyperdrive_rating
   */
  public static Predicate<Map<String, ?>> hyperdriveRatingAbove(float value) {
    return starship ->
        Optional.ofNullable((String) starship.get("hyperdrive_rating"))
            .filter(text -> !"unknown".equals(text))
            .map(Float::valueOf)
            .filter(rating -> rating > value)
            .isPresent();
  }

}
