package com.cicdlectures.menuserver.service;

import java.util.HashSet;
import java.util.List;
import java.util.Arrays;
import com.cicdlectures.menuserver.repository.MenuRepository;
import com.cicdlectures.menuserver.service.CreateMenuService;
import com.cicdlectures.menuserver.repository.DishRepository;
import com.cicdlectures.menuserver.dto.MenuDto;
import com.cicdlectures.menuserver.dto.DishDto;
import com.cicdlectures.menuserver.model.Menu;
import com.cicdlectures.menuserver.model.Dish;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateMenuServiceTests {
    
    private CreateMenuService createMenuService;
    private MenuRepository menuRepository;
    private DishRepository dishRepository;

    @BeforeEach
  public void init() {
    this.menuRepository = mock(MenuRepository.class);
    this.dishRepository = mock(dishRepository.class);
    this.createMenuService = new CreateMenuService(this.menuRepository, this.menuRepository);
  }
  
  @Test
  @DisplayName("lists all known menus")
  public void firstListsKnownMenus() {
    // Défini une liste de menus avec un menus.
  Iterable<MenuDto> existingMenus = Arrays.asList(
    new MenuDto(
      Long.valueOf(1),
      "Christmas menu",
      new HashSet<>(
        Arrays.asList(
          new Dish(Long.valueOf(1), "Turkey", null),
          new Dish(Long.valueOf(2), "Pecan Pie", null),
          new Dish(Long.valueOf(3), "Turkey", null)
        )
      )
    )
  );

  }
}
