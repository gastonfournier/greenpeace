package org.ktonga.greenpeace

class Project {

	static constraints = {
		description nullable: true
		defaultTag nullable: true
	}

	static hasMany = [tags: Tag, configurations: Configuration]

	String name
	String description
	// Use this tag when no tag is specified.
	Tag defaultTag
	
	Configuration getConfiguration(String env) {
		this.configurations.find {it.environment1.name == env}
	}
}
