package dal;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
import dto.Pages;

public class PaginationDAOTest {

	@Test
	@DisplayName("Null input should return single empty page")
	void testPaginateNullInput() {
		List<Pages> result = PaginationDAO.paginate(null);
		assertEquals(1, result.size());
		assertEquals("", result.get(0).getPageContent());
		assertEquals(1, result.get(0).getPageNumber());
	}

	@Test
	@DisplayName("Empty string should return single empty page")
	void testPaginateEmptyString() {
		List<Pages> result = PaginationDAO.paginate("");
		assertEquals(1, result.size());
		assertEquals("", result.get(0).getPageContent());
	}

	@Test
	@DisplayName("Short content under 100 chars should fit in one page")
	void testPaginateShortContent() {
		String content = "Short text";
		List<Pages> result = PaginationDAO.paginate(content);
		assertEquals(1, result.size());
		assertEquals(content, result.get(0).getPageContent());
		assertEquals(1, result.get(0).getPageNumber());
	}

	@Test
	@DisplayName("Exactly 100 chars should produce one page")
	void testPaginateExactPageSize() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 100; i++) {
			sb.append("a");
		}
		List<Pages> result = PaginationDAO.paginate(sb.toString());
		assertEquals(1, result.size());
		assertEquals(100, result.get(0).getPageContent().length());
	}

	@Test
	@DisplayName("101 chars should produce two pages")
	void testPaginateTwoPages() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 101; i++) {
			sb.append("b");
		}
		List<Pages> result = PaginationDAO.paginate(sb.toString());
		assertEquals(2, result.size());
		assertEquals(100, result.get(0).getPageContent().length());
		assertEquals(1, result.get(1).getPageContent().length());
	}

	@Test
	@DisplayName("Page numbers should be sequential starting from 1")
	void testPageNumbersSequential() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 250; i++) {
			sb.append("c");
		}
		List<Pages> result = PaginationDAO.paginate(sb.toString());
		assertEquals(3, result.size());
		assertEquals(1, result.get(0).getPageNumber());
		assertEquals(2, result.get(1).getPageNumber());
		assertEquals(3, result.get(2).getPageNumber());
	}

	@Test
	@DisplayName("Total content across all pages should equal original content")
	void testContentIntegrity() {
		String content = "This is a test string that is used to verify pagination integrity across multiple pages of content in the system";
		List<Pages> result = PaginationDAO.paginate(content);
		StringBuilder reconstructed = new StringBuilder();
		for (Pages page : result) {
			reconstructed.append(page.getPageContent());
		}
		assertEquals(content, reconstructed.toString());
	}
}
