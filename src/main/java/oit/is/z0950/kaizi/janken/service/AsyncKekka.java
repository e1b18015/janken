package oit.is.z0950.kaizi.janken.service;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.beans.factory.annotation.Autowired;

import oit.is.z0950.kaizi.janken.model.Match;
import oit.is.z0950.kaizi.janken.model.MatchMapper;

@Service
public class AsyncKekka {

  @Autowired
  MatchMapper MatchMapper;

  private final Logger logger = LoggerFactory.getLogger(AsyncKekka.class);

  public ArrayList<Match> syncShowMatchList() {
    return MatchMapper.selectByActive(true);
  }

  @Async
  public void asyncMatchWait(SseEmitter emitter) {
    try {
      ArrayList<Match> TrueMatch = this.syncShowMatchList();
      while (true) {
        TimeUnit.MILLISECONDS.sleep(500);
        TrueMatch = this.syncShowMatchList();
        if (TrueMatch.size() != 0) {
          emitter.send("p1");
          emitter.complete();
          break;
        }
      }
    } catch (Exception e) {
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    } finally {
      emitter.complete();
    }
  }

}
