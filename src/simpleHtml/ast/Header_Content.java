package simpleHtml.ast;

import simpleHtml.visitor.Visitor;

public class Header_Content implements Ast {

	private Title_Content title_content;
	private Link_Content link_content;

	public Header_Content(Title_Content tc, Link_Content lc) {
		this.title_content = tc;
		this.link_content = lc;
	}

	public Title_Content getTitle_content() {
		return title_content;
	}

	public void setTitle_content(Title_Content title_content) {
		this.title_content = title_content;
	}

	public Link_Content getLink_content() {
		return link_content;
	}

	public void setBody_content(Link_Content link_content) {
		this.link_content = link_content;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
