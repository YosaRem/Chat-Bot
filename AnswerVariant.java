package firstTask;

public enum AnswerVariant {
	A("a"), B("b"), C("c"), D("d");
	private String name;

	private AnswerVariant(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
