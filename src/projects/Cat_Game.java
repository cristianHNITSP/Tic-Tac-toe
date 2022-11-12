package projects;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
class Cat_Game {
	//Se crea una variable para que las demas funciones / variables puedan acceder a static char para poder imprimir la tabla
	//me lele la cabeza :C 
	
	public static char[][] michi = new char[3][3];

	public static void imprimir_Posiciones(){
		int pos = 1;
		System.out.println("Selecciona un espacio vacio en el tablero: ");
		System.out.println("Las casillas marcadas con X u O ya están ocupadas");
		for (int i= 0; i<michi.length; i++){
			for (int j=0; j<michi[i].length; j++){
				if (michi[i][j] == 'X' || michi[i][j] == 'O')
					System.out.print( " " + michi[i][j]);
				else
					System.out.print( " " + pos);                    
				pos++;
			}
			System.out.println();
		}  
	}
	//se crea boolean con switch para validad si hay casillas vacias  
	public static boolean espacio_no_ocupado(int posicion){
		switch (posicion){
		case 1: return michi[0][0]!=' ';
		case 2: return michi[0][1]!=' ';
		case 3: return michi[0][2]!=' ';
		case 4: return michi[1][0]!=' ';
		case 5: return michi[1][1]!=' ';
		case 6: return michi[1][2]!=' ';
		case 7: return michi[2][0]!=' ';
		case 8: return michi[2][1]!=' ';
		case 9: return michi[2][2]!=' ';
		default: return false;
		}
	}
	//se cera para detectar las posiciones actual de los jugadores para determinar si el juego continua o no
	public static void registrar_jugada_actual(char caracter) throws IOException{
		boolean salir = false;
		String entrada;
		BufferedReader bufer = new BufferedReader(new InputStreamReader(System.in));    
		int posicion;
		do{
			imprimir_Posiciones();
			System.out.println("Proporcione el número de casilla donde desee marcar: ");
			entrada = bufer.readLine();
			posicion = Integer.parseInt(entrada);
			if ( espacio_no_ocupado(posicion)){
				switch (posicion){
				case 1: michi[0][0] = caracter;
				break;
				case 2: michi[0][1] = caracter;
				break;
				case 3: michi[0][2] = caracter;
				break;
				case 4: michi[1][0] = caracter;
				break;
				case 5: michi[1][1] = caracter;
				break;         
				case 6: michi[1][2] = caracter;
				break;
				case 7: michi[2][0] = caracter;
				break;
				case 8: michi[2][1] = caracter;
				break;
				case 9: michi[2][2] = caracter;
				break;
				}
				salir = true;
			}
			else
				System.out.println("Casilla no válida, proporcione una posición valida"); 
		} while (!salir);
	}
	//se crea la clase void (como siempre usamos) para poder imprimir esto debido a que no tenemos que recibir ningun dato
	public static void imprimir_michi(){
		System.out.println("El juego actualemente esta yendo: ");
		for (char[] michi1 : michi) {
			for (int j = 0; j<michi.length; j++) {
				System.out.print("    " + michi1[j]);
			}
			System.out.println();
		}

	}
	//se crea la funcion boolean para poder afirmar que el usuario gano por filas
	public static boolean gano_por_fila(char caracter){
		for (char[] michi1 : michi) {
			if (michi1[0] == caracter && michi1[1] == caracter && michi1[2] == caracter) {
				return true;
			}
		}
		return false;
	}
	//se crea la funcion boolean para poder afirmar que el usuario gano por columnas
	public static boolean gano_por_columna(char caracter){
		for (int i=0; i<michi.length; i++){
			if (michi[0][i] == caracter && michi[1][i]==caracter && michi[2][i] == caracter)
				return true;
		}
		return false;
	}  
	//se crea la funcion boolean para poder afirmar que fue empate
	public static boolean gano_por_diagonal(char caracter){    
		// Busca ganador en la columna de izquierda a derecha
		if (michi[0][0] == caracter && michi[1][1]==caracter && michi[2][2] == caracter)
			return true;
		if (michi[0][2] == caracter && michi[1][1]==caracter && michi[2][0] == caracter)
			return true; 

		return false;
	} 
	//se crea la funcion boolean para poder confirmar e imprimir si el usuario gano, perdio o fue empate
	public static boolean ganador(char caracter){
		if ( gano_por_fila(caracter) )
			return true;
		if ( gano_por_columna(caracter))
			return true;
		if ( gano_por_diagonal(caracter))
			return true;
		return false;    
	}
	// se crea la funcion booleaan para confirmar si hay espacio para poder imprimir y en dado caso que no se mostrar en pantalla que no hay
	public static boolean Hay_espacio(){
		for (char[] michi1 : michi) {
			for (int j = 0; j<michi.length; j++) {
				if (michi1[j] == '-') { 
					return true;
				}
			}
		}
		return false;
	}
	//Funcion para iniciar el juego
	public static void iniciar_juego(){
		for(int i=0;i<michi.length; i++)
			for(int j=0;j<michi.length; j++)
				michi[i][j] = '-';
	}
	/**
	 * 
	 */
	// Funcion para imprimir
	public static void main(String[] args) throws IOException {
		char jugadorActual = 'X';
		boolean terminar = false;
		iniciar_juego();
		do{
			registrar_jugada_actual(jugadorActual);
			imprimir_michi();
			if ( ganador(jugadorActual)){
				System.out.println("Felicidades jugador c: " + jugadorActual);
				System.out.println("Has ganado el juego c:");
				terminar = true;
			}
			else {
				if (!Hay_espacio()){
					// Ya no hay casillas disponibles, el juego se empató
					System.out.println("El juego termino en empate");
					terminar = true;
				}
				else
					if (jugadorActual == 'X')
						jugadorActual = 'O';
					else
						jugadorActual = 'X';
			}
		} while ( !terminar);
	}
}