package render;

import render.visitor.Visitor;

public interface Page {

	Object accept(Visitor v, Object param);

}
