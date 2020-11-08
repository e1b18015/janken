package oit.is.z0950.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.z0950.kaizi.janken.model.User;
import oit.is.z0950.kaizi.janken.model.UserMapper;
import oit.is.z0950.kaizi.janken.model.Match;
import oit.is.z0950.kaizi.janken.model.MatchMapper;
import oit.is.z0950.kaizi.janken.model.MatchInfo;
import oit.is.z0950.kaizi.janken.model.MatchInfoMapper;
import oit.is.z0950.kaizi.janken.service.AsyncKekka;

@Controller
@RequestMapping("/lec02")
public class Lec02Controller {

  @Autowired
  UserMapper UserMapper;

  @Autowired
  MatchMapper MatchMapper;

  @Autowired
  MatchInfoMapper MatchInfoMapper;

  @Autowired
  AsyncKekka Kekka;

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
  public String hoi(@RequestParam String myname, @RequestParam String hand, @RequestParam int id, Principal prin,
      ModelMap model) {
    User loginUser = UserMapper.selectByUser(myname);
    int loginId = loginUser.getId();
    ArrayList<Match> waitMatch = MatchMapper.selectWaitMatches();
    if (waitMatch.size() != 0) {
      MatchMapper.waitMatchUpdate(waitMatch.get(0).getId(), hand);
    } else {
      Match newMatch = new Match(loginId, id, hand, "wait", false);
      MatchMapper.insertMatch(newMatch);
    }
    model.addAttribute("loginUser", loginUser);
    return "wait.html";
  }

  @GetMapping("kekka")
  public String kekka(Principal prin, ModelMap model, ModelMap model2, ModelMap model3) {
    ArrayList<User> User = UserMapper.selectAllUsers();
    String loginUser = prin.getName();
    ArrayList<Match> Match = MatchMapper.selectAllMatches();
    MatchMapper.toNonActiveMatch();
    MatchInfoMapper.toNonActiveMatchInfo();
    model.addAttribute("User", User);
    model2.addAttribute("loginUser", loginUser);
    model3.addAttribute("Match", Match);
    return "lec02.html";
  }

  @GetMapping("res")
  public SseEmitter res() {
    final SseEmitter sseEmitter = new SseEmitter();
    this.Kekka.asyncMatchWait(sseEmitter);
    return sseEmitter;
  }

}
