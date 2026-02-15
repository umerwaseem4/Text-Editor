package dal;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class HashCalculatorTest {

	@Test
	@DisplayName("Hash of known input should return correct MD5 hex string")
	void testCalculateHashKnownInput() throws Exception {
		String input = "hello world";
		String hash = HashCalculator.calculateHash(input);
		// MD5 of "hello world" is 5eb63bbbe01eeed093cb22bb8f5acdc3
		assertEquals("5EB63BBBE01EEED093CB22BB8F5ACDC3", hash);
	}

	@Test
	@DisplayName("Hash should return consistent result for same input")
	void testHashConsistency() throws Exception {
		String input = "test document content";
		String hash1 = HashCalculator.calculateHash(input);
		String hash2 = HashCalculator.calculateHash(input);
		assertEquals(hash1, hash2);
	}

	@Test
	@DisplayName("Different inputs should produce different hashes")
	void testDifferentInputsDifferentHashes() throws Exception {
		String hash1 = HashCalculator.calculateHash("document version 1");
		String hash2 = HashCalculator.calculateHash("document version 2");
		assertNotEquals(hash1, hash2);
	}

	@Test
	@DisplayName("Hash should return 32 character hex string")
	void testHashLength() throws Exception {
		String hash = HashCalculator.calculateHash("any input");
		assertEquals(32, hash.length());
	}

	@Test
	@DisplayName("Empty string should still produce a valid hash")
	void testEmptyStringHash() throws Exception {
		String hash = HashCalculator.calculateHash("");
		assertNotNull(hash);
		assertEquals(32, hash.length());
		// MD5 of empty string is d41d8cd98f00b204e9800998ecf8427e
		assertEquals("D41D8CD98F00B204E9800998ECF8427E", hash);
	}

	@Test
	@DisplayName("Editing file content should change the hash")
	void testEditingChangesHash() throws Exception {
		String originalContent = "This is the original file content";
		String editedContent = "This is the edited file content";
		String originalHash = HashCalculator.calculateHash(originalContent);
		String editedHash = HashCalculator.calculateHash(editedContent);
		assertNotEquals(originalHash, editedHash, "Editing a file should change its hash");
	}
}
