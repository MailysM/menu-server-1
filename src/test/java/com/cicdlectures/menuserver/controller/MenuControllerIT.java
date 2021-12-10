package com.cicdlectures.menuserver.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Arrays;
import java.net.URL;

import com.cicdlectures.menuserver.repository.MenuRepository;
import com.cicdlectures.menuserver.dto.MenuDto;
import com.cicdlectures.menuserver.dto.DishDto;
import com.cicdlectures.menuserver.model.Menu;
import com.cicdlectures.menuserver.model.Dish;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;


// Lance l'application sur un port aléatoire.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// Indique de relancer l'application à chaque test.
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class MenuControllerIT {

  @LocalServerPort
  private int port;

  private URL getMenusURL() throws Exception {
    return new URL("http://localhost:" + port + "/menus");
  }

  // Injecte automatiquement l'instance du menu repository
@Autowired
private MenuRepository menuRepository;

// Injecte automatiquement l'instance du TestRestTemplate
@Autowired
private TestRestTemplate template;


@Test
@DisplayName("lists all known menus")
public void listsAllMenus() throws Exception {
    //Creer un menu dans le menuRepository
    Menu existingMenus =
    new Menu(
      Long.valueOf(1),
      "Christmas menu",
      new HashSet<>(
        Arrays.asList(
          new Dish(Long.valueOf(1), "Turkey", null),
          new Dish(Long.valueOf(2), "Pecan Pie", null)
        )
      )
  );

  Iterable<MenuDto> wantMenus = Arrays.asList(
        new MenuDto(
        Long.valueOf(1),
        "Christmas menu",
        new HashSet<>(
            Arrays.asList(
            new DishDto(Long.valueOf(1), "Turkey"),
            new DishDto(Long.valueOf(2), "Pecan Pie")
            )
        )
        )
    );
   existingMenus = menuRepository.save(existingMenus);
    // Effectue une requête GET /menus
   ResponseEntity<MenuDto[]> response = this.template.getForEntity(getMenusURL().toString(), MenuDto[].class);

   //Parse le payload de la réponse sous forme d'array de MenuDto
   MenuDto[] gotMenus = response.getBody();

   //Verifie que la réponse est 200
   HttpStatus statusCode = response.getStatusCode();
   assertEquals(HttpStatus.OK, statusCode);

   //Verifie que le menuDto est ok
   // On défini wantMenus, les résultats attendus
   
   assertEquals(wantMenus,gotMenus);
}
}