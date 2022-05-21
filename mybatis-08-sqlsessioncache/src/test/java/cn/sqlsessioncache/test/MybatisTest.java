package cn.sqlsessioncache.test;

import cn.sqlsessioncache.dao.UserMapper;
import cn.sqlsessioncache.pojo.User;
import cn.sqlsessioncache.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * 简介：
 *  1、什么是缓存Cache？
 *      存在内存中的临时数据；
 *      将用户经常查询的数据放在缓存（内存）中，用户去查询数据就不用从磁盘上（关系型数据库文件）查询，
 *          从缓存中查询，从而提高查询效率，解决了高并发系统的性能问题；
 *  2、为什么使用缓存？
 *      减少和数据库的交互次数，减少系统开销，提高系统效率；
 *  3、什么样的数据能使用缓存？
 *      经常查询并且不经常改变的数据；
 *
 * Mybatis缓存：
 *  1、Mybatis包含一个非常强大的查询缓存特性，它可以非常方便地定制和配置缓存，可以极大地提升查询效率；
 *  2、Mybatis系统中默认定义了两级缓存：一级缓存和二级缓存；
 *      默认情况下，只有一级缓存开启（SqlSession级别的缓存，也称为本地缓存）；
 *      二级缓存需要手动开启和配置，他是基于namespace级别的缓存；
 *      为了提高扩展性，Mybatis定义了缓存接口Cache。我们可以通过实现Cache接口来自定义二级缓存；
 *
 * 一次缓存：
 *  1、一级缓存也教本地缓存；
 *      与数据库同一次会话期间查询到的数据会放在本地缓存中；
 *      以后如果需要获取相同的数据，直接从缓存中拿，没必要再去查询数据库；
 *      一级缓存是SqlSession级别的缓存，是一直开启的，我们关闭不了它；
 *  2、一级缓存失效的四种情况；
 *      SqlSession不同：即向数据库发起不同连接请求；
 *      SqlSession相同，查询条件不同；
 *      两次查询之间执行了增删改操作（例：test02）；
 *      手动清除一级缓存session.clearCache()；
 */
public class MybatisTest {

    @Test
    public void test02() {
        SqlSession session = MybatisUtils.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user1 = mapper.selectUserById(4);
        mapper.updateUser(new User(4, "test11111", null));
        User user2 = mapper.selectUserById(4);
        System.out.println(user1 == user2);
        /**
         * ==>  Preparing: select * from user where id = ?
         * ==> Parameters: 4(Integer)
         * <==    Columns: id, name, pwd
         * <==        Row: 4, ryu, 123
         * <==      Total: 1
         * ==>  Preparing: update user set name = ? where id = ?
         * ==> Parameters: test11111(String), 4(Integer)
         * <==    Updates: 1
         * ==>  Preparing: select * from user where id = ?
         * ==> Parameters: 4(Integer)
         * <==    Columns: id, name, pwd
         * <==        Row: 4, test11111, 123
         * <==      Total: 1
         * false
         *
         * 可见以上执行过程，SQL语句查询了两次，因为增删改对当前数据产生影响
         */
        session.close();
    }

    @Test
    public void test01() {
        SqlSession session = MybatisUtils.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user1 = mapper.selectUserById(4);
        System.out.println(user1);
        User user2 = mapper.selectUserById(4);
        System.out.println(user2);
        System.out.println(user1 == user2);
        /**
         * ==>  Preparing: select * from user where id = ?
         * ==> Parameters: 4(Integer)
         * <==    Columns: id, name, pwd
         * <==        Row: 4, ryu, 123
         * <==      Total: 1
         * User(id=4, name=ryu, pwd=123)
         * User(id=4, name=ryu, pwd=123)
         * true
         *
         * 可见以上执行过程，SQL语句只查询了一次，针对于第二次的结果user2，没有进行数据库查询
         *  且user1和user2是同一个对象
         */
        session.close();
    }
}
