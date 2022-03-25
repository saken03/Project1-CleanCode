import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Quiz {
    String name;
    static ArrayList<Question> questions = new ArrayList<Question> ();

    public Quiz() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static void addQuestion(Question question) {
        questions.add(question);
    }
    
    public static void loadFromFile() throws FileNotFoundException { // should be 'quiz' instead of 'void'
        File file = new File("Suraqtar.txt");
        Scanner fileReader = new Scanner(file);
        while(fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            if (data.contains("{blank}")) {
                Question fillin = new Fillin();
                fillin.setDescription(data);
                fillin.setAnswer(fileReader.nextLine());
                addQuestion(fillin);
            }
            else {
                Question test = new Test();
                test.setDescription(data);
                String options [] = new String [4];
                for (int i = 0; i < 4; i++) {
                    options[i] = fileReader.nextLine();
                }
                test.setAnswer(options[0]);
                ((Test) test).setOptions(options);
                addQuestion(test);
            }
        }
        fileReader.close();
    }

    public void start() throws FileNotFoundException {
        int result = 0;

        loadFromFile();
        Collections.shuffle(questions);


        ArrayList<Integer> order = new ArrayList<Integer>(4);
        for (int j = 0; j < 4; j++) {
            order.add(j);
        }

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            Scanner scan = new Scanner(System.in);

            if (question instanceof Test) {
                Collections.shuffle(order);

                System.out.println(question);

                for (int j = 0; j < 4; j++) {
                    System.out.println(((Test) question).labels[j] + ". "
                    + ((Test) question).getOptionAt(order.get(j)));
                }

                while (true) {
                    System.out.println("Input your answer please: ");
                    String ans = scan.next();
                    int answer = -1;

                    if (ans.equals("A")) {
                        answer = 0;
                    }
                    if (ans.equals("B")) {
                        answer = 1;
                    }
                    if (ans.equals("C")) {
                        answer = 2;
                    }
                    if (ans.equals("D")) {
                        answer = 3;
                    }

                    if (answer < 0 || answer > 3) {
                        System.out.println("Invalid input");
                        continue;
                    }

                    if (question.getAnswer().equals(((Test)question).getOptionAt(order.get(answer)))) {
                        System.out.println("Correct!");
                        result++;
                    }
                    else {
                        System.out.println("Incorrect!");
                    }
                    break;
                }
            }
            else {
                System.out.println(question);
                String answer = scan.nextLine();
                if (question.getAnswer().equals(answer)) {
                    System.out.println("Correct!");
                    result++;
                }
                else {
                    System.out.println("Incorrect!");
                }
            }
        }

        System.out.println("Quiz is ended\nYour result is: " + result + " from "
    + questions.size() + "\nIt's about: " + (double)result / questions.size() * 100 + "%.");
    }

    public String toString() {
        String st = "Quiz name: " + name + '\n';
        return st;
    }
}
