package firstTask;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class Permutation {
	public AnswerVariant[] permutation;

	public Permutation() {
		List<AnswerVariant> list = Arrays.asList(AnswerVariant.values());
		Collections.shuffle(list);
		permutation = list.toArray(new AnswerVariant[0]);
	}
}
