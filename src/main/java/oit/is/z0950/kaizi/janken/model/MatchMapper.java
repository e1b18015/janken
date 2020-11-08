package oit.is.z0950.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MatchMapper {
  @Select("SELECT * from matches")
  ArrayList<Match> selectAllMatches();

  @Select("SELECT * from matches where user_2_hand ='wait'")
  ArrayList<Match> selectWaitMatches();

  @Select("SELECT * from matches where is_active = #{is_active}")
  ArrayList<Match> selectByActive(boolean is_active);

  @Insert("INSERT INTO matches (user_1,user_2,user_1_hand,user_2_hand,is_active) VALUES (#{user_1},#{user_2},#{user_1_hand},#{user_2_hand},#{is_active});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertMatch(Match Match);

  @Update("UPDATE matches SET user_2_hand = #{user_2_hand},is_active = true where id = #{id}")
  void waitMatchUpdate(int id,String user_2_hand);

  @Update("UPDATE matches SET is_active=false WHERE is_active=true;")
  void toNonActiveMatch();
}
