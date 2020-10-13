package oit.is.z0950.kaizi.janken.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z0950.kaizi.janken.model.Room;

@Controller
@RequestMapping("/lec02")
public class Lec02Controller {

  @Autowired
  private Room room;

  @RequestMapping()
  public String lec02(Principal prin, ModelMap model, ModelMap model2) {
    String loginUser = prin.getName();
    this.room.addUser(loginUser);
    model2.addAttribute("loginUser", loginUser);
    model.addAttribute("room", this.room);

    return "lec02.html";
  }

  @GetMapping("/hoi")
  public String hoi(@RequestParam String hand, ModelMap model1, ModelMap model2, ModelMap model3, ModelMap model4,
      ModelMap model5, Principal prin) {
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
    String loginUser = prin.getName();
    this.room.addUser(loginUser);
    model5.addAttribute("loginUser", loginUser);
    model4.addAttribute("room", this.room);
    return "lec02.html";
  }

}
