/*
NOMBRE: Diego Valencia Figueroa
PROYECTO: El juego del gato
FECHA: 2 de octubre del 2025
FUNCIÓN: El juego consiste en dos jugadores. Donde cada partida si el jugador gana, se suman 2 puntos, si pierde se resta
1 punto y si quedan empatados no se suman ni restan ningun punto. El primer jugador en llegar a 10 puntos gana la ronda.
Se le pregunta si desea continuar, si desea continuar juega con otro jugador. Este proceso se repite hasta que el ganador
decida no seguir jugando. En ese caso el programa se termina y muestra el historial de todos los jugadores y sus datos.
 */
import java.util.ArrayList;

public class Juego
{
    private ArrayList<Jugador> historial = new ArrayList<>();//Historial de jugadores
    private Jugador j1, j2;
    private Tablero tablero;

    public void inicio(){
        //Jugador 1
        String nj1 = ingresarNombre("Ingrese nombre del jugador 1: ");
        char fj1 = elegirFicha(nj1+" elige tu ficha (X/O): ");
        //Jugador 2
        String nj2 = ingresarNombre("Ingrese nombre del jugador 2: ");
        char fj2 = (fj1 == 'O') ? 'X':'O';//Se le asigna la otra ficha.

        j1 = new Jugador(nj1, fj1);
        j2 = new Jugador(nj2, fj2);
        //Se agregan los jugadores al historial
        historial.add(j1);
        historial.add(j2);

        tablero = new Tablero();//Tablero en blanco
        boolean continuar = true;

        while (continuar) {//Ciclo para continuar el juego.
            jugarPartida(tablero, j1, j2);

            //Cuando un jugador llegue a los 10 puntos.
            if (j1.getPuntosRondas() >= 10)
                {continuar = ganarPartida(j1, true);}
            else if (j2.getPuntosRondas() >= 10)//Si el ganador quiere seguir jugando?
                {continuar = ganarPartida(j2, false);}

        }

        System.out.println("\n=== Estadísticas Finales ===");
        for (Jugador j : historial)
            {System.out.println(j);}
    }

    private Jugador nuevoJugador(char ficha){
        System.out.print("Ingrese nombre del nuevo jugador (" + ficha + "): ");
        return new Jugador(Keyboard.readString(), ficha);
    }

    private String ingresarNombre(String mensaje){
        String nombre;
        System.out.print(mensaje);
        nombre = Keyboard.readString();
        return nombre;
    }

    private char elegirFicha(String mensaje){
        while (true){
            System.out.print(mensaje);
            char entrada = Character.toUpperCase(Keyboard.readChar());
            if(entrada == 'X' || entrada == 'O')
                {return entrada;}
            System.out.println("Entrada inválida. Solo X o O.");
        }
    }

    private int elegirPosicion(Tablero tablero, Jugador jugador){
        int pos = 0;
        boolean valido = false;

        while (!valido){
            System.out.print(jugador.getNombre() + " (" + jugador.getFicha() + ") elige posición (1-9): ");
            try{
                pos = Keyboard.readInt();
                if(pos < 1 || pos > 9)
                    {System.out.println("Posición fuera de rango.");}
                else if(!tablero.espacioVacio(pos))
                    {System.out.println("Posición ya ocupada.");}
                else
                    {valido = true;}

            }catch(NumberFormatException e)
                {System.out.println("Entrada inválida. Escribe un número (1-9).");}
        }
        return pos;
    }

    private boolean pedirSN(String mensaje){
        while (true){
            System.out.print(mensaje + " (s/n): ");
            char entrada = Character.toLowerCase(Keyboard.readChar());
            switch (entrada){
                case 's' -> {
                    return true;
                }
                case 'n' -> {
                    return false;
                }
                default -> System.out.println("Entrada inválida. Escribe 's' o 'n'.");
            }
        }
    }

    private void jugarPartida(Tablero tablero, Jugador j1, Jugador j2){
        tablero.nuevoTablero();//Tablero en blanco.
        boolean turnoJ1 = true;
        Jugador actual, rival;
        boolean jugar = true;
        int movimientos = 0;

        while (jugar){
            System.out.println(tablero);
            actual = turnoJ1 ? j1 : j2;//Jugador actual
            rival = turnoJ1 ? j2 : j1;//Rival

            int pos = elegirPosicion(tablero, actual);
            tablero.colocarFicha(pos, actual.getFicha());
            movimientos++;

            //Solo revisar ganador a partir del quinto movimiento
            if (movimientos >= 5 && tablero.ganador(actual.getFicha())) {
                System.out.println(tablero);
                System.out.println(actual.getNombre() + " ganó la partida!");
                actual.partidaGanada();
                rival.partidaPerdida();
                jugar = false;//Termina la partida
            } 
            //Verificar empate (si se llenó el tablero y no hay ganador)
            else if (tablero.tableroLleno()) {
                System.out.println(tablero);
                System.out.println("Empate!");
                j1.partidaEmpatada();
                j2.partidaEmpatada();
                jugar = false;//Termina la partida
            }

            turnoJ1 = !turnoJ1;//Cambiar turno
        }
    }

    private boolean ganarPartida(Jugador ganador, boolean ganadorJ1){
        System.out.println(ganador.getNombre() + " (" + ganador.getFicha() + ") llegó a 10 puntos.");
        ganador.resetearPuntos();//Resetea los puntos a 0 para la nueva ronda.

        boolean continuar = pedirSN(ganador.getNombre() + " ¿Desea jugar otra partida?");
        if(!continuar){return false;} // ← AHORA SÍ se sale

        if(pedirSN(ganador.getNombre() + ", ¿quieres cambiar tu ficha?")){// Cambiar ficha?
            ganador.setFicha(elegirFicha(ganador.getNombre() + " elige tu ficha (X/O): "));
            System.out.println(ganador.getNombre() + " ahora jugará con '" + ganador.getFicha() + "'.");
        }

        Jugador nuevo = nuevoJugador((ganador.getFicha() == 'X') ? 'O' : 'X');//Se crea un nuevo jugador

        historial.add(nuevo);//Se agrega el nuevo jugador al historial
        ganador.aumentarAdversarios();//Aumenta el número de adversarios del ganador

        if(ganadorJ1)
            {j2 = nuevo;}   // ← AQUÍ sí se cambia el jugador real
        else
            {j1 = nuevo;}

        return true;
    }
}
