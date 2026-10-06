package Object_Class.class_problems;

import java.util.ArrayList;
import java.util.List;

abstract class Question {
    private String id;
    private String text;
    private double marks;

    public Question(String id, String text, double marks) {
        this.id = id;
        this.text = text;
        this.marks = marks;
    }

    public double getMarks() { return marks; }
    public String getId() { return id; }

    public abstract boolean evaluate(String answer);
}

class MCQQuestion extends Question {
    private String correctAnswer;

    public MCQQuestion(String id, String text, double marks, String correctAnswer) {
        super(id, text, marks);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer != null ? answer.trim() : "");
    }
}

class TrueFalseQuestion extends Question {
    private boolean correctAnswer;

    public TrueFalseQuestion(String id, String text, double marks, boolean correctAnswer) {
        super(id, text, marks);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return String.valueOf(correctAnswer).equalsIgnoreCase(answer != null ? answer.trim() : "");
    }
}

class Answer {
    private Question question;
    private String providedAnswer;

    public Answer(Question question, String providedAnswer) {
        this.question = question;
        this.providedAnswer = providedAnswer;
    }

    public Question getQuestion() { return question; }
    public String getProvidedAnswer() { return providedAnswer; }
}

class Attempt {
    private String studentId;
    private List<Answer> answers = new ArrayList<>();
    private boolean isSubmitted = false;
    private double totalScore = 0.0;

    public Attempt(String studentId) {
        this.studentId = studentId;
    }

    public boolean isSubmitted() { return isSubmitted; }

    public void recordAnswer(Question question, String answerText) {
        if (isSubmitted) {
            System.out.println("Attempt already submitted. Answers cannot be changed.");
            return;
        }
        answers.add(new Answer(question, answerText));
    }

    public double submit() {
        if (isSubmitted) {
            System.out.println("Attempt already submitted.");
            return totalScore;
        }
        isSubmitted = true;
        totalScore = 0.0;
        for (Answer ans : answers) {
            if (ans.getQuestion().evaluate(ans.getProvidedAnswer())) {
                totalScore += ans.getQuestion().getMarks();
            }
        }
        return totalScore;
    }
}

public class OnlineExaminationSystem {
    public static void main(String[] args) {
        Question q1 = new MCQQuestion("Q1", "Java is platform independent? (A/B)", 5.0, "A");
        Question q2 = new TrueFalseQuestion("Q2", "Interfaces support multiple inheritance.", 5.0, true);

        Attempt attempt = new Attempt("ST101");
        attempt.recordAnswer(q1, "A");
        attempt.recordAnswer(q2, "true");

        double score = attempt.submit();
        System.out.println("Examination Submitted successfully. Overall Score: " + score);

        // Attempting to modify after submission
        attempt.recordAnswer(q1, "B");
    }
}