package oit.is.z0950.kaizi.janken.model;

public class Match {
  int id;
  int user_1;
  int user_2;
  String user_1_hand;
  String user_2_hand;
  boolean is_active;

  public Match() {

  }

  public Match(int u1, int u2, String h1, String h2, boolean a) {
    id = 0;
    user_1 = u1;
    user_2 = u2;
    user_1_hand = h1;
    user_2_hand = h2;
    is_active = a;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUser_1() {
    return user_1;
  }

  public void setUser_1(int user_1) {
    this.user_1 = user_1;
  }

  public int getUser_2() {
    return user_2;
  }

  public void setUser_2(int user_2) {
    this.user_2 = user_2;
  }

  public String getUser_1_hand() {
    return user_1_hand;
  }

  public void setUser_1_hand(String user_1_hand) {
    this.user_1_hand = user_1_hand;
  }

  public String getUser_2_hand() {
    return user_2_hand;
  }

  public void setUser_2_hand(String user_2_hand) {
    this.user_2_hand = user_2_hand;
  }

  public boolean isIs_active() {
    return is_active;
  }

  public void setIs_active(boolean is_active) {
    this.is_active = is_active;
  }

}
