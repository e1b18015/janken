package oit.is.z0950.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Lec02Controller {

  @PostMapping("/lec02")
  public String lec02(@RequestParam String name, ModelMap model) {
    String name1 = name;
    model.addAttribute("playerName", name1);
    return "lec02.html";
  }

  @GetMapping("/hoi")
  public String hoi(@RequestParam String hand, ModelMap model1, ModelMap model2, ModelMap model3) {
    String winner = "You Lose...";
    if (hand.equals("Gu")) {
      winner = "Draw";
    }
    if (hand.equals("Pa")) {
      winner = "You Win!!";
    }
    model1.addAttribute("yourHand", hand);
    model2.addAttribute("enemyHand", "Gu");
    model3.addAttribute("jankenResult", winner);
    return "lec02.html";
  }

}
