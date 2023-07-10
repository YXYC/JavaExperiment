package ExperimentSourceCode_7.Chat.Server.JDBC;
import java.sql.*;

/**
 * 功能：数据库连接管理类
 */
public class ConnectionManager {
    //数据库连接属性
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://123.249.95.52:3306/Chat?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD ="wangweiyyds";

    /**
     * 私有化构造方法，拒绝实例化
     */
    private ConnectionManager() {

    }

    /**
     * 获取数据库连接静态方法
     * @return 数据库连接
     */
    public static Connection getConnection() {
        //定义数据库连接
        Connection conn = null;

        try {
            //安装数据库驱动
            Class.forName(DRIVER);
            //获取数据库连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            //提示用户数据库里连接成功
            System.out.println("提示：数据库连接成功~");
        } catch (ClassNotFoundException e) {
            System.err.println("异常：数据库驱动程序未找到！");
        } catch (SQLException e) {
            System.err.println("异常：数据库连接失败！");
        }

        //返回数据库连接
        return conn;
    }

    /**
     * 关闭数据库连接静态方法
     */
    public static void closeConnection(Connection conn) {
        //判断连接是否为空
        if (conn != null) {
            try {
                //判断连接是否关闭
                if (!conn.isClosed()) {
                    //关闭数据库连接,释放资源
                    conn.close();
                    //提示用户
                    System.out.println("提示：数据库连接关闭~");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 测试
     */
    public static void main(String[] args) {
        Connection conn = ConnectionManager.getConnection();
        String strSQL = "select * from user;";
        // 4. 执行SQL查询，返回结果集
        try {
            // 3. 创建语句对象
            Statement stmt = conn.createStatement();
            // 4. 执行SQL查询，返回结果集
            ResultSet rs = stmt.executeQuery(strSQL);
            while(rs.next()){
                System.out.println(rs.getString("username")+"\t"+rs.getString("password")+"\t"+rs.getString("name")+"\t"+rs.getString("isOline"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ConnectionManager.closeConnection(conn);
    }
}