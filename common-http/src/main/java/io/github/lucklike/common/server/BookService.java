package io.github.lucklike.common.server;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.lucklike.common.entity.Book;

public interface BookService extends IService<Book> {

    IPage<Book> findByPage(Page<Book> page, Book book);
}
