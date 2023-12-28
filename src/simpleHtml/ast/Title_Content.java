package simpleHtml.ast;

import simpleHtml.visitor.Visitor;

public class Title_Content implements Ast {

	private String text;

	public Title_Content(String list) {
		this.text = list;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
