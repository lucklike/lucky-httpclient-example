package io.github.lucklike.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.lucklike.common.entity.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper extends BaseMapper<Book> {

}