package ab1;

import java.util.List;

public interface RM {
	/**
	 * Setzt den Speicherinhalt. Alle Speicherzellen ab der Position content.length haben den Wert 0
	 */
	void setInitialMemoryContent(int[] content);

	/**
	 * Führt das gegebene Programm aus. Die Befehle des Programms sind wie in der VO definiert und sind case insensitive.
	 * Zusätzlich zur VO sind labels erlaubt (Labels enden mit einem ":"). In der Zeile des Labels kann, muss aber kein Befehl stehen.
	 * Erreicht die RM das Ende des Programms, so ist dies mit einem END-Befehl geleichzustzen.
	 *
	 * Bsp:
	 *
	 * LOAD #1
	 * STORE 1
	 * LABEL1:
	 * LOAD #2
	 * LABEL2: STORE 1
	 *
	 * @param programm
	 */
	boolean execute(List<String> programm);

	/**
	 * Liefert den Speicherinhalt, wobei keine endenden 0en zurück gegeben werden.
	 */
	int[] getMemoryContent();
}
