package org.ktonga.greenpeace

class Version {

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
}
