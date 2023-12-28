package simpleCss.visitor;

import simpleCss.ast.*;

public interface Visitor {
	Object visit(Color c, Object param);

	Object visit(Css_File c, Object param);

	Object visit(Font_Size f, Object param);

	Object visit(Font_Style f, Object param);

	Object visit(H1 h, Object param);

	Object visit(H2 h, Object param);

	Object visit(P p, Object param);

	Object visit(Text_Align t, Object param);

	Object visit(Ul ul, Object param);

	Object visit(Dl dl, Object param);

	Object visit(List_Style list_Style, Object param);
}
