package io.github.lucklike.common.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.proxy.annotations.Delete;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.JsonBody;
import com.luckyframework.httpclient.proxy.annotations.Post;
import com.luckyframework.httpclient.proxy.annotations.PrintLogProhibition;
import com.luckyframework.httpclient.proxy.annotations.Put;
import io.github.lucklike.common.api.annotation.Required;
import io.github.lucklike.common.entity.Book;
import io.github.lucklike.common.entity.PageBookRequest;
import io.github.lucklike.httpclient.discovery.HttpClient;

import java.util.List;

import static io.github.lucklike.common.Config.SERVICE_NAME_CONFIG;
import static io.github.lucklike.common.Config.URL_CONFIG;

@PrintLogProhibition
@HttpClient(url = URL_CONFIG, service = SERVICE_NAME_CONFIG, path = "book")
public interface BookApi {


    // 获取所有书籍
    @Get("/list")
    @Describe(name = "book_list", desc = "获取所有的书本信息")
    List<Book> list();

    @Post("/page")
    @Describe(name = "book_page_list", desc = "分页获取所有的书本信息，支持使用书名和作者进行模糊查询")
    Page<Book> page(@JsonBody PageBookRequest pageQuery);

    // 新增书籍
    @Post("/save")
    @Required({"title", "author", "price", "publishDate"})
    @Describe(name = "book_save", desc = "添加一个书本信息")
    String save(@JsonBody Book book);

    // 根据 ID 删除书籍
    @Required("id")
    @Delete("/delete/#{book.id}")
    @Describe(name = "book_delete", desc = "根据书本DI删除一个书本信息")
    String delete(Book book);

    // 根据 ID 更新书籍信息
    @Required("id")
    @Put("/update")
    @Describe(name = "book_update", desc = "根据书本ID更新一个书本信息")
    String update(@JsonBody Book book);

    // 根据 ID 查询书籍
    @Required("id")
    @Get("/get/#{book.id}")
    @Describe(name = "book_get", desc = "根据书本ID获取一本书的详细信息")
    Book getById(Book book);
}
