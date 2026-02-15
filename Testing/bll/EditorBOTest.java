package bll;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class EditorBOTest {

	private EditorBO editorBO;

	@Test
	@DisplayName("getFileExtension should return txt for text files")
	void testGetFileExtensionTxt() {
		EditorBO bo = new EditorBO(null);
		assertEquals("txt", bo.getFileExtension("document.txt"));
	}

	@Test
	@DisplayName("getFileExtension should return md5 for md5 files")
	void testGetFileExtensionMd5() {
		EditorBO bo = new EditorBO(null);
		assertEquals("md5", bo.getFileExtension("file.md5"));
	}

	@Test
	@DisplayName("getFileExtension should return empty string for no extension")
	void testGetFileExtensionNone() {
		EditorBO bo = new EditorBO(null);
		assertEquals("", bo.getFileExtension("filename"));
	}

	@Test
	@DisplayName("getFileExtension should handle multiple dots correctly")
	void testGetFileExtensionMultipleDots() {
		EditorBO bo = new EditorBO(null);
		assertEquals("txt", bo.getFileExtension("my.file.name.txt"));
	}

	@Test
	@DisplayName("getFileExtension should handle dot at start")
	void testGetFileExtensionDotAtStart() {
		EditorBO bo = new EditorBO(null);
		assertEquals("gitignore", bo.getFileExtension(".gitignore"));
	}
}
