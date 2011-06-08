package hierarchy.comments;

import doc.visitor.DocVisitor;

public class EndLineElement extends CommentElement {
	
	public Object accept(DocVisitor v) {
		return v.visit(this);
	}
	
	public String toString() {
		return "\n";
	}
	
}
