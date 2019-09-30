package firstTask;

import java.util.HashMap;

public class Question {
	public String question;
	public HashMap<AnswerVariant, String> answers;
	public AnswerVariant rightAnswer;

	public Question(String[] data) {
		question = data[0];
		answers = new HashMap<>();
		fillAnswers(data);
	}

	private void fillAnswers(String[] lines) {
		Permutation perm = new Permutation();
		for (var i = 0; i < 4; i++) {
			answers.put(perm.permutation[i], lines[i + 1]);
		}
		rightAnswer = perm.permutation[0];
	}

	public boolean isRightAnswer(AnswerVariant answer) {
		return answer == rightAnswer;
	}
}
