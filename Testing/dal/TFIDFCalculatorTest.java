package dal;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class TFIDFCalculatorTest {

	private TFIDFCalculator calculator;

	@BeforeEach
	void setUp() {
		calculator = new TFIDFCalculator();
	}

	@Test
	@DisplayName("Positive: TF-IDF score for known document in corpus")
	void testTfIdfPositivePath() {
		calculator.addDocumentToCorpus("the cat sat on the mat");
		calculator.addDocumentToCorpus("the dog sat on the log");
		calculator.addDocumentToCorpus("cats and dogs are friends");

		double score = calculator.calculateDocumentTfIdf("the cat sat on the mat");
		assertTrue(score >= 0, "TF-IDF score should be non-negative");
	}

	@Test
	@DisplayName("Positive: Different documents should have different scores")
	void testDifferentDocsHaveDifferentScores() {
		calculator.addDocumentToCorpus("the cat sat on the mat");
		calculator.addDocumentToCorpus("the dog sat on the log");
		calculator.addDocumentToCorpus("fish swim in water");

		double score1 = calculator.calculateDocumentTfIdf("the cat sat on the mat");
		double score2 = calculator.calculateDocumentTfIdf("fish swim in water");
		assertNotEquals(score1, score2, 0.01, "Different documents should yield different TF-IDF scores");
	}

	@Test
	@DisplayName("Positive: Score should be a finite number")
	void testScoreIsFinite() {
		calculator.addDocumentToCorpus("hello world");
		double score = calculator.calculateDocumentTfIdf("hello world");
		assertTrue(Double.isFinite(score), "TF-IDF score must be a finite number");
	}

	@Test
	@DisplayName("Negative: Empty document should not throw exception")
	void testEmptyDocument() {
		calculator.addDocumentToCorpus("some text here");
		assertDoesNotThrow(() -> {
			calculator.calculateDocumentTfIdf("");
		});
	}

	@Test
	@DisplayName("Negative: Special characters only document")
	void testSpecialCharactersDocument() {
		calculator.addDocumentToCorpus("normal text content");
		assertDoesNotThrow(() -> {
			calculator.calculateDocumentTfIdf("!@#$%^&*()");
		});
	}

	@Test
	@DisplayName("Positive: Adding more corpus documents should change scores")
	void testCorpusGrowthAffectsScore() {
		calculator.addDocumentToCorpus("the cat sat on the mat");
		double scoreBefore = calculator.calculateDocumentTfIdf("the cat sat on the mat");

		calculator.addDocumentToCorpus("unique words never seen before");
		double scoreAfter = calculator.calculateDocumentTfIdf("the cat sat on the mat");

		assertNotEquals(scoreBefore, scoreAfter, 0.001, "Adding new corpus docs should change TF-IDF scores");
	}
}
