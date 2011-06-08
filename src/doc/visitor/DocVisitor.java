package doc.visitor;

import hierarchy.classes.ClassDeclaration;
import hierarchy.classes.ClassManager;
import hierarchy.classes.Data;
import hierarchy.classes.DataType;
import hierarchy.classes.Function;
import hierarchy.comments.Attribute;
import hierarchy.comments.ClassRefElement;
import hierarchy.comments.Comments;
import hierarchy.comments.EmailLinkElement;
import hierarchy.comments.EndLineElement;
import hierarchy.comments.TextElement;
import hierarchy.comments.WebLinkElement;

import com.hp.gagawa.java.Document;
import com.hp.gagawa.java.Node;


public interface DocVisitor {
	void visit(ClassManager classManager);
	Document visit(ClassDeclaration classDeclaration);
	
	Node visit(Comments comments);
	Node visit(Attribute attribute);
	Node visit(ClassRefElement classRefElement);
	Node visit(EmailLinkElement emailLinkElement);
	Node visit(EndLineElement endLineElement);
	Node visit(TextElement textElement);
	Node visit(WebLinkElement webLinkElement);
	
	Node visit(Function function);
	Node visit(DataType dataType);
	Node visit(Data data);

}
