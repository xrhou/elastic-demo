package com.hxr.designmodel.proxymodel.staticproxymodel;

/**
 * 代理-电影院
 *
 * @author houxiurong
 * @date 2019-09-15
 */
public class Cinema implements Movie {

    private RealMovie realMovie;

    public Cinema(RealMovie realMovie) {
        super();
        this.realMovie = realMovie;
    }

    @Override
    public void play() {
        playAd(true);
        realMovie.play();
        playAd(false);

    }

    /**
     * 播放广告
     *
     * @param isStart 是否播放广告
     */
    public static void playAd(boolean isStart) {
        if (isStart) {
            System.out.println("电影马上【开始】了，爆米花、可乐、口香糖9.8折，快来买啊！");
        } else {
            System.out.println("电影马上【结束】了，爆米花、可乐、口香糖9.8折，买回家吃吧！");
        }
    }

    /**
     * 静态代理-代理测试
     *
     * @param args
     */
    public static void main(String[] args) {
        /**
         * 代理模式可以在不修改被代理对象的基础上，通过扩展代理类，
         * 进行一些功能的附加与增强。值得注意的是，
         * 代理类和被代理类应该共同实现一个接口，或者是共同继承某个类。
         * 上面介绍的是静态代理的内容，为什么叫做静态呢？
         * 因为它的类型是事先预定好的，比如代码中的 Cinema 这个类.
         */
        RealMovie realMovie = new RealMovie();
        Movie movie = new Cinema(realMovie);
        movie.play();

    }
}
