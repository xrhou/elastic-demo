package com.hxr.jvmdeeping;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * 1.7是PermGen space
 *
 * @author houxiurong
 * @date 2019-09-10
 */
public class PermGenOomMock {

    public static void main(String[] args) {
        URL url;
        List<ClassLoader> classLoaderList = new ArrayList<>();
        try {
            url = new File("/tmp").toURI().toURL();
            URL[] urls = {url};
            while (true) {
                ClassLoader loader = new URLClassLoader(urls);
                classLoaderList.add(loader);
                loader.loadClass("com.hxr.jvmdeeping.Demo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}