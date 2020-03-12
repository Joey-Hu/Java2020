package queue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author: huhao
 * @time: 2020/3/12 9:05
 * @desc:
 */
public class TestConurrentLinkedQueue {
    public static void main(String[] args) {
        Queue<String> queue = new ConcurrentLinkedQueue<String>();

        // boolean offer(E e)  在该队列的尾部插入指定的元素。
        queue.offer("Hello");
        queue.offer("World");
        // E poll()  检索并删除此队列的头部，如果此队列为空，则返回 null 。
        queue.poll();
        // E peek()  检索但不删除此队列的头，如果此队列为空，则返回 null 。
        queue.peek();
    }
}
