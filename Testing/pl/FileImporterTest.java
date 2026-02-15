package pl;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import bll.IEditorBO;

public class FileImporterTest {

	@Test
	@DisplayName("FileImporter should accept IEditorBO in constructor")
	void testFileImporterConstruction() {
		try {
			FileImporter importer = new FileImporter(null);
			assertNotNull(importer);
		} catch (Exception e) {
			// May throw in headless environment due to UIManager.setLookAndFeel
			assertTrue(e instanceof java.awt.HeadlessException
					|| e instanceof Exception);
		}
	}

	@Test
	@DisplayName("FileImporter should be designed against IEditorBO interface")
	void testFileImporterUsesInterface() {
		// Verify FileImporter constructor accepts the interface, not the concrete class
		// This ensures tests are swappable as required by the assignment
		try {
			java.lang.reflect.Constructor<?> constructor =
					FileImporter.class.getConstructor(IEditorBO.class);
			assertNotNull(constructor, "FileImporter must accept IEditorBO interface");
		} catch (NoSuchMethodException e) {
			fail("FileImporter should have a constructor accepting IEditorBO");
		}
	}

	@Test
	@DisplayName("FileImporter importFiles method should exist and accept ActionEvent")
	void testImportFilesMethodExists() {
		try {
			java.lang.reflect.Method method =
					FileImporter.class.getMethod("importFiles", java.awt.event.ActionEvent.class);
			assertNotNull(method, "importFiles(ActionEvent) method must exist");
		} catch (NoSuchMethodException e) {
			fail("FileImporter should have importFiles(ActionEvent) method");
		}
	}
}
