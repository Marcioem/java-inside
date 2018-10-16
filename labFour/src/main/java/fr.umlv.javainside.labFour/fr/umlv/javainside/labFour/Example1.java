package fr.umlv.javainside.labFour;

public class Example1 {

    public static void main(String[] args) {
        ContinuationScope contScope1 = new ContinuationScope("hello1");
        Runnable runContinuation = () -> {
            System.out.println(Continuation.getCurrentContinuation(contScope1));
            Continuation.yield(contScope1);
            System.out.println("hello continuation");
            Continuation.yield(contScope1);
            System.out.println("Re-hello continuation");
            Continuation.yield(contScope1);
            System.out.println("You can continue continuation");
            Continuation.yield(contScope1);
            System.out.println("You can finish continuation");
            Continuation.yield(contScope1);
            System.out.println("I said FINISH!");
            Continuation.yield(contScope1);
            System.out.println("Thanks continuation");
        };
        Continuation cont1 = new Continuation(contScope1,runContinuation);
        Continuation cont2 = new Continuation(contScope1,runContinuation);

        while(!(cont1.isDone() && cont2.isDone())){
            if(!cont1.isDone()){
                cont1.run();
            }
            if(!cont2.isDone()){
                cont2.run();
            }
        }
        /*
        cont1.run();
        System.out.println(cont1.isDone());
        cont1.run();
        System.out.println(cont1.isDone());
        System.out.println(Continuation.getCurrentContinuation(contScope1));*/
    }
}
