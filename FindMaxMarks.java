import java.util.*;

public class FindMaxMarks {

    public static void main(String[] args) {
        Stack<Integer> marksStack = new Stack<>();
        marksStack.push(11);
        marksStack.push(14);
        marksStack.push(13);
        marksStack.push(12);

        Queue<String> namesQueue = new ArrayDeque<String>();
        namesQueue.add("Ricky");
        namesQueue.add("Surya");
        namesQueue.add("Steve");
        namesQueue.add("Akram");

        getMaxScorer(marksStack, namesQueue);

        marksStack.clear();
        namesQueue.clear();

        marksStack.push(1);
        namesQueue.add("Saurav");
        getMaxScorer(marksStack, namesQueue);

        marksStack.clear();
        namesQueue.clear();

        namesQueue.add("Rahul");
        namesQueue.add("Sehwag");
        namesQueue.add("Sachin");

        marksStack.push(50);
        marksStack.push(40);
        marksStack.push(80);

        getMaxScorer(marksStack, namesQueue);

        marksStack.clear();
        namesQueue.clear();
        getMaxScorer(marksStack, namesQueue);

    }

private static void getMaxScorer(Stack<Integer> marksStack, Queue<String> namesQueue) {
    try{
    System.out.println("=========Begin Calculation======");
    if(namesQueue.size() == 0){
        throw new IllegalArgumentException("Empty Input, cannot calculate");
    }
    if(namesQueue.size() == 1){
        System.out.println("The highest scorer is " + namesQueue.poll() + " with a score of " + marksStack.pop());
        return;
    }
    Queue<Integer> reversalQueue =  new ArrayDeque<>();

    System.out.println("Marks -- "+ marksStack);
    System.out.println("Names -- "+ namesQueue);


    while(!marksStack.isEmpty()){
        reversalQueue.add(marksStack.pop());
    }

    while(!reversalQueue.isEmpty()){
        marksStack.push(reversalQueue.poll());
    }

    int maxScore = marksStack.pop();
    String maxScorer = namesQueue.poll();

    while(!marksStack.isEmpty()){
        int score = marksStack.pop();
        String scorer = namesQueue.poll();
        if(score > maxScore){
            maxScore = score;
            maxScorer = scorer;
        }
    }


    System.out.println("Marks -- "+ marksStack);
    System.out.println("Names -- "+ namesQueue);
    System.out.println("The highest scorer is " + maxScorer + " with a score of " + maxScore);
}
finally{
            System.out.println("=========End Calculation======");
}
}

}