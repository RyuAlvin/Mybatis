package cn.lognpage.test;

import cn.lognpage.dao.UserMapper;
import cn.lognpage.pojo.User;
import cn.lognpage.utils.MybatisUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisTest {

    //org.apache.log4j.Logger
    static Logger logger = Logger.getLogger(MybatisTest.class);

    @Test
    public void test02(){
        //分页方式二：在JAVA层面实现分页
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        int currentPage = 3;
        int pageSize = 2;
        RowBounds rowBounds = new RowBounds((currentPage-1)*pageSize,pageSize);
        List<User> list = sqlSession.selectList("cn.lognpage.dao.UserMapper.getUserListByRowBounds", null, rowBounds);
        list.forEach(System.out::println);
        sqlSession.close();

        //分页方式三：PageHelper
        //https://pagehelper.github.io/
    }

    @Test
    public void test01() {
        logger.info("info:xxxxxx");
        logger.debug("info:xxxxxx");
        logger.error("info:xxxxxx");
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int currentPage = 3;
        int pageSize = 2;
        Map<String, Integer> map = new HashMap<>();
        map.put("startIndex", (currentPage - 1) * pageSize);
        map.put("pageSize", pageSize);
        mapper.getUserList(map).forEach(System.out::println);
        System.out.println(sqlSession);
        sqlSession.close();
    }
}
