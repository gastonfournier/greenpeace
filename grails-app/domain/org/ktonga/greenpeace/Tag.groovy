package org.ktonga.greenpeace

class Tag {

	static constraints = {
		matchingPrefix nullable: true
	}

	static belongsTo = [project: Project]
	
	String name
	String matchingPrefix
	// Map<propName, defaultValue>
	Map<String, String> propertyDefs
	
	boolean matches(String ver) {
		!this.matchingPrefix || ver.startsWith(this.matchingPrefix)
	}
	
	String getEncodedName() {
		this.name.replace('.','_')
	}
}
