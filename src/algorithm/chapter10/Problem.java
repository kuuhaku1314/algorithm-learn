package algorithm.chapter10;


/**
 * @author by kuuhaku
 * @Date 2021/4/10 10:41
 * @Description
 */
public class Problem {

    /**
     * 10.1-2 大小为n的数组内存2个栈确保总和不大于n就不会溢出
     * @param <E>
     */
    static class TwoStack<E> {
        private final int capacity;
        private int size;
        private E[] items;
        private int topOne;
        private int topTwo;

        public TwoStack(int capacity) {
            this.capacity = capacity;
            items = (E[]) new Object[capacity];
            topOne = -1;
            topTwo = capacity;
        }

        public boolean pushOne(E e) {
            if (topOne < topTwo - 1) {
                items[++topOne] = e;
                ++size;
                return true;
            }
            return false;
        }

        public E popOne() {
            if (topOne == -1) {
                return null;
            }
            E item = items[topOne];
            --topOne;
            --size;
            return item;
        }

        public boolean pushTwo(E e) {
            if (topOne < topTwo - 1) {
                items[--topTwo] = e;
                ++size;
                return true;
            }
            return false;
        }

        public E popTwo() {
            if (topOne == capacity) {
                return null;
            }
            E item = items[topTwo];
            ++topTwo;
            --size;
            return item;
        }
    }

    /**
     * 10.1-4 不可扩容队列，可以处理溢出
     * @param <E>
     */
    static class Queue<E> {
        private int head;
        private int tail;
        private int size;
        private final int capacity;
        private final E[] items;

        public Queue(int capacity) {
            this.capacity = capacity;
            items = (E[]) new Object[capacity];
        }

        public boolean enqueue(E e) {
            if (size >= capacity) {
                return false;
            }
            items[tail++] = e;
            ++size;
            if (tail == capacity) {
                tail = 0;
            }
            return true;
        }

        public E dequeue() {
            if (size == 0) {
                return null;
            }
            E item = items[head++];
            --size;
            if (head == capacity) {
                head = 0;
            }
            return item;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    /**
     * 10.1-5 双端队列
     * @param <E>
     */
    static class Deque<E> {
        private int head;
        private int tail;
        private int size;
        private final int capacity;
        private final E[] items;

        public Deque(int capacity) {
            this.capacity = capacity;
            items = (E[]) new Object[capacity];
        }

        public boolean addFirst(E e) {
            if (size >= capacity) {
                return false;
            }
            items[tail++] = e;
            ++size;
            if (tail == capacity) {
                tail = 0;
            }
            return true;
        }

        public E pollFirst() {
            if (size == 0) {
                return null;
            }
            E item = items[head++];
            --size;
            if (head == capacity) {
                head = 0;
            }
            return item;
        }

        public boolean addLast(E e) {
            if (size >= capacity) {
                return false;
            }
            if (head == 0) {
                items[capacity - 1] = e;
                head = capacity - 1;
            } else {
                items[--head] = e;
            }
            ++size;
            return true;
        }

        public E pollLast() {
            if (size == 0) {
                return null;
            }
            E item;
            if (tail == 0) {
                item = items[capacity - 1];
                --tail;
            } else {
                item = items[--tail];
            }
            --size;
            return item;
        }
    }

    static abstract class Stack<E> {
        public abstract void push(E e);
        public abstract E pop();
        public abstract int size();
        public boolean isEmpty() {return size() == 0;};
    }

    /**
     * 10.1-6 两个栈实现一个队列，时间复杂度n
     * @param <E>
     */
    static class QueueByTwoStack<E> {
        public Stack<E> stackOne;
        public Stack<E> stackTwo;
        public void enqueue(E e) {
            if (stackTwo.isEmpty()) {
                stackTwo.push(e);
            } else {
                stackOne.push(e);
            }
        }
        public E dequeue() {
            if (stackTwo.isEmpty()) {
                while (!stackOne.isEmpty()) {
                    stackTwo.push(stackOne.pop());
                }
                return stackTwo.pop();
            } else {
                while (!stackTwo.isEmpty()) {
                    stackOne.push(stackTwo.pop());
                }
                return stackOne.pop();
            }
        }
    }

    /**
     * 10.1-7 两个队列实现一个栈，时间复杂度n
     * @param <E>
     */
    static class StackByTwoQueue<E> {
        public Queue<E> queueOne;
        public Queue<E> queueTwo;
        public void push(E e) {
            if (queueTwo.isEmpty()) {
                queueTwo.enqueue(e);
            } else {
                queueOne.enqueue(e);
            }
        }
        public E pop() {
            if (queueTwo.isEmpty()) {
                while (!queueOne.isEmpty()) {
                    queueTwo.enqueue(queueOne.dequeue());
                }
                return queueTwo.dequeue();
            } else {
                while (!queueTwo.isEmpty()) {
                    queueOne.enqueue(queueTwo.dequeue());
                }
                return queueOne.dequeue();
            }
        }
    }

    public static class Node<E> {
        Node<E> next;
        E item;

        public Node(Node<E> next, E item) {
            this.next = next;
            this.item = item;
        }
    }

    /**
     * 12.2-2 链表堆栈
     * @param <E>
     */
    static class LinkedStack<E> {
        private final Node<E> top = new Node<>(null, null);

        public void push(E e) {
            top.next = new Node<>(top.next, e);
        }

        public E pop() {
            Node<E> next = top.next;
            if (next == null) {
                return null;
            }
            top.next = top.next.next;
            return next.item;
        }
    }


    /**
     * 12.2-3 链表队列
     * @param <E>
     */
    static class LinkedQueue<E> {
        private final Node<E> dummyNode = new Node<>(null, null);
        private final Node<E> head = new Node<>(dummyNode, null);
        private final Node<E> tail = new Node<>(dummyNode, null);

        public void enqueue(E e) {
            Node<E> newNode = new Node<>(null, e);
            if (head.next == dummyNode) {
                head.next = newNode;
                tail.next = newNode;
            } else {
                tail.next.next = newNode;
            }
            tail.next = newNode;
        }

        public E dequeue() {
            Node<E> node = head.next;
            if (node == tail.next && node != dummyNode) {
                head.next = dummyNode;
                tail.next = dummyNode;
                return node.item;
            } else if (node == tail.next) {
                return null;
            } else {
                head.next = head.next.next;
                return node.item;
            }
        }
    }

    /**
     * 单向链表
     * @param <E>
     */
    static class LinkedNode<E> {
        private Node<E> first;
        private Node<E> last;
        private int size;

        public void insert(E e) {
            Node<E> l = last;
            Node<E> newNode = new Node<>(null, e);
            last = newNode;
            if (l == null) {
                first = newNode;
            } else {
                l.next = newNode;
            }
            ++size;
        }

        public E search(E e) {
            Node<E> node = first;
            while (node != null) {
                if (node.item == e) {
                    return node.item;
                }
                node = node.next;
            }
            return null;
        }

        public boolean delete(E e) {
            Node<E> node = first;
            if (first == null) {
                return false;
            }
            if (first.item == e) {
                first = null;
                last = null;
                --size;
                return true;
            }
            Node<E> next = node.next;
            while (next != null) {
                if (next.item == e) {
                    node.next = next.next;
                    --size;
                    return true;
                }
                node = node.next;
                next = node.next;
            }
            return false;
        }
    }

    /**
     * 10.2-5 单向循环链表
     * @param <E>
     */
    static class CycleLinkedList<E> {
        private final Node<E> NIL = new Node<>(null, null);
        private int size;

        public void insert(E e) {
            Node<E> newNode = new Node<>(NIL, e);
            Node<E> first = NIL.next;
            if (first == null) {
                NIL.next = newNode;
            } else {
                while (first.next != NIL) {
                    first = first.next;
                }
                first.next = newNode;
            }
            ++size;
        }

        public E search(E e) {
            Node<E> first = NIL.next;
            if (first != null) {
                while (first != NIL) {
                    if (first.item == e) {
                        return first.item;
                    }
                    first = first.next;
                }
            }
            return null;
        }

        public boolean delete(E e) {
            Node<E> first = NIL;
            if (first.next == null) {
                return false;
            }
            while (first.next != NIL) {
                if (first.next.item == e) {
                    first.next = first.next.next;
                    --size;
                    return true;
                }
                first = first.next;
            }
            return false;
        }
    }

    /**
     * 10.2-7 翻转链表
     * @param node
     * @param <E>
     * @return
     */
    public static <E> Node<E> reverseNodeList(Node<E> node) {
        if (node == null || node.next == null) {
            return node;
        }
        Node<E> indexNode = node;
        Node<E> nextNode = node.next, temp;
        indexNode.next = null;
        do {
            // 保存当前节点
            temp = indexNode;
            // 移动当前节点位置
            indexNode = nextNode;
            // 移动下一个节点位置
            nextNode = nextNode.next;
            // 指向前一个节点
            indexNode.next = temp;
        } while (nextNode != null);
        return indexNode;
    }

    public static void main(String[] args) {
        Node<Integer> node1 = new Node<>(null, 1);
        Node<Integer> node2 = new Node<>(null, 2);
        Node<Integer> node3 = new Node<>(null, 3);
        Node<Integer> node4 = new Node<>(null, 4);
        Node<Integer> node5 = new Node<>(null, 5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        Node<Integer> integerNode = reverseNodeList(node1);
        do {
            System.out.println(integerNode.item);
            integerNode = integerNode.next;
        } while (integerNode != null);


    }



}
