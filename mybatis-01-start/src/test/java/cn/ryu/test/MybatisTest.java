package cn.ryu.test;

import cn.ryu.dao.UserMapper;
import cn.ryu.pojo.User;
import cn.ryu.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisTest {

    @Test
    public void test06() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // select * from user where name like '%' #{name} '%'
        // 注意：#{name}和左右拼接时要加空格
        List<User> list = mapper.getUserLike1("李");
        list.forEach(System.out::println);
        // 增删改一定要提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test05() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // select * from user where name like #{name}
        List<User> list = mapper.getUserLike("%李%");
        list.forEach(System.out::println);
        // 增删改一定要提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test04() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("userid", 5);
        map.put("username", "alvin");
        map.put("userpwd", "456");
        mapper.addUser2(map);
        // 增删改一定要提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test03() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.addUser(new User(4, "ryu", "123"));
        // 增删改一定要提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test02() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.getUserList().forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void test01() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        System.out.println(sqlSession);
        sqlSession.close();
    }
}
