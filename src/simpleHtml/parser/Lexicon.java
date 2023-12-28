package simpleHtml.parser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Lexicon {

	// Gestión de tokens
	public List<Token> tokens = new ArrayList<Token>();
	int i = 0; // Último token entregado en getToken()
	// Gestión de lectura del fichero
	FileReader filereader;
	boolean charBuffUsed = false;
	char charBuff;
	int line = 1; // indica la línea del fichero fuente

	HashSet<Character> charText = new HashSet<Character>();

	public Lexicon(FileReader f) {
		/*
		 * tokens.add(new Token(TokensId.HTML, "<html>")); tokens.add(new
		 * Token(TokensId.HTMLCLOSE, "</html>"));
		 */
		filereader = f;
		String lex;
		loadSet();
		try {
			char valor = (char) 0;
			while (valor != (char) -1) {
				valor = nextChar();
				switch ((char) valor) {
				case '<':
					valor = nextChar();
					if ((char) valor == '/') {
						valor = nextChar();
						switch ((char) valor) {
						case 'h':
							lex = getLexeme("</h", '>');
							if (lex.equals("</html>"))
								tokens.add(new Token(TokensId.HTML_C, lex, line));
							else if (lex.equals("</head>"))
								tokens.add(new Token(TokensId.HEAD_C, lex, line));
							else if (lex.equals("</h1>"))
								tokens.add(new Token(TokensId.H1_C, lex, line));
							else if (lex.equals("</h2>"))
								tokens.add(new Token(TokensId.H2_C, lex, line));
							else
								errorLexico(lex);
							break;
						case 't':
							lex = getLexeme("</t", '>');
							if (lex.equals("</title>"))
								tokens.add(new Token(TokensId.TITLE_C, lex, line));
							else
								errorLexico(lex);
							break;
						case 'b':
							lex = getLexeme("</b", '>');
							if (lex.equals("</b>"))
								tokens.add(new Token(TokensId.B_C, lex, line));
							else if (lex.equals("</body>"))
								tokens.add(new Token(TokensId.BODY_C, lex, line));
							else
								errorLexico(lex);
							break;
						case 'u':
							lex = getLexeme("</u", '>');
							if (lex.equals("</u>"))
								tokens.add(new Token(TokensId.U_C, lex, line));
							else if (lex.equals("</ul>"))
								tokens.add(new Token(TokensId.UL_C, lex, line));
							else
								errorLexico(lex);
							break;
						case 'i':
							lex = getLexeme("</i", '>');
							if (lex.equals("</i>"))
								tokens.add(new Token(TokensId.I_C, lex, line));
							else
								errorLexico(lex);
							break;
						case 'd':
							lex = getLexeme("</d", '>');
							if (lex.equals("</dl>"))
								tokens.add(new Token(TokensId.DL_C, lex, line));
							else if (lex.equals("</div>"))
								tokens.add(new Token(TokensId.DIV_C, lex, line));
							else
								errorLexico(lex);
							break;
						case 'p':
							lex = getLexeme("</p", '>');
							if (lex.equals("</p>"))
								tokens.add(new Token(TokensId.P_C, lex, line));
							else
								errorLexico(lex);
							break;
						default:
							errorLexico(getLexeme("<" + valor, '>'));
						}
					} else {
						switch ((char) valor) {
						case 'l':
							lex = getLexeme("<l", 'k');
							if (lex.equals("<link"))
								tokens.add(new Token(TokensId.LINK, lex, line));
							else
								errorLexico(lex);
							break;
						case 'd':
							lex = getLexeme("<d", '>');
							if (lex.equals("<dl>"))
								tokens.add(new Token(TokensId.DL, lex, line));
							else if (lex.equals("<div>"))
								tokens.add(new Token(TokensId.DIV, lex, line));
							else
								errorLexico(lex);
							break;
						case 'h':
							lex = getLexeme("<h", '>');
							if (lex.equals("<html>"))
								tokens.add(new Token(TokensId.HTML, lex, line));
							else if (lex.equals("<head>"))
								tokens.add(new Token(TokensId.HEAD, lex, line));
							else if (lex.equals("<h1>"))
								tokens.add(new Token(TokensId.H1, lex, line));
							else if (lex.equals("<h2>"))
								tokens.add(new Token(TokensId.H2, lex, line));
							else
								errorLexico(lex);
							break;
						case 't':
							lex = getLexeme("<t", '>');
							if (lex.equals("<title>"))
								tokens.add(new Token(TokensId.TITLE, lex, line));
							else
								errorLexico(lex);
							break;
						case 'b':
							lex = getLexeme("<b", '>');
							if (lex.equals("<b>"))
								tokens.add(new Token(TokensId.B, lex, line));
							else if (lex.equals("<body>"))
								tokens.add(new Token(TokensId.BODY, lex, line));
							else
								errorLexico(lex);
							break;
						case 'u':
							lex = getLexeme("<u", '>');
							if (lex.equals("<u>"))
								tokens.add(new Token(TokensId.U, lex, line));
							else if (lex.equals("<ul>"))
								tokens.add(new Token(TokensId.UL, lex, line));
							else
								errorLexico(lex);
							break;
						case 'i':
							lex = getLexeme("<i", '>');
							if (lex.equals("<i>"))
								tokens.add(new Token(TokensId.I, lex, line));
							else
								errorLexico(lex);
							break;
						case 'p':
							lex = getLexeme("<p", '>');
							if (lex.equals("<p>"))
								tokens.add(new Token(TokensId.P, lex, line));
							else
								errorLexico(lex);
							break;
						default:
							errorLexico(getLexeme("<" + valor, '>'));
						}
					}
					break;
				case '>':
					tokens.add(new Token(TokensId.CLOSE, new String(">"), line));
					break;
				// Resto de token de un solo caracter
				// ...
				case '\n':
					line++;
					break;
				case '\r':
					break;
				case ' ':
					break;
				case '\t':
					break;
				case '"':
					lex = getLexeme("\"", '"');
					tokens.add(new Token(TokensId.CADENA, lex, line));
					break;
				case '=':
					lex = String.valueOf(valor);
					tokens.add(new Token(TokensId.EQUAL, lex, line));
					break;
				case (char) -1:
					// Eliminar todos los espacios TokensId.WS
					break;
				default:
					// Texto
					lex = getLexemeTEXT(new String("" + (char) valor));
					tokens.add(new Token(TokensId.PALABRA, lex, line));
					break;
				}
				// System.out.print((char)valor);
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
	// ++
	// ++ Operaciones para el Sintactico
	// ++

	// Privadas

	// Dado el inicio de una cadena y el caracter final, devuelve el lexema
	// correspondiente
	String getLexeme(String lexStart, char finChar) throws IOException {
		String lexReturned = lexStart;
		char valor;
		do {
			valor = nextChar();
			lexReturned = lexReturned + ((char) valor);
		} while (((char) valor != finChar) && ((char) valor != -1));
		// returnChar(valor);
		return lexReturned;
	}

	// Devuelve un lexema de texto, el caracter final es un espacio
	String getLexemeTEXT(String lexStart) throws IOException {
		String lexReturned = lexStart;
		char valor = nextChar();
		while (charText.contains(((char) valor)) && ((char) valor != -1)) {
			lexReturned = lexReturned + ((char) valor);
			valor = nextChar();
		}
		returnChar(valor);
		return lexReturned;
	}

	// Carga el conjunto de caracteres adminidos pro el analizador léxico
	void loadSet() {
		String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789.,;:+-*/()[]!?";
		int i = 0;
		Character a = new Character('a');
		while (i < s.length()) {
			a = s.charAt(i);
			charText.add(a);
			i++;
		}
		// System.out.println(charText);
	}

	// Devuelde el siguiente caracter de fuente
	char nextChar() throws IOException {
		if (charBuffUsed) {
			charBuffUsed = false;
			return charBuff;
		} else {
			int valor = filereader.read();
			return ((char) valor);
		}
	}

	// Devuelve un caracger que se ha leído pero no se ha consumido al buffer
	void returnChar(char r) {
		charBuffUsed = true;
		charBuff = r;
	}

	// Emite error léxico
	void errorLexico(String e) {
		System.out.println("Error léxico en : " + e);
	}
}
