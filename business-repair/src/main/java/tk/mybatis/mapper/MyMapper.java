package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author 你是电脑
 * @create 2019/10/28 - 23:48
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
