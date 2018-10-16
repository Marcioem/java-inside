package fr.umlv.javainside.labFour;

public class Example1 {

    public static void main(String[] args) {
        ContinuationScope contScope1 = new ContinuationScope("hello1");
        Continuation cont1 = new Continuation(contScope1,()-> {
            System.out.println("hello continuation");
        });
        cont1.run();
        cont1.run();
    }

}
