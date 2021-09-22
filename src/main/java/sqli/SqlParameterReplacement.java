package sqli;

import static utils.SqlUtils.getJDBCConnection;
import static utils.SqlUtils.getUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import utils.UserData;

public class SqlParameterReplacement {
  public ResultSet parameterReplacement1(HttpServletRequest req)
      throws SQLException {
    List<String> users = new ArrayList<>();
    users.add(req.getParameter("User_1"));
    users.add(req.getParameter("User_2"));
    Statement statement = getJDBCConnection().createStatement();
    String previousUser = "id_" + users.remove(users.size() - 1);
    users.add(req.getParameter("User_3"));
    return statement.executeQuery("SELECT first_name FROM user_data WHERE userid = '" + previousUser + "'");
  }

  public ResultSet parameterReplacement2(HttpServletRequest req)
      throws SQLException {
    List<String> users = new ArrayList<>();
    users.add(req.getParameter("User_1"));
    users.add(req.getParameter("User_2"));
    Statement statement = getJDBCConnection().createStatement();
    String previousUser = "id_" + users.remove(users.size() - 1);
    users.clear();
    return statement.executeQuery("SELECT first_name FROM user_data WHERE userid = '" + previousUser + "'");
  }

  public ResultSet parameterReplacement3(HttpServletRequest req)
      throws SQLException {
    String prefix = "a_";
    Statement statement = getJDBCConnection().createStatement();
    String previousUser = "id_" + getUser(req, prefix);
    prefix = "b_";
    return statement.executeQuery("SELECT first_name FROM user_data WHERE userid = '" + previousUser + "'");
  }

  public ResultSet parameterReplacement4(HttpServletRequest req)
      throws SQLException {
    Statement statement = getJDBCConnection().createStatement();
    UserData userData = new UserData(req.getParameter("userid"), "guest");
    String userId = "id_" + userData.getUserId();
    userData.setUserId("new_id");
    return statement.executeQuery("SELECT first_name FROM user_data WHERE userid = '" + userId + "'");
  }
}
