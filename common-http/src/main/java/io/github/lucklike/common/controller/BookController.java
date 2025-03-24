package io.github.lucklike.common.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.id.NanoId;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.lucklike.common.entity.Book;
import io.github.lucklike.common.entity.PageBookRequest;
import io.github.lucklike.common.server.BookService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService bookService;

    // 获取所有书籍
    @GetMapping("/list")
    public List<Book> list() {
        return bookService.list();
    }

    @PostMapping("/page")
    public IPage<Book> page(@RequestBody PageBookRequest pageBookRequest) {
        return bookService.findByPage(
                new Page<>(pageBookRequest.getPageNum(), pageBookRequest.getPageSize()),
                BeanUtil.copyProperties(pageBookRequest, Book.class)
        );
    }

    // 新增书籍
    @PostMapping("/save")
    public boolean save(@RequestBody Book book) {
        book.setId(NanoId.randomNanoId(21));
        return bookService.save(book);
    }

    // 根据 ID 删除书籍
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable String id) {
        return bookService.removeById(id);
    }

    // 根据 ID 更新书籍信息
    @PutMapping("/update")
    public boolean update(@RequestBody Book book) {
        return bookService.updateById(book);
    }

    // 根据 ID 查询书籍
    @GetMapping("/get/{id}")
    public Book getById(@PathVariable String id) {
        return bookService.getById(id);
    }
}