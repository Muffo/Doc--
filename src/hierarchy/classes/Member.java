package hierarchy.classes;

import hierarchy.comments.Comments;
import doc.visitor.DocVisitor;

public abstract class Member {
	
	protected String name;
	protected DataType type;
	protected VisibilityAttribute visibility;
	protected boolean isConst;
	protected boolean isStatic;
	protected Comments comments;
	
	public String getName() {
		return name;
	}
	
	public DataType getDataType() {
		return type;
	}
	
	public VisibilityAttribute getVisibility() {
		return visibility;
	}
	
	public boolean isConst() {
		return isConst;
	}
	
	public void setConst(boolean c) {
		isConst = c;
	}
	
	public boolean isStatic() {
		return isStatic;
	}
	
	public void setStatic(boolean s) {
		isStatic = s;
	}
	
	
	public void setComments(Comments comments) {
		this.comments = comments;
	}
	
	public Comments getComments() {
		return comments;
	}

	public abstract Object accept(DocVisitor v);
	
}
