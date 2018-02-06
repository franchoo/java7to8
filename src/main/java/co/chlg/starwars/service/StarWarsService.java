package co.chlg.starwars.service;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

public interface StarWarsService {

  RestOperations rest();

  BiFunction<String, ?, ?> replacing();

  @SuppressWarnings("unchecked")
  default List<Map<String, ?>> get(String search) throws Exception {
    List<Map<String, ?>> results = (List<Map<String, ?>>) getByUrl("/?search={search}", search)
        .get("results");
    if (results.size() > 10) {
      throw new Exception("Too many results");
    } else {
      results.forEach(entity -> ((Map) entity).replaceAll(replacing()));
      return results;
    }
  }

  @SuppressWarnings("unchecked")
  default Map<String, ?> getEntity(String url) {
    Map<String, ?> entity = getByUrl(url);
    ((Map) entity).replaceAll(replacing());
    return entity;
  }

  @SuppressWarnings("unchecked")
  default List<?> getAttributeValues(String search, String attribute) throws Exception {
    List<Map<String, ?>> results = (List<Map<String, ?>>) getByUrl("/?search={search}", search)
        .get("results");
    if (results.size() == 1) {
      return (List<?>) results.get(0).get(attribute);
    } else {
      throw new Exception("Too many results");
    }
  }

  @SuppressWarnings("unchecked")
  default Map<String, ?> getByUrl(String url, Object... uriVariables) {
    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.USER_AGENT, "swapi-Java-Yoda-Replies");
    HttpEntity entity = new HttpEntity(headers);
    final ResponseEntity<Map> response = rest()
        .exchange(url, HttpMethod.GET, entity, Map.class, uriVariables);
    return response.getBody();
  }

}
