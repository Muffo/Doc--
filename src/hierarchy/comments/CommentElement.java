package hierarchy.comments;

import doc.visitor.DocVisitor;

public abstract class CommentElement {
	public abstract Object accept(DocVisitor v);
}
