package io.github.lucklike.common.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luckyframework.httpclient.proxy.annotations.Delete;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PrintLogProhibition;
import com.luckyframework.httpclient.proxy.annotations.Put;
import io.github.lucklike.common.api.annotation.FunctionCalling;
import io.github.lucklike.common.api.annotation.Tool;
import io.github.lucklike.common.entity.Book;
import io.github.lucklike.common.entity.PageBookRequest;
import io.github.lucklike.httpclient.discovery.HttpClient;

import java.util.List;

import static io.github.lucklike.common.Config.SERVICE_NAME_CONFIG;
import static io.github.lucklike.common.Config.URL_CONFIG;

@FunctionCalling
@PrintLogProhibition
@HttpClient(url = URL_CONFIG, service = SERVICE_NAME_CONFIG, path = "book")
public interface BookApi {


    // 获取所有书籍
    @Get("/list")
    @Tool(name = "book_list", desc = "获取所有的书本信息")
    List<Book> list();

    @Post("/page")
    @Tool(name = "book_page_list", desc = "分页查询书本信息，支持使用书名和作者进行模糊查询")
    Page<Book> page(@JsonBody PageBookRequest pageQuery);

    // 新增书籍
    @Post("/save")
    @Tool(name = "book_save", desc = "添加一个书本信息", required = {"title", "author", "price", "publishDate"})
    String save(@JsonBody Book book);

    // 根据 ID 删除书籍
    @Delete("/delete/#{book.id}")
    @Tool(name = "book_delete", desc = "根据书本DI删除一个书本信息", required = "id")
    String delete(Book book);

    // 根据 ID 更新书籍信息
    @Put("/update")
    @Tool(name = "book_update", desc = "根据书本ID更新一个书本信息", required = "id")
    String update(@JsonBody Book book);

    // 根据 ID 查询书籍
    @Get("/get/#{book.id}")
    @Tool(name = "book_get", desc = "根据书本ID获取一本书的详细信息", required = "id")
    Book getById(Book book);
}
