# 과제2 - Doubly Linked List

## 구현
```java
package LecturePractice;

class Node {
    public int value;
    public Node next;
    public Node prev;

    public Node(int value, Node next, Node prev) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }
}

class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int cnt;


    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.cnt = 0;
    }

    public int getCnt() {
        return cnt;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public boolean prepend(int value) {
        if (this.head == null) {
            this.head = new Node(value, null, null);
            this.tail = new Node(value, null, this.head);
            return true;
        }

        this.head = new Node(value, this.head, null);

        cnt++;
        return true;
    }

    public boolean append(int value) {
        if (this.head == null) {
            this.head = new Node(value, null, null);
            this.tail = this.head;
            return true;
        }

        Node curr = this.head;
        Node pre = null;

        while (curr != null) {
            pre = curr;
            curr = curr.next;
        }

        pre.next = new Node(value, null, pre);
        this.tail = pre.next;
        cnt++;
        return true;
    }

    public boolean setHead(int index) {
        Node curr = this.head;

        if (curr == null) {
            return false;
        }

        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        this.head = curr;

        return true;
    }

    public int access(int index) {
        Node curr = this.head;

        if (curr == null) {
            return -1;
        }

        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }

        return curr.value;
    }

    public boolean insert(int index, int value) {
        System.out.println(cnt);
        if (index == 0) {
            this.prepend(value);
            return true;
        }
        Node curr = this.head;
        Node pre = null;

        if (cnt % 2 == 0) {
            if ((cnt / 2) >= index) {
                System.out.println("head에서 탐색");
                for (int i = 0; i < index; i++) {
                    pre = curr;
                    curr = curr.next;
                }
                pre.next = new Node(value, curr, pre);

                return true;

            } else {
                curr = this.tail;
                pre = curr.prev;
                System.out.println("tail에서 탐색!");
                for (int i = 0; i < cnt - index; i++) {
                    curr = pre;
                    pre = curr.prev;
                }

                curr.prev = new Node(value, curr, curr.prev);

            }
            return true;
        } else {
            if ((cnt / 2 + 1) >= index) {
                System.out.println("head에서 탐색");
                for (int i = 0; i < index; i++) {
                    pre = curr;
                    curr = curr.next;
                }
                pre.next = new Node(value, curr, pre);

                return true;

            } else {
                curr = this.tail;
                pre = curr.prev;
                System.out.println("tail에서 탐색!");
                for (int i = 0; i < cnt - index; i++) {
                    curr = pre;
                    pre = curr.prev;
                }
                pre.next = new Node(value, curr, pre);

            }
        }
        return true;
    }

    public boolean remove(int index) {
        if (index == 0) {
            if (this.head != null) {
                this.head = this.head.next;
                return true;
            } else {
                return false;
            }
        }
        if (index == cnt) {
            if (this.head != null) {
                this.tail = this.tail.prev;
                return true;
            } else {
                return false;
            }
        }

        Node curr = this.head;
        Node pre = null;

        if (cnt % 2 == 0) {
            if ((cnt / 2) >= index) {

                for (int i = 0; i < index; i++) {
                    pre = curr;
                    curr = curr.next;
                }
                pre.next = curr.next;
                curr.next.prev = curr.prev;

                return true;

            } else {
                curr = this.tail;
                pre = curr.prev;

                for (int i = 0; i < cnt - index; i++) {
                    curr = pre;
                    pre = curr.prev;
                }
                pre.next = curr.next;
                curr.next.prev = pre;
            }
            return true;
        } else {
            if ((cnt / 2 + 1) >= index) {

                for (int i = 0; i < index; i++) {
                    pre = curr;
                    curr = curr.next;
                }
                pre.next = curr.next;
                curr.next.prev = curr.prev;

                return true;

            } else {
                curr = this.tail;
                pre = curr.prev;

                for (int i = 0; i < cnt - index; i++) {
                    curr = pre;
                    pre = curr.prev;
                }
                pre.next = curr.next;
                curr.next.prev = pre;
            }
        }
        return true;
    }

    public String toString() {
        String answer = "";
        if (this.head == null) {
            answer = "[ ]";
        }
        Node curr = this.head;

        answer = "[";
        while (curr != null) {
            answer += curr.value + " ";
            curr = curr.next;
        }
        answer += "]";

        return answer;
    }

    public String toStringInv() {
        return null;
    }
}

public class DoublyLinkedListTest {
    public static void main(String[] args) {

        DoublyLinkedList myList = new DoublyLinkedList();
        System.out.println(myList);

        for (int i = 0; i < 10; i++) {
            myList.append(i + 1);
        }
        System.out.println(myList);

        for (int i = 0; i < 10; i++) {
            myList.prepend(i + 1);
        }
        System.out.println(myList);

        int value = myList.access(3);
        System.out.println("myList.access(3) = " + value);

        myList.insert(8, 128);
        System.out.println(myList);

        myList.remove(4);
        System.out.println(myList);

        myList.setHead(10);
        System.out.println(myList);
    }
}
```