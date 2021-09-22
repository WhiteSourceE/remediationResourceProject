package sqli;

import static utils.SqlUtils.getJDBCConnection;
import static utils.SqlUtils.pad;

import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;

public class SqlInter {
  public void sqlInter1(HttpServletRequest req) throws SQLException {
    final String input = req.getParameter("user_id");
    Sql sql = new Sql() {
      public String getSql() {
        return "safe_" + input;
      }

      @Override
      public String getNameQuery(String name) {
        return " AND name = '" + name + "'";
      }

      @Override
      public String getUserIdOrDefault(String userId) {
        return null;
      }
    };
    Statement statement = getJDBCConnection().createStatement();
    statement.executeQuery("SELECT first_name FROM user_data WHERE userid = '" + sql.getSql() + "'");
  }

  public void sqlInter2(HttpServletRequest req) throws SQLException {
    final String input = req.getParameter("user_id");
    Statement statement = getJDBCConnection().createStatement();
    statement.executeQuery("SELECT first_name FROM user_data WHERE userid = '" + pad(input) + "'");
  }

  interface Sql {

    String getSql();

    String getNameQuery(String name);

    String getUserIdOrDefault(String userId);
  }
}
