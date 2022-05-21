package cn.n2one.test;

import cn.n2one.dao.StudentMapper;
import cn.n2one.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class MybatisTest {

    @Test
    public void test01() {
        SqlSession session = MybatisUtils.getSession();
        StudentMapper sMapper = session.getMapper(StudentMapper.class);
        sMapper.getStudentList().forEach(System.out::println);
        System.out.println("================================================");
        sMapper.getStudentList1().forEach(System.out::println);
        session.close();
    }
}
