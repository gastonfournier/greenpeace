package org.ktonga.greenpeace

import org.apache.catalina.connector.RequestFacade.GetAttributePrivilegedAction;

class Configuration {

	static belongsTo = [project: Project]
	static hasMany = [overrides: Overrides]
	
	Environment environment1
	
	Overrides getOverrides(String tag) {
		def found
		if(!tag || !(found = this.overrides.find {it.tag.matches(tag)})) {
			found = this.overrides.find {it.tag == this.project.defaultTag}
		}
		return found
	}
	
}
