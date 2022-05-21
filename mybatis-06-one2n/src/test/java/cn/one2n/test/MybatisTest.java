package cn.one2n.test;

import cn.one2n.dao.TeacherMapper;
import cn.one2n.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class MybatisTest {

    @Test
    public void test01(){
        SqlSession session = MybatisUtils.getSession();
        TeacherMapper mapper = session.getMapper(TeacherMapper.class);
        mapper.getTeacherList().forEach(System.out::println);
        System.out.println("=================================");
        mapper.getTeacherList1().forEach(System.out::println);
        session.close();
    }
}

