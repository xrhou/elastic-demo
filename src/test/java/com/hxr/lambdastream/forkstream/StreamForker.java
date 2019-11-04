package com.hxr.lambdastream.forkstream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 将同一个流进行合并并发处理了
 *
 * @author houxiurong
 * @date 2019-10-02
 */
public class StreamForker<T> {
    private final Stream<T> stream;
    private final Map<Object, Function<Stream<T>, ?>> forks = new HashMap<>();


    public StreamForker(Stream<T> stream) {
        this.stream = stream;
    }

    public StreamForker<T> fork(Object key, Function<Stream<T>, ?> function) {
        forks.put(key, function);
        return this;
    }

    public Results getResults() {
        ForkingStreamConsumer<T> consumer = builder();
        try {
            stream.sequential().forEach(consumer);
        } finally {
            consumer.finish();
        }
        return consumer;
    }

    /**
     * builder方法创建ForkingStreamConsumer
     *
     * @return
     */
    private ForkingStreamConsumer<T> builder() {
        //创建由队列组成的列表,每一个队列对应一个lambda操作
        List<BlockingQueue<T>> queues = new ArrayList<>();

        //建立用户标识操作的键与包含操作结果的Future之间的映射关系
        Map<Object, Future<?>> actions = forks.entrySet().stream()
                .reduce(new HashMap<Object, Future<?>>(),
                        (map, e) -> {
                            map.put(e.getKey(), getOperationsResult(queues, e.getValue()));
                            return map;
                        },
                        (m1, m2) -> {
                            m1.putAll(m2);
                            return m1;
                        });
        return new ForkingStreamConsumer<>(queues, actions);
    }

    /**
     * 使用getOperationsResult创建一个Future
     *
     * @param queues List队列
     * @param func   函数流
     * @return 新的Future
     */
    private Future<?> getOperationsResult(List<BlockingQueue<T>> queues,
                                          Function<Stream<T>, ?> func) {
        //创建一个队列,并将其添加到队列的列表中
        BlockingQueue<T> queue = new LinkedBlockingQueue<>();
        queues.add(queue);

        //创建一个 Spliterator,遍历队列中的元素
        Spliterator<T> spliterator = new BlockingQueueSpliterator<>(queue);

        //创建一个流,将 spliterator 做为数据源
        Stream<T> source = StreamSupport.stream(spliterator, false);

        //创建一个Future对象,已异步方式计算在流上执行特定函数的结果
        return CompletableFuture.supplyAsync(() -> func.apply(source));
    }

}
