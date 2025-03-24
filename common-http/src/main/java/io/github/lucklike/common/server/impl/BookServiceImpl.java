package io.github.lucklike.common.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.lucklike.common.entity.Book;
import io.github.lucklike.common.mapper.BookMapper;
import io.github.lucklike.common.server.BookService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {


    @Override
    public IPage<Book> findByPage(Page<Book> page, Book book) {

        return page(page,
                new LambdaQueryWrapper<Book>()
                .like(StringUtils.hasText(book.getTitle()), Book::getTitle, book.getTitle())
                        .or(StringUtils.hasText(book.getAuthor()), lm -> lm.like(Book::getAuthor, book.getAuthor()))
        );
    }
}