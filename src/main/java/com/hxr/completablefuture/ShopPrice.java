package com.hxr.completablefuture;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;

/**
 * 异步拆分-获取商店的价格
 *
 * @author houxiurong
 * @date 2019-09-06
 */
public class ShopPrice {

    private String shopName;

    public ShopPrice(String shopName) {
        this.shopName = shopName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public String getPrice2(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", shopName, price, code);
    }

    private static Random random = new Random();

    /**
     * 异步方式计算价格
     *
     * @param product 商品名称
     * @return 商品价格
     */
    private double calculatePrice(String product) {
        try {
            //任务-延迟1秒-模拟http网络请求延迟1秒
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    /**
     * 异步获取商品价格
     *
     * @param product 商品
     * @return Future<Double> 包含价格的future
     */
    private Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception ex) {
                futurePrice.completeExceptionally(ex);
            }
        }).start();
        return futurePrice;
    }

    /**
     * 异步获取商品价格-lambda优化
     *
     * @param product 商品
     * @return Future<Double> 包含价格的future
     */
    private Future<Double> getPriceAsync1(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }


    /**
     * 任务-延迟1秒
     */
    private void delay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行其他耗时任务
     */
    private static void doSomethingElse() {
        try {
            System.out.println("~~~我是其他要执行的任务-耗时2秒开始~~~");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("~~~我是其他要执行的任务-耗时2秒结束~~~");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main2(String[] args) {
        ShopPrice shop = new ShopPrice("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        //Future<Double> futurePrice = shop.getPriceAsync1("my favorite product");
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Invocation return after " + invocationTime + " msecs");

        //执行其他需要操作的任务
        doSomethingElse();

        //获取商品价格
        try {
            double price = futurePrice.get();
            System.out.printf("异步获取的价格为:%.2f%n", price);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        long endTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("价格返回后的时间:" + endTime + " msecs");
    }

    public List<String> findPrices(String product) {
        List<ShopPrice> shopPrices = asList(
                new ShopPrice("Aa"),
                new ShopPrice("Bb"),
                new ShopPrice("Cc"),
                new ShopPrice("Dd"));
        return shopPrices.stream()
                .map(shop -> shop.getShopName() + "价格是 " + shop.getPrice(product))
                .collect(Collectors.toList());
    }

    public static void main1(String[] args) {
        long start = System.nanoTime();
        System.out.println(new ShopPrice("x").findPrices("myPhone"));
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Invocation return after " + invocationTime + " msecs");
    }

    static List<ShopPrice> shopList = newArrayList(
            new ShopPrice("Shop1"),
            new ShopPrice("Shop2"),
            new ShopPrice("Shop3"),
            new ShopPrice("Shop4"),
            new ShopPrice("Shop5"),
            new ShopPrice("Shop6"),
            new ShopPrice("Shop7"),
            new ShopPrice("Shop8"),
            new ShopPrice("Shop9"),
            new ShopPrice("Shop10"),
            new ShopPrice("Shop11"),
            new ShopPrice("Shop12"),
            new ShopPrice("Shop13"),
            new ShopPrice("Shop14"),
            new ShopPrice("Shop15"),
            new ShopPrice("Shop16"),
            new ShopPrice("Shop17"),
            new ShopPrice("Shop18"),
            new ShopPrice("Shop19"),
            new ShopPrice("Shop20"));

    public static List<String> findPriceByProduct(String product) {
        return shopList.stream()
                .parallel()
                .map(shop ->
                        String.format("%s price is %.2f", shop.getShopName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    /**
     * 使用CompletableFuture
     *
     * @param product 商品名称
     * @return 被CompletableFuture处理过的list
     */
    public static List<String> findPriceByProductAsync(String product) {
        //使用CompletableFuture的工厂方法supplyAsync,异步获取值并放入future中
        List<CompletableFuture<String>> priceFuture = shopList.stream()
                .map(shop -> CompletableFuture.supplyAsync(() ->
                        String.format("%s price is %.2f",
                                shop.getShopName(), shop.getPrice(product))))
                .collect(Collectors.toList());
        return priceFuture.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    /**
     * 使用CompletableFuture
     *
     * @param product 商品名称
     * @return 被CompletableFuture处理过的list
     */
    public static List<String> findPriceByProductAsyncThread(String product) {
        //使用CompletableFuture的工厂方法supplyAsync,异步获取值并放入future中
        List<CompletableFuture<String>> priceFuture = shopList.stream()
                .map(shop ->
                        CompletableFuture.supplyAsync(() ->
                                        String.format("%s price is %.2f", shop.getShopName(), shop.getPrice(product)),
                                EXECUTOR))
                .collect(Collectors.toList());
        return priceFuture.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    /**
     * 创建一个线程池,最大100个
     * 《Java并发编程实战》Brian Goetz建议:
     * 线程池的大小按照下面公式来计算:
     * N(threads)=N(cpu)*U(cpu)*(1 + W/C)
     * N(cpu)处理器的核数目,可以通过:CUP的核数= (Runtime.getRuntime().availableProcessors())获取
     * U(cpu)是期望的CUP利用率,数值介于0~1之间
     * W/C是等待时间和程序单次执行时间
     */
    static final int corePoolSize = Math.min(shopList.size(), 100);
    private static final Executor EXECUTOR = new ThreadPoolExecutor(
            corePoolSize, corePoolSize,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(),
            r -> {
                Thread t = new Thread(r);
                t.setName("priceByProductAsyncThread");
                //设置为守护线程不阻止程序关停
                t.setDaemon(true);
                return t;
            });

    public static void main(String[] args) {
        long start = System.nanoTime();
        //System.out.println(findPriceByProduct("myIphone11Max"));
        //System.out.println(findPriceByProductAsync("myIphone11Max"));
        System.out.println(findPriceByProductAsyncThread("myIphone11Max"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Duration in " + duration + " msecs");
        //[A1 price is 168.99, A2 price is 190.99, A3 price is 183.31, A4 price is 210.65]
        //Duration in 4164 msecs -- 4个商家接口调用大约4秒
        //[A1 price is 184.21, A2 price is 148.43, A3 price is 134.85, A4 price is 221.05, A5 price is 190.88]
        //Duration in 5140 msecs -- 5个商家接口调用大约5秒

        //[A1 price is 229.06, A2 price is 198.69, A3 price is 163.47, A4 price is 135.35]
        //Duration in 1180 msecs -- parallel 并行流处理时间
        //[A1 price is 153.41, A2 price is 137.70, A3 price is 169.99, A4 price is 212.28, A5 price is 133.78]
        //Duration in 2138 msecs -- parallel 并行流处理5个商家时间
        //[A1 price is 180.93, A2 price is 187.74, A3 price is 206.71, A4 price is 123.72, A5 price is 185.13, A6 price is 201.66, A7 price is 122.12, A8 price is 122.28, A9 price is 133.44]
        //Duration in 3139 msecs parallel 并行流处理9个商家耗时 3秒左右
        //[A1 price is 165.29, A2 price is 194.87, A3 price is 226.72, A4 price is 146.20, A5 price is 164.49, A6 price is 146.97, A7 price is 198.16, A8 price is 213.22, A9 price is 128.42, A10 price is 166.33, A11 price is 186.02, A12 price is 137.38, A13 price is 130.81, A14 price is 212.11, A15 price is 184.66, A16 price is 188.99, A17 price is 222.41, A18 price is 133.20, A19 price is 198.52, A20 price is 192.64]
        //Duration in 5154 msecs --parallel并行流处理20个商家耗时 5秒左右

        //[A1 price is 182.99, A2 price is 163.31, A3 price is 210.66, A4 price is 150.64]
        //Duration in 2146 msecs -- CompletableFuture 4个商家耗时 2秒左右
        //[A1 price is 203.81, A2 price is 146.71, A3 price is 195.79, A4 price is 148.64, A5 price is 172.47]
        //Duration in 2155 msecs -- CompletableFuture 5个商家耗时 2秒左右
        //[A1 price is 175.50, A2 price is 227.35, A3 price is 136.42, A4 price is 149.88, A5 price is 227.83, A6 price is 158.69, A7 price is 145.41, A8 price is 199.95, A9 price is 152.76]
        //Duration in 3184 msecs -- CompletableFuture 9个商家耗时 3秒左右
        //[A1 price is 139.27, A2 price is 227.24, A3 price is 185.27, A4 price is 189.21, A5 price is 212.77, A6 price is 223.50, A7 price is 134.73, A8 price is 161.68, A9 price is 181.64, A10 price is 228.58, A11 price is 190.20, A12 price is 133.67, A13 price is 229.10, A14 price is 148.53, A15 price is 212.89, A16 price is 159.47, A17 price is 170.14, A18 price is 202.56, A19 price is 137.83, A20 price is 128.59]
        //Duration in 7165 msecs -- CompletableFuture 20个商家耗时 7秒左右
        //System.out.println("CUP的核数=" + Runtime.getRuntime().availableProcessors());

        //[A1 price is 214.90, A2 price is 192.81, A3 price is 147.93, A4 price is 174.46, A5 price is 208.14, A6 price is 177.70, A7 price is 183.81, A8 price is 161.27, A9 price is 129.07, A10 price is 194.89, A11 price is 174.63, A12 price is 159.22, A13 price is 143.92, A14 price is 141.92, A15 price is 136.10, A16 price is 174.94, A17 price is 137.55, A18 price is 204.08, A19 price is 150.38, A20 price is 147.92]
        //Duration in 1041 msecs -- 使用多线程线程池情况下20个异步请求耗时1秒左右
    }

}
