package model;

import java.sql.Date;

public interface ISchedualable {
  void schedule(Date date, String time);
}
