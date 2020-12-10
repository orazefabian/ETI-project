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
		rm.setInitialMemoryContent(new int[]{0, 10, 8});
		boolean success = rm.execute(Arrays.asList(
				"LOAD 1",
				"SUB 2",
				"STORE 3",
				"LOAD 2",
				"SUB 1",
				"STORE 4",
				"LOAD 1",
				"ADD 2",
				"ADD 3",
				"ADD 4",
				"DIV #2",
				"STORE 3",
				"END"));

		assertThat(success)
				.isTrue();
		assertThat(rm.getMemoryContent())
				.isEqualTo(new int[]{10, 10, 8, 10});

		pts += 3;
	}

	@Test
	public void execution3() {
		rm.setInitialMemoryContent(new int[]{0, 10});
		boolean success = rm.execute(Arrays.asList(
				"LOAD 1",
				"STORE 3",
				"LOAD #3",
				"STORE 1",
				"schleife: LOAD *1",
				"SUB #1",
				"STORE 2",
				"LOAD 1",
				"ADD #1",
				"STORE 1",
				"LOAD 2",
				"STORE *1",
				"JZERO ende",
				"GOTO schleife",
				"ende: END"));

		assertThat(success)
				.isTrue();
		assertThat(rm.getMemoryContent())
				.isEqualTo(new int[]{0, 13, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1});

		pts += 3;
	}

	@Test
	public void execution4() {
		rm.setInitialMemoryContent(new int[]{});
		boolean success = rm.execute(Arrays.asList(
				"LOAD #2",
				"SUB #1",
				"SUB #1",
				"SUB #1",
				"SUB #1",
				"STORE 1",
				"END"));

		assertThat(success)
				.isTrue();
		assertThat(rm.getMemoryContent())
				.isEqualTo(new int[]{});

		pts += 3;
	}

	@AfterAll
	public static void printPoints() {
		System.out.printf("Erreichte Punkte: %d\n", pts);
	}
}
