package cn.alvin.test;

import cn.alvin.dao.UserMapper;
import cn.alvin.pojo.User;
import cn.alvin.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class MybatisTest {

    @Test
    public void test02() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // select * from user where id=#{id}
        User user = mapper.getUserById(3);// User{id=3, username='null', password='null'}
        System.out.println(user);
        // 返回结果集列名和属性名不一致，解决方式一：
        // select id,name as username,pwd as password from user where id=#{id}
        User user1 = mapper.getUserById1(3);// User{id=3, username='李四', password='987654'}
        System.out.println(user1);
        // 返回结果集列名和属性名不一致，解决方式二：使用结果集映射resultMap="UserMap"
        User user2 = mapper.getUserById2(3);// User{id=3, username='李四', password='987654'}
        System.out.println(user2);
        sqlSession.close();

        //mybatis-01-start中的id="addUser2"参数集映射和以上两种方式其实是同一个思想
    }

    @Test
    public void test01() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.getUserList().forEach(System.out::println);
        sqlSession.close();
    }
}
