package sqli;

import static utils.SqlUtils.getJDBCConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;
import utils.UserData;

public class SqlScope {
  public ResultSet getUser(HttpServletRequest req)
      throws SQLException {
    String adminId = "";
    if (req != null) {
      UserData userData = new UserData();
      userData.setUserId(req.getParameter("userId"));
      adminId = "admin_" + userData.getUserId();
    }
    Statement statement = getJDBCConnection().createStatement();
    String sql = "SELECT first_name FROM user_data WHERE userid = '" + adminId + "'";
    return statement.executeQuery(sql);
  }
}
