package oit.is.z0950.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z0950.kaizi.janken.model.User;
import oit.is.z0950.kaizi.janken.model.UserMapper;
import oit.is.z0950.kaizi.janken.model.Match;
import oit.is.z0950.kaizi.janken.model.MatchMapper;
import oit.is.z0950.kaizi.janken.model.MatchInfo;
import oit.is.z0950.kaizi.janken.model.MatchInfoMapper;

@Controller
@RequestMapping("/lec02")
public class Lec02Controller {

  @Autowired
  UserMapper UserMapper;

  @Autowired
  MatchMapper MatchMapper;

  @Autowired
  MatchInfoMapper MatchInfoMapper;

  @GetMapping
  public String lec02(Principal prin, ModelMap model, ModelMap model2, ModelMap model3) {
    ArrayList<User> User = UserMapper.selectAllUsers();
    String loginUser = prin.getName();
    ArrayList<Match> Match = MatchMapper.selectAllMatches();
    model.addAttribute("User", User);
    model2.addAttribute("loginUser", loginUser);
    model3.addAttribute("Match", Match);
    return "lec02.html";
  }

  @GetMapping("match")
  public String match(@RequestParam Integer id, Principal prin, ModelMap model, ModelMap model2) {
    User Enemy = UserMapper.selectById(id);
    User loginUser = UserMapper.selectByUser(prin.getName());
    int loginId = loginUser.getId();
    MatchInfo newMatchInfo = new MatchInfo(loginId, id, true);
    MatchInfoMapper.insertMatchInfo(newMatchInfo);
    model.addAttribute("Enemy", Enemy);
    model2.addAttribute("loginUser", loginUser);
    return "match.html";
  }

  @GetMapping("hoi")
  public String hoi(@RequestParam String hand, @RequestParam int id, Principal prin, ModelMap model1, ModelMap model2,
      ModelMap model3, ModelMap model4, ModelMap model5) {
    User loginUser = UserMapper.selectByUser(prin.getName());
    User Enemy = UserMapper.selectById(id);
    int loginId = loginUser.getId();
    String winner = "You Lose...";
    if (hand.equals("Gu")) {
      winner = "Draw";
    }
    if (hand.equals("Pa")) {
      winner = "You Win!!";
    }
    Match newMatch = new Match(loginId, id, hand, "Gu", true);
    MatchMapper.insertMatch(newMatch);
    model1.addAttribute("yourHand", hand);
    model2.addAttribute("enemyHand", "Gu");
    model3.addAttribute("jankenResult", winner);
    model4.addAttribute("loginUser", loginUser);
    model5.addAttribute("Enemy", Enemy);
    return "match.html";
  }

}
