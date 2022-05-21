package cn.anno.dao;

import cn.anno.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    @Select("select * from user")
    List<User> getUserList();

    /**
     * 关于@Param：用于给方法参数起一个名字
     *  在方法只接受一个参数的情况下，可以不使用@Param；
     *  在方法接受多个参数的情况下，建议一定要使用@Param注解给参数命名；
     *  如果参数是JavaBean，则不能使用@Param（例如User对象）；
     *
     * 关于#与$的区别：
     *  #{}的作用主要是替换预编译语句（PrepareStatement）中的占位符？
     *      insert into user(name) values(#{name});
     *      insert into user(name) values(?);
     *  ${}的作用是直接进行字符串替换，例子：如下getUserByName
     *      insert into user(name) values('${name}');
     *      insert into user(name) values('ryu');
     * @param id
     * @return
     */
    @Select("select * from user where id=#{id}")
//    User getUserById(int sssid); OK
//    User getUserById(int id); OK
//    User getUserById(@Param("id") int uid); OK
    User getUserById(@Param("id") int id);

    @Select("select * from user where name like '%${name}%'")
    List<User> getUserByName(String uname);

    @Insert("insert into user(id,name,pwd) values(#{id},#{name},#{pwd})")
    Integer addUser(User user);

    @Update("update user set name=#{name} where id=#{id}")
    Integer updateUser(User user);

    @Delete("delete from user where id=#{id}")
    Integer deleteUser(Integer id);
}
