package simpleCss.parser;

import java.io.FileReader;
import java.util.*;
import java.io.*;

public class Lexicon {

	// Gestión de tokens
	List<Token> tokens = new ArrayList<Token>();
	int i = 0; // Último token entregado en getToken()
	// Gestión de lectura del fichero
	FileReader filereader;
	boolean charBuffUsed = false;
	char charBuff;
	int line = 1; // indica la línea del fichero fuente

	HashSet<Character> charText = new HashSet<Character>();

	public Lexicon(FileReader f) {
		filereader = f;
		// String lex;
		try {
			char valor = (char) 0;
			while (valor != (char) -1) {
				valor = nextChar();
				switch (valor) {
				case '{':
					tokens.add(new Token(TokensId.OPEN_KEY, "{", line));
					break;
				case '}':
					tokens.add(new Token(TokensId.CLOSE_KEY, "}", line));
					break;
				case ':':
					tokens.add(new Token(TokensId.TWO, ":", line));
					break;
				case ';':
					tokens.add(new Token(TokensId.DOT, ";", line));
					break;
				// Aquí deben ir todos los tokens de 1 caracter
				// ...
				case '\n':
					line++;
				case '\r':
				case ' ':
				case '\t':
				case (char) -1:
					// Eliminar todos los espacios TokensId.WS
					break;
				default:
					if (Character.isDigit(valor)) {
						String size = getSize("" + valor);
						if (size != null)
							tokens.add(new Token(TokensId.NUMBER, size, line));
					} else if (Character.isAlphabetic(valor)) {
						String ident = getText("" + valor);
						switch (ident) {
						case "px":
							tokens.add(new Token(TokensId.PX, ident, line));
							break;
						case "black":
							tokens.add(new Token(TokensId.BLACK, ident, line));
							break;
						case "blue":
							tokens.add(new Token(TokensId.BLUE, ident, line));
							break;
						case "green":
							tokens.add(new Token(TokensId.GREEN, ident, line));
							break;
						case "red":
							tokens.add(new Token(TokensId.RED, ident, line));
							break;
						case "white":
							tokens.add(new Token(TokensId.WHITE, ident, line));
							break;
						case "yellow":
							tokens.add(new Token(TokensId.YELLOW, ident, line));
							break;
						case "center":
							tokens.add(new Token(TokensId.CENTER, ident, line));
							break;
						case "left":
							tokens.add(new Token(TokensId.LEFT, ident, line));
							break;
						case "normal":
							tokens.add(new Token(TokensId.NORMAL, ident, line));
							break;
						case "italic":
							tokens.add(new Token(TokensId.ITALIC, ident, line));
							break;
						case "bold":
							tokens.add(new Token(TokensId.BOLD, ident, line));
							break;
						case "underlined":
							tokens.add(new Token(TokensId.UNDERLINED, ident, line));
							break;
						case "h1":
							tokens.add(new Token(TokensId.H1, ident, line));
							break;
						case "h2":
							tokens.add(new Token(TokensId.H2, ident, line));
							break;
						case "p":
							tokens.add(new Token(TokensId.P, ident, line));
							break;
						case "color":
							tokens.add(new Token(TokensId.COLOR, ident, line));
							break;
						case "font-size":
							tokens.add(new Token(TokensId.FONT_SIZE, ident, line));
							break;
						case "text-align":
							tokens.add(new Token(TokensId.TEXT_ALIGN, ident, line));
							break;
						case "font-style":
							tokens.add(new Token(TokensId.FONT_STYLE, ident, line));
							break;
						case "list-style-type":
							tokens.add(new Token(TokensId.LIST_STYLE, ident, line));
							break;
						case "ul":
							tokens.add(new Token(TokensId.UL, ident, line));
							break;
						case "dl":
							tokens.add(new Token(TokensId.DL, ident, line));
							break;
						case "circle":
							tokens.add(new Token(TokensId.CIRCLE, ident, line));
							break;
						case "square":
							tokens.add(new Token(TokensId.SQUARE, ident, line));
							break;
						case "upper-roman":
							tokens.add(new Token(TokensId.UPPER_ROMAN, ident, line));
							break;
						case "lower-alpha":
							tokens.add(new Token(TokensId.LOWER_ALPHA, ident, line));
							break;
						// Aquí van todos los tokens de más de 1 caracter
						// ...
						default:

						}
					} else
						errorLexico("Caracter " + valor + " encontrado en línea " + line);
				}
			}
			filereader.close();
		} catch (IOException e) {
			System.out.println("Error E/S: " + e);
		}

	}

	// ++
	// ++ Operaciones para el Sintactico
	// ++
	// Devolver el último token
	public void returnLastToken() {
		i--;
	}

	// Get Token
	public Token getToken() {
		if (i < tokens.size()) {
			return tokens.get(i++);
		}
		return new Token(TokensId.EOF, "EOF", line);
	}

	// Recupera un token de tipo size incluyendo el px final
	String getSize(String lexStart) throws IOException {
		String lexReturned = lexStart;
		char valor;
		do {
			valor = nextChar();
			lexReturned = lexReturned + (valor);
		} while ((valor != 'p') && (valor != -1));
		// returnChar(valor);
		if (valor == 'p') {
			// lexReturned = lexReturned+(valor);
			valor = nextChar();
			if (valor == 'x') {
				lexReturned = lexReturned + (valor);
			} else {
				errorLexico("Encontrado " + lexReturned + ". Se esperada un token SIZE.");
				return null;
			}
		}
		return lexReturned;
	}

	// Recupera un token de más de 1 caracter
	String getText(String lexStart) throws IOException {
		String lexReturned = lexStart;
		char valor = nextChar();
		while (Character.isDigit(valor) || Character.isAlphabetic(valor) || (valor == '-')) {
			lexReturned = lexReturned + (valor);
			valor = nextChar();
		}
		returnChar(valor);
		return lexReturned;
	}

	// Borra un comentario que está encerrado en /* y */
	boolean deleteComment() throws IOException {
		boolean r = false;
		char c = nextChar();
		if (c != '*')
			return false;
		do {
			c = nextChar();
			if (c == (char) -1) // Inesperado fin de fichero
				return false;
			if (c == '\n') // debe seguir contando líneas
				line++;
			if (c == '*') {
				c = nextChar();
				if (c == '/') {
					r = true;
				}
			}
		} while (!r);

		return r;
	}

	// Devuelve el siguiente caracter del fichero
	char nextChar() throws IOException {
		if (charBuffUsed) {
			charBuffUsed = false;
			return charBuff;
		} else {
			int valor = filereader.read();
			return ((char) valor);
		}
	}

	// Devuelve un caracter al buffer si fue leído y no consumido
	void returnChar(char r) {
		charBuffUsed = true;
		charBuff = r;
	}

	// Error léxico
	void errorLexico(String e) {
		System.out.println("Error léxico en : " + e);
	}
}
