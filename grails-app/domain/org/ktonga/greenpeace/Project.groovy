package org.ktonga.greenpeace

class Project {

	static constraints = {
		description nullable: true
		defaultVersion nullable: true
	}

	static hasMany = [versions: Version, configurations: Configuration]

	String name
	String description
	// Use this version when no version is specified.
	Version defaultVersion
	
	Configuration getConfiguration(String env) {
		this.configurations.find {it.environment1.name == env}
	}
}
