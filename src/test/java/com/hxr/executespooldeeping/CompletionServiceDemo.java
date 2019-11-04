package com.hxr.executespooldeeping;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 比异步获取线程更好的线程结果任务处理
 *
 * @author houxiurong
 * @date 2019-10-24
 */
public class CompletionServiceDemo {

    private final ExecutorService executorService;

    public CompletionServiceDemo(ExecutorService executorService) {
        this.executorService = executorService;
    }

    void solve(Executor e) throws InterruptedException, ExecutionException {
        //生成者容器的个数
        List<String> solvers = new ArrayList<>();
        solvers.add("hello");

        CompletionService<String> ecs = new ExecutorCompletionService<>(e);

        for (String s : solvers) {
            ecs.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return s + "参数耗时执行的方法获取值";
                }
            });
        }

        //消费者同样按照容器个数获取处理结果
        int n = solvers.size();
        for (int i = 0; i < n; ++i) {
            Future<String> stringFuture = ecs.take();
            String result = stringFuture.get();
            if (result != null) {
                use(result);
            }
        }
    }

    private void use(Object r) {
        System.out.println(r);
    }
}
