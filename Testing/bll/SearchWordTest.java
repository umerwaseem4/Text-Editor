package bll;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import dto.Documents;
import dto.Pages;

public class SearchWordTest {

	private List<Documents> docs;

	@BeforeEach
	void setUp() {
		List<Pages> pages1 = new ArrayList<>();
		pages1.add(new Pages(1, 1, 1, "the quick brown fox jumps over the lazy dog"));

		List<Pages> pages2 = new ArrayList<>();
		pages2.add(new Pages(2, 2, 1, "hello world this is a test document"));

		docs = new ArrayList<>();
		docs.add(new Documents(1, "file1.txt", "hash1", "2026-01-01", "2026-01-01", pages1));
		docs.add(new Documents(2, "file2.txt", "hash2", "2026-01-01", "2026-01-01", pages2));
	}

	@Test
	@DisplayName("Search should find keyword that exists in document")
	void testSearchFindsExistingKeyword() {
		List<String> results = SearchWord.searchKeyword("fox", docs);
		assertFalse(results.isEmpty(), "Should find 'fox' in documents");
	}

	@Test
	@DisplayName("Search should return empty list for non-existent keyword")
	void testSearchKeywordNotFound() {
		List<String> results = SearchWord.searchKeyword("elephant", docs);
		assertTrue(results.isEmpty(), "Should not find 'elephant' in documents");
	}

	@Test
	@DisplayName("Search with less than 3 characters should throw exception")
	void testSearchShortKeywordThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> {
			SearchWord.searchKeyword("ab", docs);
		});
	}

	@Test
	@DisplayName("Search with exactly 3 characters should not throw")
	void testSearchExactlyThreeChars() {
		assertDoesNotThrow(() -> {
			SearchWord.searchKeyword("the", docs);
		});
	}

	@Test
	@DisplayName("Search should be case insensitive")
	void testSearchCaseInsensitive() {
		List<String> results = SearchWord.searchKeyword("Fox", docs);
		assertFalse(results.isEmpty(), "Search should find 'Fox' matching 'fox' case-insensitively");
	}

	@Test
	@DisplayName("Search result should contain the document name")
	void testSearchResultContainsDocName() {
		List<String> results = SearchWord.searchKeyword("fox", docs);
		assertTrue(results.get(0).contains("file1.txt"), "Result should reference the correct file name");
	}

	@Test
	@DisplayName("Search with empty document list should return empty results")
	void testSearchEmptyDocumentList() {
		List<String> results = SearchWord.searchKeyword("test", new ArrayList<>());
		assertTrue(results.isEmpty());
	}

	@Test
	@DisplayName("Search should find keyword across multiple documents")
	void testSearchAcrossMultipleDocuments() {
		List<String> results = SearchWord.searchKeyword("the", docs);
		assertTrue(results.size() >= 1, "Should find 'the' in at least one document");
	}
}
