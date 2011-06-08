package hierarchy.comments;

import doc.visitor.DocVisitor;

public class TextElement extends CommentElement {
	
	private String text;
	
	public TextElement(String text) {
		this.text = text;
	}

//	public String getText() {
//		return text;
//	}
	
	
	public Object accept(DocVisitor v) {
		return v.visit(this);
	}
	
	public String toString() {
		return text;
	}
}
