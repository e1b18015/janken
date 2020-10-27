package oit.is.z0950.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import oit.is.z0950.kaizi.janken.model.User;
import oit.is.z0950.kaizi.janken.model.UserMapper;
import oit.is.z0950.kaizi.janken.model.Match;
import oit.is.z0950.kaizi.janken.model.MatchMapper;

@Controller
@RequestMapping("/lec02")
public class Lec02Controller {

  @Autowired
  UserMapper UserMapper;

  @Autowired
  MatchMapper MatchMapper;

  @RequestMapping()
  public String lec02(Principal prin, ModelMap model, ModelMap model2, ModelMap model3) {
    ArrayList<User> User = UserMapper.selectAll();
    String loginUser = prin.getName();
    ArrayList<Match> Match = MatchMapper.selectAll();
    model.addAttribute("User", User);
    model2.addAttribute("loginUser", loginUser);
    model3.addAttribute("Match", Match);
    return "lec02.html";
  }

}
