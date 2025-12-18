package dao;

import domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import utils.DataSourceUtils;

import java.sql.SQLException;

public class UserDao {

    /**
     * 根据用户名查询用户
     * @return
     */
    public User getUserByUsername(String username) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from user where username=?";
        return queryRunner.query(sql, new BeanHandler<>(User.class), username);
    }
}
