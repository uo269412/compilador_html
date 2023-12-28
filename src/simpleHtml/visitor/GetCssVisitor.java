package simpleHtml.visitor;

import simpleHtml.ast.Attribute;
import simpleHtml.ast.Body_Content;
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
import simpleHtml.ast.Title_Content;
import simpleHtml.ast.Ul;
import simpleHtml.ast.Underline;

public class GetCssVisitor implements Visitor {

	@Override
	public Object visit(Attribute attribute, Object param) {
		if (!attribute.getCadena().isEmpty()) {
			return attribute.getCadena().replaceAll("\"", "");
		}
		return null;
	}

	@Override
	public Object visit(Body_Content bc, Object param) {
		return null;
	}

	@Override
	public Object visit(Bold bold, Object param) {
		return null;
	}

	@Override
	public Object visit(H1 h1, Object param) {
		return null;
	}

	@Override
	public Object visit(H2 h2, Object param) {
		return null;
	}

	@Override
	public Object visit(Header_Content hc, Object param) {
		return hc.getLink_content().accept(this, param);
	}

	@Override
	public Object visit(Html_File hf, Object param) {
		return hf.getHeader().accept(this, param);
	}

	@Override
	public Object visit(Italic i, Object param) {
		return null;
	}

	@Override
	public Object visit(Link_Content lc, Object param) {
		for (Attribute a : lc.getAtributos()) {
			if (a.getAtributo().equals("href")) {
				return a.accept(this, param);
			}

		}
		return null;
	}

	@Override
	public Object visit(P p, Object param) {
		return null;
	}

	@Override
	public Object visit(Text text, Object param) {
		return null;
	}

	@Override
	public Object visit(Title_Content tc, Object param) {
		return null;
	}

	@Override
	public Object visit(Underline u, Object param) {
		return null;
	}

	@Override
	public Object visit(Div footer, Object param) {
		return null;
	}

	@Override
	public Object visit(Dl li, Object param) {
		return null;
	}

	@Override
	public Object visit(Ul ul, Object param) {
		return null;
	}

}
