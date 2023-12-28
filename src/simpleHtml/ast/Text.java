package simpleHtml.ast;

import simpleHtml.visitor.Visitor;

public class Text implements Text_Content {

	private String text;

	public Text(String lexeme) {
		this.text = lexeme;
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
