package cn.anno.test;

import cn.anno.dao.UserMapper;
import cn.anno.pojo.User;
import cn.anno.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class MybatisTest {

    @Test
    public void test06(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.getUserByName("李").forEach(System.out::println);
    }

    @Test
    public void test05() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.deleteUser(12);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test04() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.updateUser(new User(7, "aaa111111111111", null));
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test03() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.addUser(new User(12, "fff", "fff"));
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test02() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        System.out.println(mapper.getUserById(3));
        sqlSession.close();
    }

    @Test
    public void test01() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //本质上利用了JVM的动态代理机制
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.getUserList().forEach(System.out::println);
    }
}
