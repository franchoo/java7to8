package co.chlg.starwars.service;

import java.util.List;
import java.util.Map;

public interface SpeciesService {

  List<Map<String, ?>> getSpecies(String search);

  List<?> getSpeciesAttributes(String search, String... attributes);

  List<?> getSpeciesAttributeValues(String search, String attribute);

}
