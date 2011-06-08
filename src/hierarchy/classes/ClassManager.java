package hierarchy.classes;

import java.util.*;

import doc.visitor.DocVisitor;

public class ClassManager {

	private static ClassManager instance;

	private Map<String, ClassDeclaration> classes;
	private ClassDeclaration activeClass;
	private VisibilityAttribute currentVisibility;
	
	// Singleton
	public static ClassManager getInstance() {
		if (instance == null) {
			instance = new ClassManager();
		}
		return instance;
	}
	
	private ClassManager() {
		classes = new HashMap<String, ClassDeclaration>();
		resetDefaultVisibility();
	}
	
	public void accept(DocVisitor v) {
		v.visit(this);
	}
	
	public ClassDeclaration getClassByName(String name) {
		
		String tName = name.trim();
		
		if (!classes.containsKey(tName)) {
			classes.put(tName, new ClassDeclaration(tName));
		}
		
		return classes.get(tName);
	}
	
	public boolean existClassWithName(String name) {
		return classes.keySet().contains(name);
	}
	
	public Set<String> getAllClasses() {
		return classes.keySet();
	}
	
	
	public void resetDefaultVisibility() {
		currentVisibility = VisibilityAttribute.PRIVATE;
	}
	
	public void setVisibility(VisibilityAttribute vis) {
		currentVisibility = vis;
	}
	
	public VisibilityAttribute getVisibility() {
		return currentVisibility;
	}
	
	
	public void setVisibility(String vis) {
		if (vis.trim().equalsIgnoreCase("public")) {
			currentVisibility = VisibilityAttribute.PUBLIC;
		}
		else if (vis.trim().equalsIgnoreCase("private")) {
			currentVisibility = VisibilityAttribute.PRIVATE;
		}
		else if (vis.trim().equalsIgnoreCase("protected")) {
			currentVisibility = VisibilityAttribute.PROTECTED;
		}
	}
	
	public ClassDeclaration getActiveClass() {
		return activeClass;
	}

	public void setActiveClass(ClassDeclaration activeClass) {
		this.activeClass = activeClass;
	}

	
	
}
