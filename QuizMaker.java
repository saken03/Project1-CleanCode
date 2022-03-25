import java.io.FileNotFoundException;

public class QuizMaker {
    public static void main(String[] args) throws FileNotFoundException {
        Quiz quiz = new Quiz();
        quiz.setName("Kahoot");
        System.out.println(quiz);
        quiz.start();

    }
}
