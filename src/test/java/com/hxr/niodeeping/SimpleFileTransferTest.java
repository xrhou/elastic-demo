package com.hxr.niodeeping;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class SimpleFileTransferTest {

    /**
     * 本地流方式处理文件
     *
     * @param source 源文件
     * @param des    新文件
     * @return
     * @throws IOException
     */
    private long transferFile(File source, File des) throws IOException {
        long startTime = System.currentTimeMillis();

        if (!des.exists()) {
            des.createNewFile();
        }

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(des));

        //将数据源读到的内容写入目的地--使用数组
        byte[] bytes = new byte[1024 * 1024];
        int len;
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }

        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    /**
     * No-blocking IO 块方式处理文件数据
     *
     * @param source 源文件
     * @param des    目的文件
     * @return
     * @throws IOException
     */
    private long transferFileWithNIO(File source, File des) throws IOException {
        long startTime = System.currentTimeMillis();

        if (!des.exists())
            des.createNewFile();

        RandomAccessFile read = new RandomAccessFile(source, "rw");
        RandomAccessFile write = new RandomAccessFile(des, "rw");

        FileChannel readChannel = read.getChannel();
        FileChannel writeChannel = write.getChannel();


        //1M缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);

        while (readChannel.read(byteBuffer) > 0) {
            byteBuffer.flip();
            writeChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        writeChannel.close();
        readChannel.close();
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static void main0(String[] args) throws IOException {
        SimpleFileTransferTest simpleFileTransferTest = new SimpleFileTransferTest();
        File source = new File("/Users/houxiurong/Movies/50视频.avi");
        File des = new File("/Users/houxiurong/Desktop/io.avi");
        File nio = new File("/Users/houxiurong/Desktop/nio.avi");

        long time = simpleFileTransferTest.transferFile(source, des);
        System.out.println(time + "：普通字节流时间");

        long timeNio = simpleFileTransferTest.transferFileWithNIO(source, nio);
        System.out.println(timeNio + "：NIO时间");
    }

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);//1Bit
        // 看一下初始时4个核心变量的值
        System.out.println("初始时-->limit--->" + byteBuffer.limit());
        System.out.println("初始时-->position--->" + byteBuffer.position());
        System.out.println("初始时-->capacity--->" + byteBuffer.capacity());
        System.out.println("初始时-->mark--->" + byteBuffer.mark());

        System.out.println("=======================================");
        String s = "java6666";
        byteBuffer.put(s.getBytes());

        // 看一下初始时4个核心变量的值
        System.out.println("put完之后-->limit--->" + byteBuffer.limit());
        System.out.println("put完之后-->position--->" + byteBuffer.position());
        System.out.println("put完之后-->capacity--->" + byteBuffer.capacity());
        System.out.println("put完之后-->mark--->" + byteBuffer.mark());

        byteBuffer.flip();// 每当要从缓存区的时候读取数据时，调用filp()-“切换成读模式”。
        // 看一下初始时4个核心变量的值
        System.out.println("flip完之后-->limit--->" + byteBuffer.limit());
        System.out.println("flip完之后-->position--->" + byteBuffer.position());
        System.out.println("flip完之后-->capacity--->" + byteBuffer.capacity());
        System.out.println("flip完之后-->mark--->" + byteBuffer.mark());

        System.out.println("-----------------------------");
        // 创建一个limit()大小的字节数组(因为就只有limit这么多个数据可读)
        byte[] bytes = new byte[byteBuffer.limit()];

        // 将读取的数据装进我们的字节数组中
        byteBuffer.get(bytes);

        // 输出数据
        System.out.println(new String(bytes, 0, bytes.length));

    }

}
