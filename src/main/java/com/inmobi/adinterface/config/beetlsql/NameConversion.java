package com.inmobi.adinterface.config.beetlsql;

import org.beetl.sql.core.DefaultNameConversion;
import org.beetl.sql.core.annotatoin.Table;

/**
 * @创建人 Mr.Yan
 * @创建时间 2018/8/28
 * @描述:  命名转化，表和列名映射  重写 下面是API 说明 ： --
 *  DefaultNameConversion 数据库名和java属性名保持一致，如数据库表User，对应Java类也是User，数据库列是sysytemId,则java属性也是systemId，反之亦然
 *  UnderlinedNameConversion 将数据库下划线去掉，首字母大写，如数据库是SYS_USER（oralce数据库的表和属性总是大写的), 则会改成SysUser
 *  JPA2NameConversion 支持JPA方式的映射，适合不能用确定的映射关系(2.7.4以前采用JPANameConversion过于简单，已经不用了)
 */
public class NameConversion extends DefaultNameConversion {
    @Override
    public String getTableName(Class<?> c) {
        Table table = (Table)c.getAnnotation(Table.class);
        if(table!=null){
            return table.name();
        }
        return c.getSimpleName();
    }

    @Override
    public String getColName(Class<?> c, String attrName) {
        return attrName;
    }
    @Override
    public String getPropertyName(Class<?> c, String colName) {
        return colName;
    }

}
