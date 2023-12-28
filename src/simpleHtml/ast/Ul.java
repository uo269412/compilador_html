package simpleHtml.ast;

import java.util.List;

import simpleHtml.visitor.Visitor;

public class Ul implements Body_Data {

	private List<Dl> content;

	public Ul(List<Dl> palabras) {
		this.content = palabras;
	}

	public List<Dl> getContent() {
		return content;
	}

	public void setContent(List<Dl> content) {
		this.content = content;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
