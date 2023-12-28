package simpleHtml.visitor;

import simpleHtml.ast.Attribute;
import simpleHtml.ast.Body_Content;
import simpleHtml.ast.Bold;
import simpleHtml.ast.Dl;
import simpleHtml.ast.Div;
import simpleHtml.ast.H1;
import simpleHtml.ast.H2;
import simpleHtml.ast.Header_Content;
import simpleHtml.ast.Html_File;
import simpleHtml.ast.Italic;
import simpleHtml.ast.Link_Content;
import simpleHtml.ast.P;
import simpleHtml.ast.Text;
import simpleHtml.ast.Title_Content;
import simpleHtml.ast.Ul;
import simpleHtml.ast.Underline;

public interface Visitor {
	Object visit(Attribute attribute, Object param);

	Object visit(Body_Content bc, Object param);

	Object visit(Bold bold, Object param);

	Object visit(H1 h1, Object param);

	Object visit(H2 h2, Object param);

	Object visit(Header_Content hc, Object param);

	Object visit(Html_File hf, Object param);

	Object visit(Italic i, Object param);

	Object visit(Link_Content lc, Object param);

	Object visit(P p, Object param);

	Object visit(Text text, Object param);

	Object visit(Title_Content tc, Object param);

	Object visit(Underline u, Object param);

	Object visit(Div footer, Object param);

	Object visit(Dl li, Object param);

	Object visit(Ul ul, Object param);

}
