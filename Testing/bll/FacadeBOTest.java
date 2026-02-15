package bll;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class FacadeBOTest {

	@Test
	@DisplayName("FacadeBO should accept IEditorBO in constructor")
	void testFacadeBOConstruction() {
		EditorBO bo = new EditorBO(null);
		FacadeBO facade = new FacadeBO(bo);
		assertNotNull(facade);
	}

	@Test
	@DisplayName("FacadeBO getFileExtension should delegate to EditorBO")
	void testFacadeDelegatesGetFileExtension() {
		EditorBO bo = new EditorBO(null);
		FacadeBO facade = new FacadeBO(bo);
		assertEquals("txt", facade.getFileExtension("test.txt"));
	}

	@Test
	@DisplayName("FacadeBO getFileExtension with no extension should return empty")
	void testFacadeDelegatesNoExtension() {
		EditorBO bo = new EditorBO(null);
		FacadeBO facade = new FacadeBO(bo);
		assertEquals("", facade.getFileExtension("noext"));
	}

	@Test
	@DisplayName("FacadeBO should implement IFacadeBO interface")
	void testFacadeImplementsInterface() {
		EditorBO bo = new EditorBO(null);
		FacadeBO facade = new FacadeBO(bo);
		assertTrue(facade instanceof IFacadeBO);
	}
}
