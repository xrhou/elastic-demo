package com.hxr.completablefuture;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.google.common.collect.Lists.newArrayList;

/**
 * 折扣服务
 *
 * @author houxiurong
 * @date 2019-09-26
 */
public class Discount {

    enum Code {
        /**
         * 折扣枚举
         */
        NONE(0),
        SILVER(5),
        GOLD(10),
        PLATINUM(15),
        DIAMOND(20),
        ;

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }

        public int getPercentage() {
            return percentage;
        }
    }


    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is "
                + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    public static double apply(double price, Code code) {
        randomDelay();
        return format(price * (100 - code.percentage) / 100);
    }

    private static double format(double value) {
        return 0.00;
    }


    private static final Random random = new Random();

    /**
     * 随机延迟0.5~2.5秒
     */
    private static void randomDelay() {
        try {
            int delay = 500 + random.nextInt(2000);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

   /* public List<String> findPrices(String product) {
        return shops.stream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }*/


    static List<ShopPrice> shopList = newArrayList(
            new ShopPrice("Shop1"),
            new ShopPrice("Shop2"),
            new ShopPrice("Shop3"),
            new ShopPrice("Shop4"),
            new ShopPrice("Shop5"));

    static final int corePoolSize = Math.min(shopList.size(), 100);
    private static final Executor executor = new ThreadPoolExecutor(
            corePoolSize, corePoolSize,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(),
            r -> {
                Thread t = new Thread(r);
                //设置为守护线程不阻止程序关停
                t.setDaemon(true);
                return t;
            });


    /*public List<String> findPrices(String product) {
        List<CompletableFuture<String>> priceFutures = shopList.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future->((CompletableFuture) future).thenCompose())

    }*/


}
