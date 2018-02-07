package co.chlg.starwars.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

@SuppressWarnings("unchecked")
public interface StarWarsService {

  RestOperations rest();

  Collection<String> removables();

  /**
   * Realiza una busqueda de entidades (max 10) en la API publica y abierta de Star Wars
   *
   * @param search termino de busqueda
   * @return lista de entidades
   */
  default List<Map<String, ?>> get(String search) {
    List<Map<String, ?>> results = (List<Map<String, ?>>) getByUrl("/?search={search}", search)
        .get("results");
    if (results.size() > 10) {
      throw new IllegalArgumentException(search);
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
      throw new IllegalArgumentException(search);
    }
    return results.stream().findFirst().map(entity -> (List) entity.get(attribute)).get();
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
