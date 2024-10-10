import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class JumbledWordsGame {
    private List<String> words;
    private String currentWord;
    private String jumbledWord;
    private int score;
    private JFrame frame;
    private JLabel jumbledLabel;
    private JTextField answerField;
    private JButton submitButton;
    private JLabel scoreLabel;

    public JumbledWordsGame() {
        initializeGame();
    }

    private void initializeGame() {
        words = new ArrayList<>();
        words.add("java");
        words.add("pseudocode");
        words.add("algorithm");
        words.add("project");
        words.add("computer");
        words.add("development");
        words.add("programming");
        words.add("interface");
        words.add("software");
        words.add("debugging");

        currentWord = getRandomWord(words);
        jumbledWord = jumbleWord(currentWord);

        frame = new JFrame("Jumbled Words Game");
        jumbledLabel = new JLabel("Jumbled Word: " + jumbledWord);
        answerField = new JTextField(20);
        submitButton = new JButton("Submit");
        scoreLabel = new JLabel("Score: " + score);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                checkAnswer();
            }
        });

        frame.setLayout(new FlowLayout());
        frame.add(jumbledLabel);
        frame.add(answerField);
        frame.add(submitButton);
        frame.add(scoreLabel);
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private String getRandomWord(List<String> wordList) {
        Random random = new Random();
        return wordList.get(random.nextInt(wordList.size()));
    }

    private String jumbleWord(String word) {
        char[] letters = word.toCharArray();
        Random random = new Random();
        for (int i = 0; i < letters.length; i++) {
            int j = random.nextInt(letters.length);
            char temp = letters[i];
            letters[i] = letters[j];
            letters[j] = temp;
        }
        return new String(letters);
    }

    private void checkAnswer() {
        String userAnswer = answerField.getText().trim();
        if (userAnswer.equalsIgnoreCase(currentWord)) {
            score++;
            scoreLabel.setText("Score: " + score);
            currentWord = getRandomWord(words);
            jumbledWord = jumbleWord(currentWord);
            jumbledLabel.setText("Jumbled Word: " + jumbledWord);
            answerField.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Try Again!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JumbledWordsGame::new);
    }
}
