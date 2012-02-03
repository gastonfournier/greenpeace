package org.ktonga.greenpeace

import org.apache.catalina.connector.RequestFacade.GetAttributePrivilegedAction;

class Configuration {

	static belongsTo = [project: Project]
	static hasMany = [overrides: Overrides]
	
	Environment environment1
	
	Overrides getOverrides(String ver) {
		this.overrides.find {it.version1.matches(ver)}
	}
	
}
