package com.hxr.lockdeeping;

import java.util.concurrent.locks.LockSupport;

/**
 * @author houxiurong
 * @date 2019-09-13
 */
public class TestPark {

    public void testPark() {
        LockSupport.park(this);
    }

    public static void main(String[] args) {
        TestPark park = new TestPark();
        park.testPark();
    }
}
