package simpleHtml.parser;

import java.util.ArrayList;
import java.util.List;

import simpleHtml.ast.Ast;
import simpleHtml.ast.Attribute;
import simpleHtml.ast.Body_Content;
import simpleHtml.ast.Body_Data;
import simpleHtml.ast.Bold;
import simpleHtml.ast.Div;
import simpleHtml.ast.H1;
import simpleHtml.ast.H2;
import simpleHtml.ast.Header_Content;
import simpleHtml.ast.Html_File;
import simpleHtml.ast.Italic;
import simpleHtml.ast.Dl;
import simpleHtml.ast.Link_Content;
import simpleHtml.ast.P;
import simpleHtml.ast.Text;
import simpleHtml.ast.Text_Content;
import simpleHtml.ast.Title_Content;
import simpleHtml.ast.Ul;
import simpleHtml.ast.Underline;

public class Parser {

	Lexicon lex;
	boolean errorSint = false;

	public Parser(Lexicon lex) {
		this.lex = lex;
	}

	public Ast parse() {
		return parseHtmlFile();
	}

	public void matchToken(Token token, TokensId id) {
		if (!token.token.equals(id)) {
			errorSintactico("Error en el procesamiento de token " + token.getLexeme(), token.line);
		}
	}

	private Html_File parseHtmlFile() {
		Token token = lex.getToken();
		matchToken(token, TokensId.HTML);
		Header_Content hc = parseHeaderContent();
		Body_Content bc = parseBodyContent();
		token = lex.getToken();
		matchToken(token, TokensId.HTML_C);
		return new Html_File(hc, bc);
	}

	private Body_Data parseDiv() {
		List<Body_Data> bd = parseBodyData(TokensId.DIV_C);
		return new Div(bd);
	}

	private Header_Content parseHeaderContent() {
		Token token = lex.getToken();
		matchToken(token, TokensId.HEAD);
		Title_Content tc = parseTitleContent();
		Link_Content lc = parseLinkContent();
		token = lex.getToken();
		matchToken(token, TokensId.HEAD_C);
		return new Header_Content(tc, lc);
	}

	private Title_Content parseTitleContent() {
		Token token = lex.getToken();
		matchToken(token, TokensId.TITLE);
		String list = String.join(" ", parseText(TokensId.TITLE_C));
		return new Title_Content(list);
	}

	private Link_Content parseLinkContent() {
		Token token = lex.getToken();
		matchToken(token, TokensId.LINK);
		List<Attribute> attributes = new ArrayList<Attribute>();
		token = lex.getToken();
		while (!token.token.equals(TokensId.CLOSE)) {
			attributes.add(parseAttribute(token));
			token = lex.getToken();
		}
		return new Link_Content(attributes);

	}

	private Attribute parseAttribute(Token token) {
		String palabra = token.getLexeme();
		matchToken(token, TokensId.PALABRA);
		token = lex.getToken();
		matchToken(token, TokensId.EQUAL);
		token = lex.getToken();
		String cadena = token.getLexeme();
		matchToken(token, TokensId.CADENA);
		return new Attribute(palabra, cadena);
	}

	private Body_Content parseBodyContent() {
		Token token = lex.getToken();
		matchToken(token, TokensId.BODY);
		List<Body_Data> bd = parseBodyData(TokensId.BODY_C);
		return new Body_Content(bd);
	}

	private List<Body_Data> parseBodyData(TokensId tokenId) {
		Token token = lex.getToken();
		List<Body_Data> palabras = new ArrayList<Body_Data>();
		while (!token.token.equals(tokenId) && !(token.token.equals(TokensId.EOF))) {
			if (token.token.equals(TokensId.H1)) {
				palabras.add(parseH1());
			} else if (token.token.equals(TokensId.H2)) {
				palabras.add(parseH2());
			} else if (token.token.equals(TokensId.P)) {
				palabras.add(parseP());
			} else if (token.token.equals(TokensId.UL)) {
				palabras.add(parseUl());
			} else if (token.token.equals(TokensId.DIV) && !tokenId.equals(TokensId.DIV_C)) {
				palabras.add(parseDiv());
			}
			token = lex.getToken();
		}
		return palabras;
	}

	private Body_Data parseUl() {
		Token token = lex.getToken();
		List<Dl> palabras = new ArrayList<Dl>();
		while (!token.token.equals(TokensId.UL_C)) {
			if (token.token.equals(TokensId.DL)) {
				palabras.add(parseDl());
			}
			token = lex.getToken();
		}
		return new Ul(palabras);
	}

	private Dl parseDl() {
		List<Text_Content> palabras = parseTextContent(TokensId.DL_C);
		return new Dl(palabras);
	}

	private Text_Content parseBold() {
		List<String> tc = parseText(TokensId.B_C);
		return new Bold(String.join(" ", tc));
	}

	private Text_Content parseItalic() {
		List<String> tc = parseText(TokensId.I_C);
		return new Italic(String.join(" ", tc));
	}

	private Text_Content parseUnderline() {
		List<String> tc = parseText(TokensId.U_C);
		return new Underline(String.join(" ", tc));
	}

	private List<Text_Content> parseTextContent(TokensId id) {
		Token token = lex.getToken();
		List<Text_Content> palabras = new ArrayList<Text_Content>();
		while (!token.token.equals(id) && !(token.token.equals(TokensId.EOF))) {
			if (token.token.equals(TokensId.B)) {
				palabras.add(parseBold());
			} else if (token.token.equals(TokensId.I)) {
				palabras.add(parseItalic());
			} else if (token.token.equals(TokensId.U)) {
				palabras.add(parseUnderline());
			} else if (token.token.equals(TokensId.PALABRA)) {
				palabras.add(new Text(token.getLexeme()));
			}
			token = lex.getToken();
		}
		return palabras;
	}

	private List<String> parseText(TokensId id) {
		Token token = lex.getToken();
		List<String> palabras = new ArrayList<String>();
		while (!token.token.equals(id) && !(token.token.equals(TokensId.EOF))) {
			palabras.add(token.getLexeme());
			token = lex.getToken();
		}
		return palabras;
	}

	private H1 parseH1() {
		List<Text_Content> palabras = parseTextContent(TokensId.H1_C);
		return new H1(palabras);
	}

	private H2 parseH2() {
		List<Text_Content> palabras = parseTextContent(TokensId.H2_C);
		return new H2(palabras);
	}

	private P parseP() {
		List<Text_Content> palabras = parseTextContent(TokensId.P_C);
		return new P(palabras);
	}

	// Gestión de Errores Sintáctico
	void errorSintactico(String e, int line) {
		errorSint = true;
		System.out.println("Error Sintáctico : " + e + " en la línea " + line);
	}
}
