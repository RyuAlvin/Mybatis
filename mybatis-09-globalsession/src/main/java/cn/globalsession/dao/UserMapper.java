package cn.globalsession.dao;

import cn.globalsession.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User selectUserById(@Param("id") int id);
}
