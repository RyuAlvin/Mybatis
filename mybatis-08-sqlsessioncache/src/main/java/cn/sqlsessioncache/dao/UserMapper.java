package cn.sqlsessioncache.dao;

import cn.sqlsessioncache.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User selectUserById(@Param("id") int id);

    Integer updateUser(User user);
}
