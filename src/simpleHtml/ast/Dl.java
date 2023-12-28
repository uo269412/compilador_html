package simpleHtml.ast;

import java.util.List;

import simpleHtml.visitor.Visitor;

public class Dl implements Text_Content {

	private List<Text_Content> content;

	public Dl(List<Text_Content> palabras) {
		this.content = palabras;
	}

	public List<Text_Content> getContent() {
		return content;
	}

	public void setContent(List<Text_Content> content) {
		this.content = content;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
