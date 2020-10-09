package oit.is.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Lec02Controller {
  @PostMapping("/lec02")
  public String lec02(@PathVariable String name, ModelMap model) {
    model.addAttribute("playerName", name);
    return "lec02.html";
  }

  @GetMapping("/hoi")
  public String hoi(@PathVariable String hand, ModelMap model) {
    String winner = "You Lose...";
    if (hand.equals("Gu")) {
      winner = "Draw";
    }
    if (hand.equals("Pa")) {
      winner = "You Win!!";
    }
    model.addAttribute("yourHand", hand);
    model.addAttribute("enemyHand", "Gu");
    model.addAttribute("jankenResult", winner);
    return "lec02.html";
  }

}
