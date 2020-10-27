package oit.is.z0950.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MatchMapper {
  @Select("SELECT id,user_1,user_2,user_1_hand,user_2_hand from matches")
  ArrayList<Match> selectAll();
}
