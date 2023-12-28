package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import render.Formatted_Page;
import render.visitor.PageVisitor;
import simpleCss.ast.AstCss;
import simpleHtml.ast.Ast;
import simpleHtml.parser.Parser;
import simpleHtml.visitor.GetCssVisitor;
import simpleHtml.visitor.RenderVisitor;

public class Main {

	/**
	 * Se utilizan los archivos EX5.html y Default2.css porque ambos contienen las
	 * etiquetas añadidas en las extensiones. La ruta a los ficheros se deberá
	 * cambiar
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {

		String URL = "C:\\Users\\uo269412\\eclipse-workspace\\navegador_uo269412\\";
		// String URL =
		// "C:\\Users\\javie\\OneDrive\\Escritorio\\workspace_dlp2\\navegador_uo269412\\";

		// HTML

		File archivo = new File(URL + "EX5.html");
		FileReader fr = new FileReader(archivo);
		simpleHtml.parser.Lexicon lexicon = new simpleHtml.parser.Lexicon(fr);
		Parser parser = new Parser(lexicon);
		simpleHtml.visitor.PrintVisitor pv = new simpleHtml.visitor.PrintVisitor();
		System.out.println("HTML VISITOR");
		simpleHtml.ast.Ast astHtml = (Ast) parser.parse();
		astHtml.accept(pv, null);

		// COGER URL CSS

		FileReader frURL = new FileReader(archivo);
		simpleHtml.parser.Lexicon lexiconURL = new simpleHtml.parser.Lexicon(frURL);
		Parser parserURL = new Parser(lexiconURL);
		GetCssVisitor cssV = new GetCssVisitor();
		String custom_css_url = (String) parserURL.parse().accept(cssV, null);

		// DEFAULT CSS

		File archivo2 = new File(URL + "Default.CSS");
		FileReader fr2 = new FileReader(archivo2);
		simpleCss.parser.Lexicon lexicon2 = new simpleCss.parser.Lexicon(fr2);
		simpleCss.parser.Parser parser2 = new simpleCss.parser.Parser(lexicon2);
		simpleCss.visitor.PrintVisitor pv2 = new simpleCss.visitor.PrintVisitor();
		System.out.println("DEFAULT CSS");
		simpleCss.ast.AstCss astDefaultCss = (AstCss) parser2.parse();
		astDefaultCss.accept(pv2, null);

		// CUSTOM CSS

		File archivo3 = new File(URL + custom_css_url);
		FileReader fr3 = new FileReader(archivo3);
		simpleCss.parser.Lexicon lexicon3 = new simpleCss.parser.Lexicon(fr3);
		simpleCss.parser.Parser parser3 = new simpleCss.parser.Parser(lexicon3);
		simpleCss.visitor.PrintVisitor pv3 = new simpleCss.visitor.PrintVisitor();
		System.out.println("CUSTOM CSS");
		simpleCss.ast.AstCss astCustomCss = (AstCss) parser3.parse();
		astCustomCss.accept(pv3, null);

		// CREATE PAGE

		RenderVisitor rv = new RenderVisitor(astHtml, astCustomCss, astDefaultCss);
		Formatted_Page fp = rv.render();
		System.out.println("CREATE PAGE");
		PageVisitor pgV = new PageVisitor();
		fp.accept(pgV, null);

	}
}
