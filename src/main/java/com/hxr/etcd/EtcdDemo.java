package com.hxr.etcd;

import java.util.concurrent.CompletableFuture;

/**
 * 使用场景:共享配置和服务发现主要使用。
 * 底层是处理日志复制来保持一致性
 * raft一致性算法协议:
 * leader(领导者) follower(跟随着) candidate(候选者)
 *
 * key-value 分布式一致性文件解决方案,k8s中使用的。
 *
 * lease grant:续租-lease,延续续租时间:keepalive-time
 * lease revoke:取消续租
 * watch监控
 *
 * @author houxiurong
 * @date 2019-09-22
 */
public class EtcdDemo {

    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> "hello")
                .thenApply(s -> s + ",world")
                .thenApply(String::toUpperCase)
                .thenCombine(CompletableFuture.completedFuture(",java"), (s1, s2) -> s1 + s2)
                .thenAccept(System.out::println);
    }
}
