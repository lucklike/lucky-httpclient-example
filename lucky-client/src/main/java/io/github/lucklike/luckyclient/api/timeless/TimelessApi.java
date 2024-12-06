package io.github.lucklike.luckyclient.api.timeless;

import com.luckyframework.cache.Cache;
import com.luckyframework.cache.impl.LRUCache;
import com.luckyframework.common.Console;
import com.luckyframework.common.StringUtils;
import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.proxy.CommonFunctions;
import com.luckyframework.httpclient.proxy.annotations.Condition;
import com.luckyframework.httpclient.proxy.annotations.Get;
import com.luckyframework.httpclient.proxy.annotations.RespConvert;
import com.luckyframework.httpclient.proxy.spel.hook.Lifecycle;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Var;
import com.luckyframework.io.FileUtils;
import io.github.lucklike.httpclient.annotation.HttpClient;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

/**
 * Timeless 歌词查询API
 */
@Condition(assertion = "#{$status$ != 200}", exception = "[Timeless Api]<{}>接口调用异常，错误的状态码：'#{$status$}' 接口地址：[#{$reqMethod$}] #{$url$}")
@Condition(assertion = "#{$body$.errno != 0}", exception = "[Timeless Api]<#{$api.name}>接口调用异常，错误的响应码：'#{$body$.errno}' : #{$body$.errmsg} 接口地址：[#{$reqMethod$}] #{$url$}")
@HttpClient("https://api.timelessq.com")
public interface TimelessApi {

    @Var(lifecycle = Lifecycle.METHOD_META)
    String $api = "#{#describe($mec$)}";

    // 歌词文件保存路径
    File SAVE_DIR = new File(System.getProperty("user.dir"), "lyrics");

    // 歌曲与歌曲ID的缓存
    Cache<String, String> SONGID_CACHE = new LRUCache<>(20);

    // 歌曲与歌词的缓存
    Cache<String, String> LYRICS_CACHE = new LRUCache<>(20);

    default void lyrics() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Console.printlnGreen("请输入歌名: ");
            String context = scanner.nextLine();
            Console.printlnMulberry("\n《{}》\n------------------------", context);
            String lyrics = qryLyricsFindFile(context);
            Console.printMulberry(lyrics + "\n");
        }
    }

    //----------------------------------------------------------------------------
    //                              文件存储
    //----------------------------------------------------------------------------

    default String qryLyricsFindFile(String song) throws IOException {
        String lyrics = StringUtils.format("{}.txt", song);
        File file = new File(SAVE_DIR, lyrics);
        if (file.exists() && file.isFile()) {
            return CommonFunctions.read(file);
        }

        String lyricsContext = qryLyricsFindCache(song);
        FileUtils.createSaveFolder(SAVE_DIR);
        FileCopyUtils.copy(lyricsContext.getBytes(StandardCharsets.UTF_8), file);
        return lyricsContext;
    }

    //----------------------------------------------------------------------------
    //                             带缓存功能的API
    //----------------------------------------------------------------------------

    default String qryLyricsFindCache(String song) {
        return LYRICS_CACHE.computeIfAbsent(song, _k -> qryLyrics(song));
    }

    default String querySongIdFindCache(String song) {
        return SONGID_CACHE.computeIfAbsent(song, _k -> querySongId(song));
    }

    //----------------------------------------------------------------------------
    //                             原生API
    //----------------------------------------------------------------------------

    @Describe("查找歌词")
    @RespConvert("#{$body$.data.lyric}")
    @Get("/music/tencent/lyric?songmid=#{$this$.querySongIdFindCache(song)}")
    String qryLyrics(String song);

    @Condition(assertion = "#{$body$.data.total <= 0}", exception = "未找到‘#{song}’相关的歌曲信息！")
    @RespConvert("#{$body$.data.list.get(0).songmid}")
    @Describe("查找歌曲ID")
    @Get("/music/tencent/search?keyword=#{song}")
    String querySongId(String song);

    @RespConvert("#{$body$.data}")
    @Describe("省市区街道列表查询")
    @Get("/district?level=4")
    List<District> district();

}
