package fr.umlv.javainside.labFour;

import java.util.ArrayDeque;
import java.util.Objects;

public class Scheduler {
    private final ArrayDeque<Continuation> continuationQueue;
/*    private final Politique politique;

    static enum Politique{

    }

    public Scheduler(Politique politique) {
        this.continuationQueue = new ArrayDeque<>();
        this.politique=politique;
    }
*/

    public Scheduler() {
        this.continuationQueue = new ArrayDeque<>();
    }

    public void enqueue(ContinuationScope contScope){
        Objects.requireNonNull(contScope);
        Continuation currentCont = Continuation.getCurrentContinuation(contScope);
        if(currentCont==null){
            throw new IllegalStateException();
        }
        continuationQueue.add(currentCont);
        Continuation.yield(contScope);
    }

    public void runLoop(){
        while(!continuationQueue.isEmpty()){
            Continuation first = continuationQueue.getFirst();
            continuationQueue.remove(first);
            first.run();

        }
    }

    public static void main(String[] args) {
        Scheduler sched = new Scheduler();
        ContinuationScope contScope1 = new ContinuationScope("hello1");
        ContinuationScope contScope2 = new ContinuationScope("hello2");
        Runnable runContinuation1 = () -> {
            System.out.println(Continuation.getCurrentContinuation(contScope1));
            sched.enqueue(contScope1);
            System.out.println("hello continuation");
            sched.enqueue(contScope1);
            System.out.println("Re-hello continuation");
            sched.enqueue(contScope1);
            System.out.println("You can continue continuation");
            sched.enqueue(contScope1);
            System.out.println("You can finish continuation");
            sched.enqueue(contScope1);
            System.out.println("I said FINISH!");
            sched.enqueue(contScope1);
            System.out.println("Thanks continuation");
        };
        Runnable runContinuation2 = () -> {
            System.out.println(Continuation.getCurrentContinuation(contScope2));
            sched.enqueue(contScope2);
            System.out.println("hello continuation2");
            sched.enqueue(contScope2);
            System.out.println("Re-hello continuation2");
            sched.enqueue(contScope2);
            System.out.println("You can continue continuation2");
            sched.enqueue(contScope2);
            System.out.println("You can finish continuation2");
            sched.enqueue(contScope2);
            System.out.println("I said 2 FINISH!");
            sched.enqueue(contScope2);
            System.out.println("Thanks continuation2");
        };
        Continuation cont1 = new Continuation(contScope1,runContinuation1);
        Continuation cont2 = new Continuation(contScope2,runContinuation2);
        Continuation cont3 = new Continuation(contScope1,runContinuation1);

        cont1.run();
        cont2.run();
        cont3.run();


        sched.runLoop();

    }
}
