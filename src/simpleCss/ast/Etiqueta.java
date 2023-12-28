package simpleCss.ast;

import simpleCss.visitor.Visitor;

public interface Etiqueta {
	Object accept(Visitor v, Object param);
}
