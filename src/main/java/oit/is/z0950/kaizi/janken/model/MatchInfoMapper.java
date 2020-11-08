package oit.is.z0950.kaizi.janken.model;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MatchInfoMapper {

  @Insert("INSERT INTO match_info (user_1,user_2,is_active) VALUES (#{user_1},#{user_2},#{is_active});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertMatchInfo(MatchInfo MatchInfo);

  @Update("UPDATE match_info SET is_active=false WHERE is_active=true;")
  void toNonActiveMatchInfo();
}
