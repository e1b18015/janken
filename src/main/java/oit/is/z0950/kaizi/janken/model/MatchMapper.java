package oit.is.z0950.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;

@Mapper
public interface MatchMapper {
  @Select("SELECT id,user_1,user_2,user_1_hand,user_2_hand,is_active from matches")
  ArrayList<Match> selectAllMatches();

  @Insert("INSERT INTO matches (user_1,user_2,user_1_hand,user_2_hand,is_active) VALUES (#{user_1},#{user_2},#{user_1_hand},#{user_2_hand},#{is_active});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertMatch(Match Match);
}
