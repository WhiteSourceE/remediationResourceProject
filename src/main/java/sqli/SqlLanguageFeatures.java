package sqli;

import static utils.SqlUtils.getJDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;

public class SqlLanguageFeatures {
  void languageFeatures1(HttpServletRequest req) throws SQLException {

    String sql;
    String userid = req.getParameter("userid");
    Connection connection = getJDBCConnection();
    try (Statement statement = connection.createStatement()) {
      sql = "SELECT first_name FROM user_data WHERE userid = '"
          + userid + "'";
      statement.executeQuery(sql);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  void example2(HttpServletRequest req) throws SQLException {

    Connection dbConnection = null;
    PreparedStatement sqlStatement = null;
    String data = Integer.parseInt(req.getParameter("index")) == 1 ?
        "id_" + req.getParameter("one") : "id_" + req.getParameter("two");
    dbConnection = getJDBCConnection();

    sqlStatement = dbConnection.prepareStatement(
        "insert into users (status) values ('updated') where name='" + data + "'");

    sqlStatement.execute();
  }
}
