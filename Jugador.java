/*
NOMBRE: Diego Valencia Figueroa
PROYECTO: El juego del gato
FECHA: 2 de octubre del 2025
FUNCIÓN: El juego consiste en dos jugadores. Donde cada partida si el jugador gana, se suman 2 puntos, si pierde se resta
1 punto y si quedan empatados no se suman ni restan ningun punto. El primer jugador en llegar a 10 puntos gana la ronda.
Se le pregunta si desea continuar, si desea continuar juega con otro jugador. Este proceso se repite hasta que el ganador
decida no seguir jugando. En ese caso el programa se termina y muestra el historial de todos los jugadores y sus datos.
 */
public class Jugador {
    private final String NOMBRE;
    private int tp, gan, perd, emp, adv, puntosTotales, puntosRonda;
    private char ficha;


    public Jugador(String nombre, char ficha)
    {
        this.NOMBRE = nombre;
        this.adv = 1;
        this.ficha = ficha;
    }

    public String getNombre(){return NOMBRE;}
    public char getFicha(){return ficha;}
    public int getPuntosRondas(){return puntosRonda;}
    public void setFicha(char ficha){this.ficha = ficha;}

    public void partidaGanada()
    {
        tp++;
        gan ++;
        puntosTotales +=5;
        puntosRonda +=5;
    }

    public void partidaPerdida()
    {
        tp++;
        perd ++;
        puntosTotales --;
        puntosRonda --;
    }

    public void partidaEmpatada()
    {
        tp++;
        emp++;
    }

    public void aumentarAdversarios(){adv++;}

    public void resetearPuntos(){puntosRonda = 0;}

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(NOMBRE).append("\n");
        sb.append("Ficha: ").append(ficha).append("\n");
        sb.append("Total de puntos: ").append(puntosTotales).append("\n");
        sb.append("Número de partidas: ").append(tp).append("\n");
        sb.append("Número de adversarios: ").append(adv).append("\n");
        sb.append("Número de partidas ganadas: ").append(gan).append("\n");
        sb.append("Número de partidas perdidas: ").append(perd).append("\n");
        sb.append("Número de partidas empatadas: ").append(emp).append("\n");

        return sb.toString();
    }
}
