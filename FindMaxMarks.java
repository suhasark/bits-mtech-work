import java.util.*;

public class FindMaxMarks{

public static void main(String[] args){
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


}