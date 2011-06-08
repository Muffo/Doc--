package hierarchy.comments;

import doc.visitor.DocVisitor;

public class EmailLinkElement extends CommentElement {
	
	private String address;
	
	public EmailLinkElement(String address) {
		this.address = address;
		
//		if (this.address.charAt(0) == '[' && this.address.charAt(this.address.length()-1) == ']'){
//			this.address = this.address.substring(1, this.address.length()-1);
//		}
		
	}

	public String getAddress() {
		return address;
	}
	
	public Object accept(DocVisitor v) {
		return v.visit(this);
	}
	
	public String toString() {
		return address;
	}
}