package cn.globalsession.test;

import cn.globalsession.dao.UserMapper;
import cn.globalsession.pojo.User;
import cn.globalsession.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * 二级缓存：
 *  1、二级缓存也叫全局缓存，一级缓存作用域太低了，所以诞生了二级缓存；
 *  2、基于namespace级别的缓存，一个名称空间，对应一个二级缓存；
 *  3、工作机制：
 *      一个会话查询一条数据，这个数据就会被放在当前会话的一级缓存中；
 *      如果当前会话关闭了，这个会话对应的一级缓存就没了；
 *          但是我们想要的是会话关闭了，一级缓存中的数据被保存到二级缓存中；
 *      新的会话查询信息，就可以从二级缓存中获取内容；
 *      不同的mapper（命名空间）查出的数据会放在自己对应的缓存（map）中；
 *
 * 使用步骤：
 *  1、在全局配置中开启全局缓存；
 *      <setting name="cacheEnabled" value="true"/>
 *  2、去每个mapper.xml中配置使用二级缓存；
 *      <cache/>
 *  3、若针对某一条语句来配置二级缓存时候则可以使用useCache="true"
 *      <select id="selectUserById" resultType="cn.globalsession.pojo.User" useCache="true">
 *  4、采用二级缓存的实体类要实现可序列化接口：implements Serializable
 *
 * 什么时候开启：
 *  1、因为所有的增删改都会触发缓存的刷新，从而导致二级缓存失效，
 *      所以二级缓存适合在读多写少的场景中开启；
 *  2、因为二级缓存针对的是同一个namespace，所以建议是在单表操作的mapper中使用，
 *      或者是在相关表的mapper文件中共享同一个缓存；
 */
public class MybatisTest {

    @Test
    public void test01() {
        SqlSession session1 = MybatisUtils.getSession();
        SqlSession session2 = MybatisUtils.getSession();
        UserMapper mapper1 = session1.getMapper(UserMapper.class);
        UserMapper mapper2 = session2.getMapper(UserMapper.class);

        User user1 = mapper1.selectUserById(4);
        System.out.println(user1);
        session1.close();

        User user2 = mapper2.selectUserById(4);
        System.out.println(user2);
        System.out.println(user1 == user2);
        session2.close();

        /**
         * Setting autocommit to false on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@4e423aa2]
         * ==>  Preparing: select * from user where id = ?
         * ==> Parameters: 4(Integer)
         * <==    Columns: id, name, pwd
         * <==        Row: 4, ryu, 123
         * <==      Total: 1
         * User(id=4, name=ryu, pwd=123)
         * Resetting autocommit to true on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@4e423aa2]
         * Closing JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@4e423aa2]
         * Returned connection 1312963234 to pool.
         * As you are using functionality that deserializes object streams, it is recommended to define the JEP-290 serial filter. Please refer to https://docs.oracle.com/pls/topic/lookup?ctx=javase15&id=GUID-8296D8E8-2B93-4B9A-856E-0A65AF9B8C66
         * Cache Hit Ratio [cn.globalsession.dao.UserMapper]: 0.5
         * User(id=4, name=ryu, pwd=123)
         * false
         *
         * 从以上过程可看到，开启二级缓存后，虽然是跨会话执行查询，但是只执行了一次SQL查询；
         * 结论：
         *  只要开启了二级缓存，我们在同一个Mapper中的查询，可以在二级缓存中拿到数据；
         *  查询的数据都会被默认先放在一级缓存中；
         *  只有会话提交（commit）或者关闭（close）以后，一级缓存中的数据才会转到二级缓存中；
         */
    }
}
