package io.github.lucklike.luckyclient.api.ollama.listener;


import com.luckyframework.common.ProgressBar;
import com.luckyframework.common.UnitUtils;
import com.luckyframework.httpclient.proxy.sse.OnMessage;
import com.luckyframework.httpclient.proxy.sse.ndjson.AnnotationNdJsonEventListener;
import io.github.lucklike.luckyclient.api.ollama.resp.ModelPullResponse;

public class OllamaModelPullEventListener extends AnnotationNdJsonEventListener<ModelPullResponse> {

    private final ProgressBar bar = ProgressBar.styleOne(35);

    private long startTime;
    private long lastTime;
    private long lastCompleted;

    @OnMessage
    public void output(ModelPullResponse response) {

        Long total = response.getTotal();
        Long completed = response.getCompleted();
        if (total == null || completed == null) {
            lastTime = System.currentTimeMillis();
            startTime = lastTime;
            System.out.println(response.getStatus());
        } else {
            long now = System.currentTimeMillis();
            // 花费时间
            long cost = now - lastTime;
            // 这段时间内下载的
            long ok = completed - lastCompleted;

            if (ok == 0) {
                return;
            }

            // 下载速度
            Double speed = (ok / (cost / 1000D));

            // 总用时
            long totalCost = (now - startTime);
            Double remainTime = ((total - completed) / speed) * 1000;
            bar.refresh(
                    response.getStatus() + " ",
                    ((double) completed / total),
                    UnitUtils.byteTo(total),
                    UnitUtils.byteTo(completed),
                    UnitUtils.byteTo(speed.longValue()) + "/s",
                    UnitUtils.millisToTime(totalCost),
                    UnitUtils.millisToTime(remainTime.longValue())
            );

            lastCompleted = completed;
            lastTime = now;
        }

    }
}
