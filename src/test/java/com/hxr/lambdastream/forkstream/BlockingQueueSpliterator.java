package com.hxr.lambdastream.forkstream;

import java.util.Spliterator;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

/**
 * 遍历BlockingQueue并读取其中元素的 Spliterator
 * 对流进行切分-流的延迟绑定
 *
 * @author houxiurong
 * @date 2019-10-03
 */
public class BlockingQueueSpliterator<T> implements Spliterator<T> {
    private final BlockingQueue<T> queue;

    public BlockingQueueSpliterator(BlockingQueue<T> queue) {
        this.queue = queue;
    }

    @Override
    public boolean tryAdvance(Consumer<? super T> action) {
        T take;
        while (true) {
            try {
                take = queue.take();
                break;
            } catch (InterruptedException e) {
            }
        }
        if (take != ForkingStreamConsumer.END_OF_STREAM) {
            action.accept(take);
            return true;
        }
        return false;
    }

    /**
     * 对流进行切分
     *
     * @return
     */
    @Override
    public Spliterator<T> trySplit() {
        return null;
    }

    /**
     * 流估计大小
     *
     * @return
     */
    @Override
    public long estimateSize() {
        return 0;
    }

    /**
     *
     * @return
     */
    @Override
    public int characteristics() {
        return 0;
    }
}
