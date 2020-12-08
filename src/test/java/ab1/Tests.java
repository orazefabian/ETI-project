package ab1;

import ab1.impl.Sandner_Kleewein_Oraze.RMImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class Tests {
	private static int pts;

	private RM rm;

	@BeforeEach
	public void setup() {
		rm = new RMImpl();
	}

	@Test
	public void memoryContent() {
		// initialer Memory ist leer
		assertThat(rm.getMemoryContent())
				.hasSize(0);

		// leeren Memory setzen
		rm.setInitialMemoryContent(new int[]{});
		assertThat(rm.getMemoryContent())
				.hasSize(0);

		// Memory mit 3 Werten
		rm.setInitialMemoryContent(new int[]{1, 2, 3});
		assertThat(rm.getMemoryContent())
				.isEqualTo(new int[]{1, 2, 3});

		// Memory mit 0
		rm.setInitialMemoryContent(new int[]{1, 2, 3, 0});
		assertThat(rm.getMemoryContent())
				.isEqualTo(new int[]{1, 2, 3});

		pts += 3;
	}

	@Test
	public void execution1() {
		// vgl. Beispiel 1.3 der Vorlesung

		rm.setInitialMemoryContent(new int[]{0, 3, 4});
		boolean success = rm.execute(Arrays.asList(
				"LOAD #1",
				"STORE 3",
				"LOAD 2",
				"JZERO 12",
				"LOAD 3",
				"MULT 1",
				"STORE 3",
				"LOAD 2",
				"SUB #1",
				"STORE 2",
				"GOTO 4",
				"END"));

		assertThat(success)
				.isTrue();
		assertThat(rm.getMemoryContent())
				.isEqualTo(new int[]{0, 3, 0, 81});

		pts += 3;
	}

	@Test
	public void execution2() {
		rm.setInitialMemoryContent(new int[]{0, 3, 5});
		boolean success = rm.execute(Arrays.asList(
				"LOAD 1",
				"SUB 2",
				"JZERO 7",
				"LOAD 1",
				"STORE 3",
				"GOTO 9",
				"LOAD 2",
				"STORE 3",
				"END"));

		assertThat(success)
				.isTrue();
		assertThat(rm.getMemoryContent())
				.isEqualTo(new int[]{5, 3, 5, 5});
	}

	@Test
	public void execution3() {

	}

	@Test
	public void execution4() {

	}

	@AfterAll
	public static void printPoints() {
		System.out.printf("Erreichte Punkte: %d\n", pts);
	}
}
