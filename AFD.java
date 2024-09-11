import java.util.HashMap;
import java.util.Map;

public class AFD {
    private String estadoActual;
    private final Map<String, Map<Character, String>> transiciones;
    private final String[] estadosAceptacion = {"q4", "q5", "q7", "q8", "q10", "q11"};

    public AFD() {
        estadoActual = "q0"; // Estado inicial
        transiciones = new HashMap<>();

        // Definir transiciones
        transiciones.put("q0", Map.of('g', "q1"));
        transiciones.put("q1", Map.of('a', "q2"));
        transiciones.put("q2", Map.of('t', "q3"));
        transiciones.put("q3", Map.of('o', "q4", 'a', "q5", 'i', "q9"));
        transiciones.put("q4", Map.of('t', "q6"));
        transiciones.put("q5", new HashMap<>()); // Estado de aceptación
        transiciones.put("q6", Map.of('a', "q7", 'e', "q8"));
        transiciones.put("q7", new HashMap<>()); // Estado de aceptación
        transiciones.put("q8", new HashMap<>()); // Estado de aceptación
        transiciones.put("q9", Map.of('t', "q10"));
        transiciones.put("q10", Map.of('a', "q11"));
        transiciones.put("q11", new HashMap<>()); // Estado de aceptación
    }

    public boolean procesar(String cadena) {
        estadoActual = "q0"; // Reiniciar al estado inicial
        for (char c : cadena.toCharArray()) {
            if (transiciones.containsKey(estadoActual) && transiciones.get(estadoActual).containsKey(c)) {
                estadoActual = transiciones.get(estadoActual).get(c);
            } else {
                return false; // No hay transición válida, palabra no aceptada
            }
        }
        // Verificar si estamos en un estado de aceptación
        for (String estado : estadosAceptacion) {
            if (estado.equals(estadoActual)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        AFD automata = new AFD();
        String[] palabras = {"gato", "gata", "gatito", "gatita", "gatote", "gatota", "gaton", "gat"};
        
        for (String palabra : palabras) {
            if (automata.procesar(palabra)) {
                System.out.println("La palabra '" + palabra + "' es aceptada.");
            } else {
                System.out.println("La palabra '" + palabra + "' no es aceptada.");
            }
        }
    }
}
