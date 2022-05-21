package cn.dynamicsql.test;

import cn.dynamicsql.dao.BlogMapper;
import cn.dynamicsql.pojo.Blog;
import cn.dynamicsql.utils.IDUtil;
import cn.dynamicsql.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.*;

public class MybatisTest {

    @Test
    public void test07() {
        SqlSession session = MybatisUtils.getSession();
        BlogMapper mapper = session.getMapper(BlogMapper.class);
        List<String> ids = new ArrayList<>();
        ids.add("05e142a5bbb244e0b30edc8339daa85f");
        ids.add("cd959b65912349c190eb8d692e421fc8");
        // select * from blog WHERE id in( ? , ? )
        mapper.queryBlogForeach2(ids).forEach(System.out::println);
        session.close();
    }

    @Test
    public void test06() {
        SqlSession session = MybatisUtils.getSession();
        BlogMapper mapper = session.getMapper(BlogMapper.class);
        Map<String, Object> map = new HashMap<>();
        List<String> ids = new ArrayList<>();
        ids.add("05e142a5bbb244e0b30edc8339daa85f");
        ids.add("cd959b65912349c190eb8d692e421fc8");
        map.put("ids", ids);
        // select * from blog WHERE ( id = ? or id = ? )
        mapper.queryBlogForeach1(map).forEach(System.out::println);
        session.close();
    }

    @Test
    public void test05() {
        SqlSession session = MybatisUtils.getSession();
        BlogMapper mapper = session.getMapper(BlogMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("author", "jackson");
        map.put("views", "05e142a5bbb244e0b30edc8339daa85f");
        // select * from blog WHERE author like ?
        mapper.queryBlogWithFragment(map).forEach(System.out::println);
        session.close();
    }

    @Test
    public void test04() {
        SqlSession session = MybatisUtils.getSession();
        BlogMapper mapper = session.getMapper(BlogMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("author", "jackson");
        map.put("views", "05e142a5bbb244e0b30edc8339daa85f");
        // select * from blog WHERE author like ?
        mapper.queryBlogChoose(map).forEach(System.out::println);
        session.close();
    }

    @Test
    public void test03() {
        SqlSession session = MybatisUtils.getSession();
        BlogMapper mapper = session.getMapper(BlogMapper.class);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("title", "996");
        map1.put("author", "jackson");
        map1.put("id", "05e142a5bbb244e0b30edc8339daa85f");
        // update blog SET title = ?, author = ? where id = ?
        mapper.updateBlog(map1);
        System.out.println("===========================================");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("author", "henry");
        map2.put("id", "cd959b65912349c190eb8d692e421fc8");
        // update blog SET author = ? where id = ?
        mapper.updateBlog(map2);
        session.commit();
        session.close();
    }

    @Test
    public void test02() {
        SqlSession session = MybatisUtils.getSession();
        BlogMapper mapper = session.getMapper(BlogMapper.class);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("views", 90);
        map1.put("author", "%Alvin%");
        // select * from blog where 1 = 1 and views > ? and author like ?
        // Parameters: 90(Integer), %Alvin%(String)
        mapper.queryBlogIf(map1).forEach(System.out::println);
        System.out.println("===========================================");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("author", "%Ryu%");
        // select * from blog where 1 = 1 and author like ?
        // Parameters: %Ryu%(String)
        mapper.queryBlogIf(map2).forEach(System.out::println);
        session.close();
    }

    @Test
    public void test01() {
        SqlSession session = MybatisUtils.getSession();
        BlogMapper mapper = session.getMapper(BlogMapper.class);
        Blog blog1 = new Blog(IDUtil.uuid(), "Hello,World", "Ryu", new Date(), 100);
        mapper.addBlog(blog1);
        Blog blog2 = new Blog(IDUtil.uuid(), "Hello,China", "Alvin", new Date(), 200);
        mapper.addBlog(blog2);
        session.commit();
        session.close();
    }
}
