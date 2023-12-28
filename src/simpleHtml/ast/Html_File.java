package simpleHtml.ast;

import simpleHtml.visitor.Visitor;

public class Html_File implements Ast {

	private Header_Content header;
	private Body_Content body;

	public Html_File(Header_Content hc, Body_Content bc) {
		this.header = hc;
		this.body = bc;
	}

	public Header_Content getHeader() {
		return header;
	}

	public void setHeader(Header_Content header) {
		this.header = header;
	}

	public Body_Content getBody() {
		return body;
	}

	public void setBody(Body_Content body) {
		this.body = body;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
