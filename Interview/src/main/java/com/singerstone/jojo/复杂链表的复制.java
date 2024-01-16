package com.singerstone.jojo;

public class 复杂链表的复制 {

    static class Node {

        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(3);
        Node node2 = new Node(3);
        Node node3 = new Node(3);

        node1.next = node2;
        node2.next = node3;
        node2.random = node1;

        Node node = new 复杂链表的复制().copyRandomList(node1);
        while (node != null) {
            System.out.print(node.val);
            System.out.print("——");
            System.out.print(node.random == null ? null : node.random.val);
            System.out.println();
            node = node.next;
        }

    }

    public Node copyRandomList(Node head) {
        Node cur = head;
        // 复制一份
        while (cur != null) {
            Node node = new Node(cur.val);
            node.next = cur.next;
            cur.next = node;
            cur = node.next;
        }
        cur = head;
        // 赋值radom
        while (cur != null) {
            Node node = cur.next;
            if (cur.random != null) {
                node.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        // 拆分 （赋值radom和拆分不能放在一个循环完成，在一起的话拆分会影响到复制，因为radom可能指向前面的节点）
        cur = head;
        Node result = null;
        while (cur != null) {
            Node node = cur.next;

            if (result == null) {
                result = node;
            }
            cur.next = cur.next.next;
            if (node.next != null) {
                node.next = node.next.next;
            }
            cur = cur.next;
        }

        return result;
    }

}
