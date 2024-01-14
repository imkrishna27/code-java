package collections.Queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayDequeImpl {
    public static void main(String[] args) {
        ArrayDequeImpl.implement();
    }

    /**
     An ArrayDeque (also known as an “Array Double Ended Queue”, pronounced as “ArrayDeck”)
     is a special kind of growable array that allows us to add or remove an element from both sides.
     An ArrayDeque implementation can be used as a Stack (Last-In-First-Out) or a Queue(First-In-First-Out).

     Operation	            Method	        -Method throwing Exception
     Insertion from Head	offerFirst(e)	addFirst(e)
     Removal from Head	    pollFirst()	    removeFirst()
     Retrieval from Head	peekFirst()	    getFirst()
     Insertion from Tail	offerLast(e)	addLast(e)
     Removal from Tail	    pollLast()	    removeLast()
     Retrieval from Tail	peekLast()	   getLast()

     It's not thread-safe
     Null elements are not accepted
     Works significantly faster than the synchronized Stack
     It is a faster queue than LinkedList due to the better locality of reference
     Most operations have amortized constant time complexity
     An Iterator returned by an ArrayDeque is fail-fast
     ArrayDeque automatically doubles the size of an array when the head and tail pointer meets each other while adding an element
     */
    private static void implement() {
        /* Using as a stack */
        Deque<String> stack = new ArrayDeque<>();
        stack.push("hari");
        System.out.println(stack.pop());
        System.out.println(stack);
        /* Using as a queue */
        Deque<String> queue = new ArrayDeque<>();
        queue.offer("krishna");
        queue.offer("hari");
        System.out.println(queue.peekLast());
        System.out.println(queue);
    }
}
