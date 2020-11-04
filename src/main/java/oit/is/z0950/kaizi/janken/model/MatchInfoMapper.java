package oit.is.z0950.kaizi.janken.model;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Insert;

@Mapper
public interface MatchInfoMapper {

  @Insert("INSERT INTO match_info (user_1,user_2,is_active) VALUES (#{user_1},#{user_2},#{is_active});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertMatchInfo(MatchInfo MatchInfo);
}
