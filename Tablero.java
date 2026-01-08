
/*
NOMBRE: Diego Valencia Figueroa
PROYECTO: El juego del gato
FECHA: 2 de octubre del 2025
FUNCIÓN: El juego consiste en dos jugadores. Donde cada partida si el jugador gana, se suman 2 puntos, si pierde se resta
1 punto y si quedan empatados no se suman ni restan ningun punto. El primer jugador en llegar a 10 puntos gana la ronda.
Se le pregunta si desea continuar, si desea continuar juega con otro jugador. Este proceso se repite hasta que el ganador
decida no seguir jugando. En ese caso el programa se termina y muestra el historial de todos los jugadores y sus datos.
 */
public class Tablero {
    private char[][] tablero = new char[3][3];
    private final String[][] FICHAS = {
        { "     ", "     ", "     " }, // vacío
        { "X   X", "  X  ", "X   X" }, // X
        { " OOO ", "O   O", " OOO " }  // O
        };

    public void nuevoTablero(){tablero = new char[3][3];}

    public boolean espacioVacio(int pos)
    {
        int f = (pos-1)/3;
        int c = (pos-1)%3;
        return (tablero[f][c] != 'X' && tablero[f][c] != 'O');
    }

    public boolean tableroLleno()
    {
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(tablero[i][j] !='O' && tablero[i][j] != 'X')
                {return false;}
            }
        }
        return true;
    }

    public boolean ganador(char ficha)
    {
        //Horizontales y verticales
        for (int i = 0; i < 3; i++) {
            if ((tablero[i][0] == ficha && tablero[i][1] == ficha && tablero[i][2] == ficha)
            ||(tablero[0][i] == ficha && tablero[1][i] == ficha && tablero[2][i] == ficha)){return true;}
        }
        //Diagonales
        return(tablero[0][0] == ficha && tablero[1][1] == ficha && tablero[2][2] == ficha)
                ||(tablero[0][2] == ficha && tablero[1][1] == ficha && tablero[2][0] == ficha);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        for (int f = 0; f < 3; f++) {
        //Cada "línea" de las figuras (3 líneas por celda)
            for (int linea = 0; linea < 3; linea++) {
                for (int c = 0; c < 3; c++) {
                    String[] fig;
                    fig = switch (tablero[f][c]) {
                        case 'X' -> FICHAS[1];
                        case 'O' -> FICHAS[2];
                        default -> FICHAS[0];
                    };

                    sb.append(fig[linea]);
                    if (c < 2) sb.append(" | "); //Separador vertical
                }
                sb.append("\n");
            }
            if (f < 2)
            {sb.append("-----+-------+-----\n");} //Separador horizontal
        }
        return sb.toString();
    }

    public void colocarFicha(int pos, char ficha) {
        int f = (pos - 1) / 3;
        int c = (pos - 1) % 3;
        tablero[f][c] = ficha;
    }
}
