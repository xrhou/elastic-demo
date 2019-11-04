package com.hxr.linknodedeeping;

import com.alibaba.fastjson.JSON;

/**
 * 合并两个顺序链表
 *
 * @author houxiurong
 * @date 2019-10-02
 */
public class ListNodeMerger {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        listNode1.next = new ListNode(2);
        listNode1.next = new ListNode(4);

        System.out.println(JSON.toJSONString(listNode1));
        ListNode listNode2 = new ListNode(1);
        listNode2.next = new ListNode(3);
        listNode2.next = new ListNode(4);

        ListNode listNode = mergerLinkList(listNode1, listNode2);
        System.out.println(JSON.toJSONString(listNode));

    }

    public static ListNode mergerLinkList(ListNode linkedList1, ListNode linkedList2) {
        if (linkedList1 == null) {
            return linkedList2;
        }
        if (linkedList2 == null) {
            return linkedList1;
        }
        if (linkedList1.val < linkedList2.val) {
            linkedList1.next = mergerLinkList(linkedList1.next, linkedList2);
            return linkedList1;
        } else {
            linkedList2.next = mergerLinkList(linkedList1, linkedList2.next);
            return linkedList2;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }
}
