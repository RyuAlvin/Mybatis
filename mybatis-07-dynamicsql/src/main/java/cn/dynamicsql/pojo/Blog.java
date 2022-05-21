package cn.dynamicsql.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private String id;
    private String title;
    private String author;
    /**
     * 数据库中是create_time，
     *  在核心配置文件中配置了<setting name="mapUnderscoreToCamelCase" value="true"/>
     *  因此开启驼峰命名自动映射，即从经典数据库列名 A_COLUMN 映射到经典 Java 属性名 aColumn。
     */
    private Date createTime;
    private int views;
}
