package doc.visitor;

import hierarchy.classes.*;
import hierarchy.comments.*;

import java.io.*;
import java.util.*;

import com.hp.gagawa.java.*;
import com.hp.gagawa.java.elements.*;

public class HtmlVisitor implements DocVisitor {
	
	
	private String path;
	
	public HtmlVisitor(String path) {
		this.path = path;
		if (this.path.charAt(this.path.length()-1) == '/') {
			this.path = this.path.substring(0, this.path.length()-2);
		}
		
		(new File(path)).mkdir();
	}
	
	private void addHeader(Document doc) {
		
		Link cssStyle = new Link();
		cssStyle.setHref("docStyle.css");
		cssStyle.setType("text/css");
		cssStyle.setRel("stylesheet");
		doc.head.appendChild(cssStyle);
		
		Div header = new Div();
		header.setCSSClass("header");
		//header.appendChild(new H3().appendText("Doc++ Generator - Grandi Andrea"));
		header.appendChild(new Img("Doc++ Logo", "logo.png"));
		header.appendChild(new Br());

		header.appendChild(new A().setHref("index.html").appendChild( new Img("Class List", "list.png")));
		header.appendChild(new A().setHref("index.html").appendText("Class List"));
		
		header.appendChild(new A().setHref("credits.html").appendChild( new Img("Credits", "author.png")));
		header.appendChild(new A().setHref("credits.html").appendText("Credits"));

		doc.body.appendChild(header);
	}

	private List<Node> docSection(String sectionName, List members) {
		
		Vector<Node> res = new Vector<Node>();
		
		if (members.isEmpty()) {
			return res;
		}
		res.add(new H2().appendText(sectionName));
		Ul list = new Ul();
		
		for (Member m: (List<Member>) members) {
			Li member = new Li();
			member.setId(m.getName());
			list.appendChild( member.appendChild( (Node) m.accept(this) ));
		}
		
		res.add(list);
		return res;
	}
	
	
	private List<Node> stringList(String name, List<String> list) {
		
		Vector<Node> res = new Vector<Node>();
		
		if ( list.size() == 0) {
			return res;
		}
		
		res.add(new Br());
		res.add( new B().setCSSClass("stringList").appendText(name + ": ") );
		
		for (String p : list) {
			if (ClassManager.getInstance().existClassWithName(p)) {
				res.add( new A().setHref("class" + p + ".html").appendText(p) );
				res.add( new Text(" "));
			}
			else {
				res.add(new Text(p + " "));
			}	
		}
		return res;
	}
	
	private List<Node> getAttributeNode(String name, Comments comments) {
		
		Vector<Node> res = new Vector<Node>();
		
		if (comments.getAttribute(name) != null ){
			res.add( (Node) comments.getAttribute(name).accept(this) );
		}	
		return res;
	}
	
	
	private Document creditPage() {
		Document document = new Document(DocumentType.XHTMLTransitional);	
		addHeader(document);
		document.head.appendChild( new Title().appendText("Doc++ Credits") ) ;
		Body body = document.body;
		
		Div wrap = new Div();
		wrap.setCSSClass("doc");
		wrap.setId("doc");
		body.appendChild(wrap);
		
		wrap.appendChild(new H1().appendText("DoC++"));
		wrap.appendChild(new H2().appendText("Realizzato da Andrea Grandi"));
		wrap.appendChild(new P().appendText("Linguaggi e Modelli Computazionali M"));
		wrap.appendChild(new P().appendText("AA 2010/2011"));
		
		return document;
	}
	
	@Override
	public void visit(ClassManager classManager) {
		Document document = new Document(DocumentType.XHTMLTransitional);	
		addHeader(document);
		document.head.appendChild( new Title().appendText("Class List") );
		Body body = document.body;
		
		Div wrap = new Div();
		wrap.setCSSClass("doc");
		wrap.setId("doc");
		body.appendChild(wrap);
		
		wrap.appendChild(new H1().appendChild(new Text("Class List")));
		
		Ul classList = new Ul();
		classList.setCSSClass("classList");
		
		SortedSet<String> sortedSet= new TreeSet<String>(classManager.getAllClasses());
		
		for (String className : sortedSet) {
			A classLink = new A();
			classLink.setHref("class" +  className + ".html");
			classLink.appendChild(new Text(className));
			classList.appendChild(new Li().appendChild(classLink));
			Document doc = (Document) classManager.getClassByName(className).accept(this);
			try {
				FileWriter fstream = new FileWriter(path + "/class" + className + ".html");
				BufferedWriter out = new BufferedWriter(fstream);
				out.write(doc.write());
				out.close();
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
			}
			
			System.out.println("Generated file: class" + className + ".html");
		}
		
		wrap.appendChild(classList);
			
		try {
			FileWriter fstream = new FileWriter(path + "/index.html");
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(document.write());
			out.close();
			
			fstream = new FileWriter(path + "/credits.html");
			out = new BufferedWriter(fstream);
			out.write(creditPage().write());
			out.close();
			
			System.out.println("Generated file: index.html");
			
			FileUtils.copyAllResourcesTo(path);
			
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	@Override
	public Document visit(ClassDeclaration classDeclaration) {
		Document document = new Document(DocumentType.XHTMLTransitional);
		addHeader(document);
		document.head.appendChild( new Title().appendChild( new Text("Class " + classDeclaration.getName() ) ) );
		Body body = document.body;
		
		Div wrap = new Div();
		wrap.setCSSClass("doc");
		wrap.setId("doc");
		body.appendChild(wrap) ;
		
		wrap.appendChild(new H1().appendText("Class " + classDeclaration.getName()));
		
		wrap.appendChild( (Node) classDeclaration.getComments().accept(this)).appendChild(new Br());
		
		wrap.appendChild(getAttributeNode("author", classDeclaration.getComments()));
		wrap.appendChild(getAttributeNode("version", classDeclaration.getComments()));
		
		wrap.appendChild(stringList("Parents", classDeclaration.getParents()));
		wrap.appendChild(stringList("Friend Class", classDeclaration.getFriends()));
		
		wrap.appendChild(docSection("Constructors", classDeclaration.getConstructors()));
		wrap.appendChild(docSection("Destructors", classDeclaration.getDestructors()));
		wrap.appendChild(docSection("Fields", classDeclaration.getDatas()));
		wrap.appendChild(docSection("Functions", classDeclaration.getFunctions()));
		wrap.appendChild(docSection("Operators", classDeclaration.getOperators()));
			
		return document;	
	}

	@Override
	public Node visit(Comments comments) {
		Div comment = new Div();		
		comment.setCSSClass("comment");
		
		for (CommentElement ce : comments.getCommentElements()) {
			comment.appendChild((Node) ce.accept(this));
		}
		return comment;
	}

	@Override
	public Node visit(Attribute attribute) {
		Span attr = new Span();
		
		attr.setCSSClass("attribute");
		attr.appendChild(new B().appendText(attribute.getName() + ": "));
		
		for (CommentElement ce : attribute.getCommentElements()) {
			attr.appendChild((Node) ce.accept(this));
		}
		return attr;	
	}

	@Override
	public Node visit(ClassRefElement classRefElement) {
		String className = classRefElement.getClassName();
		if (ClassManager.getInstance().existClassWithName(className)) {
			
			ClassDeclaration cd = ClassManager.getInstance().getClassByName(className);
			
			if ( classRefElement.getAnchor() == null ) {
				return new A().setHref("class" + classRefElement.getClassName() + ".html").appendText(className);
			}
			else
			{
				if ( cd.hasMember(classRefElement.getAnchor()))
				{
					return new A().setHref("class" + classRefElement.getClassName() + ".html#" + classRefElement.getAnchor() )
					.appendText(className + "." + classRefElement.getAnchor() + " ");
				}
				else
				{
					System.out.println("Warning: can't find reference to element " + classRefElement.getAnchor() + " of class " + classRefElement.getClassName() + " ");
					return new Text(classRefElement.getClassName() + " ");
				}
				
			}
		}
		else {
			System.out.println("Warning: can't find reference to class " + classRefElement.getClassName());
			return new Text(classRefElement.getClassName() + " ");
		}
	}

	@Override
	public Node visit(EmailLinkElement emailLinkElement) {
		return new A().setHref("mailto:" + emailLinkElement.getAddress()).appendText(emailLinkElement.getAddress()).appendText(" ");
	}

	@Override
	public Node visit(EndLineElement endLineElement) {
		return new Br();
	}

	@Override
	public Node visit(TextElement textElement) {
		return new Text(EscapeText.escapeHTML(textElement.toString())+ " ");
	}

	@Override
	public Node visit(WebLinkElement webLinkElement) {
		return new A().setHref( webLinkElement.getUrl()).appendText(webLinkElement.getUrl()).appendText(" ");
	}

	@Override
	public Node visit(Function function) {
		
		Div res = new Div();
		res.setCSSClass("member");
		
		res.appendChild(new H3().appendText(function.getName()));
		
		Span name = new Span();
		name.setCSSClass("name");
		name.appendText(function.getVisibility() + " " );
		
		if (function.isConst())
			name.appendText("const " );
		
		if (function.isStatic())
			name.appendText("static " );
		
		if (function.getDataType() != null)
			name.appendText(function.getDataType().toString() + " ");
		
		name.appendText(function.getName() + "(" + function.getArgumentsString() + ")");
		res.appendChild(name);
		
		if ( function.getComments() != null ) {
			Div comments = (Div) function.getComments().accept(this);
			res.appendChild(comments);
			
			for (Data d : function.getArguments()) {
//				if (function.getComments().getAttribute(d.getName()) != null ){
//					comments.appendChild( (Node) function.getComments().getAttribute(d.getName()).accept(this) );
//					comments.appendChild(new Br());
//				}
				comments.appendChild(getAttributeNode(d.getName(), function.getComments()));
			}
			
			comments.appendChild(getAttributeNode("return", function.getComments()));
		}
		return res;		
	}

	@Override
	public Node visit(DataType dataType) {
		return new Text( EscapeText.escapeHTML(dataType.toString()));
	}

	@Override
	public Node visit(Data data) {	
		Div res = new Div();
		res.setCSSClass("member");
		
		res.appendChild(new H3().appendText(data.getName()));
		
		Span name = new Span();
		name.setCSSClass("name");
		name.appendText(data.getVisibility() + " " );
		
		if (data.isConst())
			name.appendText("const " );
		
		if (data.isStatic())
			name.appendText("static " );
		
		name.appendChild((Node) data.getDataType().accept(this));
		name.appendText(" " + data.getName());
		
		if (data.getArray() != null) {
			name.appendText(data.getArray());
		}
		
		res.appendChild(name);
		
		if ( data.getComments() != null ) {
			res.appendChild((Node) data.getComments().accept(this));
		}

		return res;
	}

	
}
