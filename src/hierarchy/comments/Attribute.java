package hierarchy.comments;

import java.util.List;
import java.util.Vector;

import doc.visitor.DocVisitor;

public class Attribute {

	private List<CommentElement> commentElements;
	private String name;
	
	public Object accept(DocVisitor v) {
		return v.visit(this);
	}
	
	public Attribute(String name) {
		this.commentElements = new Vector<CommentElement>();
		this.name = name;
		
		if (this.name.charAt(0) == '@'){
			this.name = this.name.substring(1);
		}
		// this.name = this.name.toLowerCase();
		this.name = this.name.trim();
		
	}
	
	public String getName() {
		return name;
	}

	public void addElement(CommentElement com) {
		commentElements.add(com);
	}
	
	public int numElement() {
		return commentElements.size();
	}
	
	public List<CommentElement> getCommentElements() {
		return commentElements;
	}
	
	public String toString() {
		StringBuffer res = new StringBuffer();
		
		res.append("@" + name + " " );
		
		for (CommentElement ce : commentElements) {
			res.append(ce +" ");
		}
		
		return res.toString();
	}
}


