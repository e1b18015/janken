package oit.is.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Lec02Controller {
  @PostMapping("/lec02")
  public String lec02(@RequestParam String name, ModelMap model) {
    model.addAttribute("playerName", name);
    return "lec02.html";
  }

  @GetMapping("/hoi")
  public String sample23(@RequestParam String you, ModelMap model) {
    String winner = "Draw!";
    if (you.equals("Choki")) {
      winner = "You Win!";
    }
    if (you.equals("Pa")) {
      winner = "You Lose!";
    }
    model.addAttribute("yourHand", you);
    model.addAttribute("jankenResult", winner);
    return "lec02janken.html";
  }

}
