package co.chlg.starwars.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.net.URI;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpeciesControllerTest {

  private static final Logger log = Logger.getLogger(SpeciesControllerTest.class);

  private URI url;
  @LocalServerPort
  private int port;
  @Autowired
  private TestRestTemplate testRest;

  @Before
  public void setUp() {
    url = URI.create("http://localhost:" + port + "/species/");
  }

  @Test
  @SuppressWarnings("unchecked")
  public void getHuman() {
    // Given...
    URI uri = url.resolve("?q=human");
    // When...
    ResponseEntity<List> response = testRest.getForEntity(uri, List.class);
    // Then...
    assertThat(response.getStatusCode(), is(OK));
    assertThat(response.getHeaders().getContentType().toString(),
        startsWith(APPLICATION_JSON_VALUE));
    assertThat(response.getBody(), notNullValue());
    List<Map> body = (List<Map>) response.getBody();
    assertThat(body.size(), is(1));
    assertThat(body.get(0).keySet(), hasItem("classification"));
    assertThat(body.get(0).get("classification"), is("mammal"));
    log.info(response.getBody());
  }

}
