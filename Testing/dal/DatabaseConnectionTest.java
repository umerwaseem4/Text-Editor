package dal;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class DatabaseConnectionTest {

	@Test
	@DisplayName("getInstance should never return null")
	void testGetInstanceNotNull() {
		DatabaseConnection instance = DatabaseConnection.getInstance();
		assertNotNull(instance);
	}

	@Test
	@DisplayName("Singleton: multiple calls should return same instance")
	void testSingletonSameInstance() {
		DatabaseConnection instance1 = DatabaseConnection.getInstance();
		DatabaseConnection instance2 = DatabaseConnection.getInstance();
		assertSame(instance1, instance2, "getInstance() must return the same object every time");
	}

	@Test
	@DisplayName("Singleton: concurrent access should return same instance")
	void testSingletonThreadSafety() throws InterruptedException {
		final DatabaseConnection[] instances = new DatabaseConnection[2];

		Thread t1 = new Thread(() -> instances[0] = DatabaseConnection.getInstance());
		Thread t2 = new Thread(() -> instances[1] = DatabaseConnection.getInstance());

		t1.start();
		t2.start();
		t1.join();
		t2.join();

		assertSame(instances[0], instances[1], "Singleton must be thread-safe");
	}
}
