package wordle.game;

import wordle.Util;

public class GuessResult {
    private String guess;
    private String answer;
    public char[] feedback;
    public boolean isRight;

    // Construtor
    public GuessResult(String guess, String answer) {
        this.guess = guess;
        this.answer = Util.strip(answer);
        this.feedback = setFeedback();
        this.isRight = isRightAnswer();
    }


    private char[] setFeedback() {
        char[] feedback = new char[guess.length()]; // array para armazenar o feedback
        boolean[] answerChecked = new boolean[answer.length()]; // array para marcar letras da resposta já verificadas

        // letras corretas na posição certa (verde)
        for (int i = 0; i < guess.length(); i++) {
            char guessChar = guess.charAt(i);
            char answerChar = answer.charAt(i);

            if (guessChar == answerChar) {
                feedback[i] = 'o';
                answerChecked[i] = true; // marcar como verificada
            } else {
                feedback[i] = 'x';
            }
        }
        // letras corretas na posição errada (amarelo)
        for (int i = 0; i < guess.length(); i++) {
            if (feedback[i] != 'o') {
                char guessChar = guess.charAt(i);


                for (int j = 0; j < answer.length(); j++) {
                    if (!answerChecked[j] && guessChar == answer.charAt(j)) {
                        feedback[i] = '%';
                        answerChecked[j] = true;
                        break;
                    }
                }
            }
        }
        return feedback;
    }

    // verifica se a resposta está correta
    private boolean isRightAnswer() {
        for (char v : feedback) {
            if (v != 'o') {
                return false;
            }
        }
        return true;
    }
}
