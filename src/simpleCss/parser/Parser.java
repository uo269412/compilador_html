package simpleCss.parser;

import java.util.ArrayList;
import java.util.List;

import simpleCss.ast.AstCss;
import simpleCss.ast.Color;
import simpleCss.ast.Css_File;
import simpleCss.ast.Dl;
import simpleCss.ast.Etiqueta;
import simpleCss.ast.Font_Size;
import simpleCss.ast.Font_Style;
import simpleCss.ast.H1;
import simpleCss.ast.H2;
import simpleCss.ast.List_Style;
import simpleCss.ast.P;
import simpleCss.ast.Propiedad;
import simpleCss.ast.Text_Align;
import simpleCss.ast.Ul;

public class Parser {

	Lexicon lex;
	boolean errorSint = false;

	public Parser(Lexicon lex) {
		this.lex = lex;
	}

	public AstCss parse() {
		return parseCssFile();
	}

	public AstCss parseCssFile() {
		Token token = lex.getToken();
		List<Etiqueta> etiquetas = new ArrayList<Etiqueta>();
		while (!(token.token.equals(TokensId.EOF))) {
			etiquetas.add(parseEtiqueta(token));
			token = lex.getToken();
		}
		return new Css_File(etiquetas);
	}

	public Etiqueta parseEtiqueta(Token token) {
		if (token.token.equals(TokensId.H1)) {
			return parseH1();
		} else if (token.token.equals(TokensId.H2)) {
			return parseH2();
		} else if (token.token.equals(TokensId.P)) {
			return parseP();
		} else if (token.token.equals(TokensId.UL)) {
			return parseUl();
		} else if (token.token.equals(TokensId.DL)) {
			return parseDL();
		}
		errorSintactico("Error en el procesamiento de token " + token.getLexeme(), token.line);
		return null;

	}

	private Ul parseUl() {
		return new Ul(getList_Style());
	}

	private Dl parseDL() {
		return new Dl(getProperties());
	}

	private P parseP() {
		return new P(getProperties());
	}

	private H2 parseH2() {
		return new H2(getProperties());
	}

	private H1 parseH1() {
		return new H1(getProperties());
	}

	private List_Style getList_Style() {
		Token token = lex.getToken();
		while (!token.token.equals(TokensId.CLOSE_KEY)) {
			while (!token.token.equals(TokensId.DOT)) {
				if (token.token.equals(TokensId.LIST_STYLE)) {
					token = lex.getToken();
					matchToken(token, TokensId.TWO);
					token = lex.getToken();
					String valor = "";
					if (token.token.equals(TokensId.CIRCLE)) {
						valor = "circle";
					} else if (token.token.equals(TokensId.SQUARE)) {
						valor = "square";
					} else if (token.token.equals(TokensId.UPPER_ROMAN)) {
						valor = "upper-roman";
					} else if (token.token.equals(TokensId.LOWER_ALPHA)) {
						valor = "lower-alpha";
					} else {
						errorSintactico("Error en el procesamiento de token " + token.getLexeme(), token.line);
					}
					token = lex.getToken();
					matchToken(token, TokensId.DOT);
					token = lex.getToken();
					matchToken(token, TokensId.CLOSE_KEY);
					return new List_Style(valor);
				}
				token = lex.getToken();
			}
			errorSintactico("Error en el procesamiento de token " + token.getLexeme(), token.line);
			return null;
		}
		return null;
	}

	public Propiedad parsePropiedad(Token token) {
		while (!token.token.equals(TokensId.DOT)) {
			if (token.token.equals(TokensId.COLOR)) {
				return parseColor();
			} else if (token.token.equals(TokensId.FONT_SIZE)) {
				return parseFontSize();
			} else if (token.token.equals(TokensId.FONT_STYLE)) {
				return parseFontStyle();
			} else if (token.token.equals(TokensId.TEXT_ALIGN)) {
				return parseTextAlign();
			}
			token = lex.getToken();
		}
		errorSintactico("Error en el procesamiento de token " + token.getLexeme(), token.line);
		return null;
	}

	private Font_Size parseFontSize() {
		Token token = lex.getToken();
		matchToken(token, TokensId.TWO);
		token = lex.getToken();
		matchToken(token, TokensId.NUMBER);
		String numero = token.getLexeme();
		token = lex.getToken();
		matchToken(token, TokensId.DOT);
		return new Font_Size(numero);
	}

	private Font_Style parseFontStyle() {
		Token token = lex.getToken();
		matchToken(token, TokensId.TWO);
		token = lex.getToken();
		String valor = "";
		if (token.token.equals(TokensId.NORMAL)) {
			valor = "normal";
		} else if (token.token.equals(TokensId.ITALIC)) {
			valor = "italic";
		} else if (token.token.equals(TokensId.BOLD)) {
			valor = "bold";
		} else if (token.token.equals(TokensId.UNDERLINED)) {
			valor = "underlined";
		} else {
			errorSintactico("Error en el procesamiento de token " + token.getLexeme(), token.line);
		}
		token = lex.getToken();
		matchToken(token, TokensId.DOT);
		return new Font_Style(valor);
	}

	private Color parseColor() {
		Token token = lex.getToken();
		matchToken(token, TokensId.TWO);
		token = lex.getToken();
		String valor = "";
		if (token.token.equals(TokensId.BLUE)) {
			valor = "blue";
		} else if (token.token.equals(TokensId.BLACK)) {
			valor = "black";
		} else if (token.token.equals(TokensId.GREEN)) {
			valor = "green";
		} else if (token.token.equals(TokensId.RED)) {
			valor = "red";
		} else if (token.token.equals(TokensId.WHITE)) {
			valor = "white";
		} else if (token.token.equals(TokensId.YELLOW)) {
			valor = "yellow";
		} else {
			errorSintactico("Error en el procesamiento de token " + token.getLexeme(), token.line);
		}
		token = lex.getToken();
		matchToken(token, TokensId.DOT);
		return new Color(valor);
	}

	private Text_Align parseTextAlign() {
		Token token = lex.getToken();
		matchToken(token, TokensId.TWO);
		token = lex.getToken();
		String valor = "";
		if (token.token.equals(TokensId.CENTER)) {
			valor = "center";
		} else if (token.token.equals(TokensId.LEFT)) {
			valor = "left";
		} else if (token.token.equals(TokensId.RIGHT)) {
			valor = "right";
		} else {
			errorSintactico("Error en el procesamiento de token " + token.getLexeme(), token.line);
		}
		token = lex.getToken();
		matchToken(token, TokensId.DOT);
		return new Text_Align(valor);
	}

	public void matchToken(Token token, TokensId id) {
		if (!token.token.equals(id)) {
			errorSintactico("Error en el procesamiento de token " + token.getLexeme(), token.line);
		}
	}

	void errorSintactico(String e, int line) {
		errorSint = true;
		System.out.println("Error Sintáctico : " + e + " en la línea " + line);
	}

	private List<Propiedad> getProperties() {
		List<Propiedad> propiedades = new ArrayList<Propiedad>();
		Token token = lex.getToken();
		while (!token.token.equals(TokensId.CLOSE_KEY)) {
			propiedades.add(parsePropiedad(token));
			token = lex.getToken();
		}
		return propiedades;
	}

}
