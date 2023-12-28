package simpleHtml.visitor;

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

public class PrintVisitor implements Visitor {

	@Override
	public Object visit(Attribute attribute, Object param) {
		System.out.println("			------Atributo------");
		System.out.println("					" + attribute.getAtributo());
		System.out.println("					" + attribute.getCadena());
		System.out.println("			------/Atributo------");
		return null;
	}

	@Override
	public Object visit(Body_Content bc, Object param) {
		System.out.println("	------Body------");
		for (Body_Data bd : bc.getContenido()) {
			bd.accept(this, param);
		}
		System.out.println("	------/Body------");
		return null;
	}

	@Override
	public Object visit(Bold bold, Object param) {
		System.out.println("			------Bold------");
		System.out.println("				" + bold.getText().toString());
		System.out.println("			------/Bold------");
		return null;
	}

	@Override
	public Object visit(H1 h1, Object param) {
		System.out.println("		------H1------");
		for (Text_Content tc : h1.getContent()) {
			tc.accept(this, param);
		}
		System.out.println("		------/H1------");
		return null;
	}

	@Override
	public Object visit(H2 h2, Object param) {
		System.out.println("		------H2------");
		for (Text_Content tc : h2.getContent()) {
			tc.accept(this, param);
		}
		System.out.println("		------/H2------");
		return null;
	}

	@Override
	public Object visit(Header_Content hc, Object param) {
		System.out.println("	------Head------");
		hc.getLink_content().accept(this, param);
		hc.getTitle_content().accept(this, param);
		System.out.println("	------/Head------");
		return null;
	}

	@Override
	public Object visit(Html_File hf, Object param) {
		System.out.println("------HTML------");
		hf.getHeader().accept(this, param);
		hf.getBody().accept(this, param);
		System.out.println("------/HTML------");
		return null;
	}

	@Override
	public Object visit(Italic i, Object param) {
		System.out.println("			------Italic------");
		System.out.println("				" + i.getText().toString());
		System.out.println("			------/Italic------");
		return null;
	}

	@Override
	public Object visit(Link_Content lc, Object param) {
		System.out.println("		------Link------");
		for (Attribute a : lc.getAtributos()) {
			a.accept(this, param);
		}
		System.out.println("		------/Link------");
		return null;
	}

	@Override
	public Object visit(P p, Object param) {
		System.out.println("		------P------");
		for (Text_Content tc : p.getContent()) {
			tc.accept(this, param);
		}
		System.out.println("		------/P------");
		return null;
	}

	@Override
	public Object visit(Text text, Object param) {
		System.out.println("			------Text------");
		System.out.println("				" + text.getText());
		System.out.println("			------/Text------");
		return null;
	}

	@Override
	public Object visit(Title_Content tc, Object param) {
		System.out.println("		------Title------");
		System.out.println("			" + tc.getText().toString());
		System.out.println("		------/Title------");
		return null;
	}

	@Override
	public Object visit(Underline u, Object param) {
		System.out.println("			------Underline------");
		System.out.println("				" + u.getText().toString());
		System.out.println("			------/Underline------");
		return null;
	}

	@Override
	public Object visit(Div footer, Object param) {
		System.out.println("	------Div------");
		for (Body_Data bd : footer.getContenido()) {
			bd.accept(this, param);
		}
		System.out.println("	------/Div------");
		return null;
	}

	@Override
	public Object visit(Dl li, Object param) {
		System.out.println("			------List item------");
		for (Text_Content tc : li.getContent()) {
			tc.accept(this, param);
		}
		System.out.println("			------/List item------");
		return null;
	}

	@Override
	public Object visit(Ul ul, Object param) {
		System.out.println("		------Unordered list------");
		for (Dl li : ul.getContent()) {
			li.accept(this, param);
		}
		System.out.println("		------/Unordered list------");
		return null;
	}

}
