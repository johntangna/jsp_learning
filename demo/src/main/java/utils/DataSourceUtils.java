package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import javax.sql.StatementEvent;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DataSourceUtils {

	private static DataSource dataSource;

	static {
		try {
			Properties properties= new Properties();
//			通过类加载器获取db.properties的配置
			InputStream is = DbUtils.class.getClassLoader()
					.getResourceAsStream("db.properties");
			properties.load(is);
//			创建druid数据源
			dataSource = DruidDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("初始化数据库连接池失败");
		}
	}

	/**
	 * 获取数据源
	 */
	public static DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * 获取数据库连接
	 */
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	/**
	 * 关闭资源
	 */
	public static void close(Connection connection, Statement statement, ResultSet resultSet) {
		try {
			if (resultSet != null) resultSet.close();
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		} catch (Exception ignored) {}
	}

}
