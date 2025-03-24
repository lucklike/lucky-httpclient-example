package io.github.lucklike.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.lucklike.common.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
    @Select("<script>" +
            "SELECT * FROM BOOK " +
            "WHERE 1=1 " +
            "<if test='book.title != null and book.title != \"\"'> " +
            "  AND TITLE LIKE '%' || #{book.title} || '%' " +
            "</if> " +
            "<if test='book.author != null and book.author != \"\"'> " +
            "  AND AUTHOR LIKE '%' || #{book.author} || '%' " +
            "</if> " +
            "ORDER BY PUBLISHDATE DESC" +
            "</script>")
    IPage<Book> selectByPage(Page<Book> page, @Param("book")  Book book);
}