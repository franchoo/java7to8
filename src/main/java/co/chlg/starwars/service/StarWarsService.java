package co.chlg.starwars.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.client.RestOperations;

/**
 * Has use example for {@code default} methods on interfaces, method references, {@link Iterable#forEach(Consumer)},
 * {@link Collection#removeIf(Predicate)}, {@link Stream#findFirst()} and {@link Optional#orElseThrow(Supplier)}
 */
@SuppressWarnings("unchecked")
public interface StarWarsService {

  RestOperations rest();

  Collection<String> removables();

  /**
   * Realiza una busqueda de entidades (max 9) en la API publica y abierta de Star Wars,
   * ademas de remover las llaves definidas en {@link #removables()} a cada entidad resultante.
   *
   * @param search termino de busqueda
   * @return lista de entidades
   */
  default List<Map<String, ?>> get(String search) {
    List<Map<String, ?>> results = (List<Map<String, ?>>) getByUrl("/?search={search}", search)
        .get("results");
    if (results.size() > 9) {
      // Esta exception arroja un HTTP 400 Bad request...
      throw new HttpMessageNotReadableException(String.format("Too many results for '%s'", search));
    }
    results.forEach(entity -> entity.keySet().removeIf(removables()::contains));
    return results;
  }

  /**
   * Realiza una busqueda de una sola entidad en la API publica y abierta de Star Wars,
   * recuperando una lista de valores desde el atributo especificado.
   *
   * @param search termino de busqueda
   * @param attribute donde se encuentra la lista de valores
   * @return lista de valores de un atributo de la entidad
   */
  default List<?> getAttributeValues(String search, String attribute) {
    List<Map<String, ?>> results = (List<Map<String, ?>>) getByUrl("/?search={search}", search)
        .get("results");
    if (results.size() > 1) {
      // Esta exception arroja un HTTP 400 Bad request...
      throw new HttpMessageNotReadableException(String.format("More that one for '%s'", search));
    }
    return results.stream().findFirst().map(entity -> (List) entity.get(attribute)).orElseThrow(
        // Esta exception arroja un HTTP 400 Bad request...
        () -> new HttpMessageNotReadableException(String.format("Not even one for '%s'", search)));
  }

  /**
   * Realiza una petición a la API publica y abierta de Star Wars por url,
   * ademas de remover las llaves definidas en {@link #removables()} a la entidad resultante.
   *
   * @param url relativa o completa a ser interpretada
   * @return cuerpo de la respuesta
   */
  default Map<String, ?> getEntity(String url) {
    Map<String, ?> entity = getByUrl(url);
    entity.keySet().removeIf(removables()::contains);
    return entity;
  }

  /**
   * Realiza una petición a la API publica y abierta de Star Wars por url.
   *
   * @param url relativa o completa a ser interpretada
   * @param uriVariables valores a reemplazar en la url
   * @return cuerpo de la respuesta
   */
  default Map<String, ?> getByUrl(String url, Object... uriVariables) {
    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.USER_AGENT, "swapi-Java-Yoda-Replies");
    HttpEntity entity = new HttpEntity(headers);
    final ResponseEntity<Map> response = rest()
        .exchange(url, HttpMethod.GET, entity, Map.class, uriVariables);
    return response.getBody();
  }

}
