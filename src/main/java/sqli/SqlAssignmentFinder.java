package sqli;

import static utils.SqlUtils.getJDBCConnection;
import static utils.SqlUtils.getSafeConnection;
import static utils.SqlUtils.getStatement;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

public class SqlAssignmentFinder {

  public void sqlTest1(HttpServletRequest request) throws SQLException {
    String ip = request.getParameter("ip");
    try {
      Statement statement1 = getJDBCConnection().createStatement();
      Statement statement2 = getSafeConnection().createStatement();
      String sql1 =
          "INSERT INTO banned_ip(id, ip) VALUE('" + UUID.randomUUID().toString() + "','" + ip
              + "')";
      statement1.execute(sql1);
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    System.out.print("sdfsf");
  }

  public void sqlTest2(HttpServletRequest request) throws SQLException {
    String ip = request.getParameter("ip");
    try {
      Statement statement1 = getSafeConnection().createStatement();
      Statement statement2 = getStatement();
      String sql1 =
          "INSERT INTO banned_ip(id, ip) VALUE('" + UUID.randomUUID().toString() + "','" + ip
              + "')";
      statement2.execute(sql1);
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    System.out.print("sdfsf");
  }

}
