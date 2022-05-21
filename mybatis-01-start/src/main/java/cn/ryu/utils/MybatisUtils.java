package cn.ryu.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {

    /**
     * SqlSessionFactory可以被认为是一个数据库连接池，它的作用是创建SqlSession接口对象。
     * 因为MyBatis的本质就是Java对数据库的操作，所以SqlSessionFactory的生命周期存在于整个MyBatis的应用之中，
     * 所以一旦创建了SqlSessionFactory，就要长期保存它，直至不再使用MyBatis应用，
     * 所以可以认为SqlSessionFactory的生命周期就等同于MyBatis的应用周期。
     *
     * 由于SqlSessionFactory是一个对数据库的连接池，所以它占据着数据库的连接资源。
     * 如果创建多个SqlSessionFactory，那么就存在多个数据库连接池，这样不利于对数据库资源的控制，
     * 也会导致数据库连接资源被消耗光，出现系统宕机等情况，所以尽量避免发生这样的情况。
     *
     * 因此在一般的应用中我们往往希望SqlSessionFactory作为一个单例，让它在应用中被共享。
     * 所以说SqlSessionFactory的最佳作用域是应用作用域。
     */
    static SqlSessionFactory sqlSessionFactory = null;

    static {
        try {
            //使用Mybatis第一步 ：获取sqlSessionFactory对象
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            /**
             * SqlSessionFacotryBuilder的作用在于创建SqlSessionFactory，
             * SqlSessionFactoryBuilder就失去了作用，所以它只能存在于创建SqlSessionFactory的方法中，而不要让其长期存在。
             * 因此SqlSessionFactoryBuilder实例的最佳作用域是方法作用域（也就是局部方法变量）。
             */
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 如果说SqlSessionFactory相当于数据库连接池，那么SqlSession就相当于一个数据库连接（Connection 对象），
     * 你可以在一个事务里面执行多条SQL，然后通过它的commit、rollback等方法，提交或者回滚事务。
     * 所以它应该存活在一个业务请求中，处理完整个请求后，应该关闭这条连接，让它归还给 SqlSessionFactory，
     * 否则数据库资源就很快被耗费精光，系统就会瘫痪，
     * 所以用 try...catch...finally... 语句来保证其正确关闭。
     * @return
     */
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
