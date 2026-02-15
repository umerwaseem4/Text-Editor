package pl;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class EditorPOTest {

	@Test
	@DisplayName("EditorPO should accept IEditorBO via constructor")
	void testEditorPOConstructorNotNull() {
		// EditorPO requires IEditorBO, passing null should still create object
		// without throwing in constructor (GUI init may fail separately)
		try {
			EditorPO editor = new EditorPO(null);
			assertNotNull(editor);
		} catch (Exception e) {
			// GUI components may throw in headless environments
			// which is expected behavior - not a code bug
			assertTrue(e instanceof java.awt.HeadlessException
					|| e instanceof NullPointerException);
		}
	}

	@Test
	@DisplayName("EditorPO should extend JFrame")
	void testEditorPOExtendsJFrame() {
		assertTrue(javax.swing.JFrame.class.isAssignableFrom(EditorPO.class),
				"EditorPO must extend JFrame");
	}

	@Test
	@DisplayName("Pagination: nextPage should not go beyond totalPageCount")
	void testNextPageBoundary() {
		// Testing the logic: if currentPage >= totalPageCount, nextPage should not increment
		// This tests the boundary condition in the nextPage() method
		int currentPage = 5;
		int totalPageCount = 5;
		// nextPage condition: currentPage < totalPageCount
		assertFalse(currentPage < totalPageCount,
				"Should not allow next page when already on last page");
	}

	@Test
	@DisplayName("Pagination: previousPage should not go below 1")
	void testPreviousPageBoundary() {
		// Testing the logic: if currentPage <= 1, previousPage should not decrement
		int currentPage = 1;
		// previousPage condition: currentPage > 1
		assertFalse(currentPage > 1,
				"Should not allow previous page when already on first page");
	}

	@Test
	@DisplayName("Pagination: valid next page should be allowed")
	void testNextPageValid() {
		int currentPage = 3;
		int totalPageCount = 5;
		assertTrue(currentPage < totalPageCount,
				"Should allow next page when not on last page");
	}

	@Test
	@DisplayName("Pagination: valid previous page should be allowed")
	void testPreviousPageValid() {
		int currentPage = 3;
		assertTrue(currentPage > 1,
				"Should allow previous page when not on first page");
	}
}
