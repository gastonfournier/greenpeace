package org.ktonga.greenpeace

class DemoContent {
	private static final String LONG_KEY = "a.very.very.very.very.very.very.very.very.very.very.very.long.key"
	private static final String LONG_VALUE = "with.a.very.very.very.very.very.very.very.very.very.very.very.very.very.very.very.very.very.very.very.very.long.value"
	
	def environments

	def create() {
		this.createEnvironments()
		this.createProjects()
	}

	def createEnvironments() {
		if(!this.environments) {
			this.environments = [
				new Environment(name: "CI", description: "Continuous Integration"),
				new Environment(name: "RC", description: "Release Candidate"),
				new Environment(name: "BC", description: "Backward Compatibility"),
				new Environment(name: "PROD", description: "Production"),
			]
			this.environments*.save(flush: true)
		}
		return this.environments
	}

	def createProjects() {
		def projects = [
			this.createProject(1, 0, 1),
			this.createProject(2, 1, 2),
			this.createProject(3, 2, 3)
		]
		projects*.save(flush: true)
		println "*+*+*+*+*+ " +  projects[0].errors
	}

	def createProject(def num, def env1, def env2) {
		def p = new Project(name: "project${num}", description: "Project ${num}")
		p.save(flush: true)
		println "****************" + p.errors
		Tag v1 = new Tag(name: "v1.1", matchingPrefix: "1.1")
		v1.propertyDefs = toString(["p${num}.prop1": "p${num} value1", "p${num}.prop2": "p${num} value2", "p${num}.prop3": "p${num} value3", (LONG_KEY): LONG_VALUE])
		Tag v2 = new Tag(name: "v1.2", matchingPrefix: "1.2")
		v2.propertyDefs = toString(["p${num}.prop4": "p${num} value4", "p${num}.prop5": "p${num} value5", "p${num}.prop6": "p${num} value6"])
		p.addToTags(v1)
		p.addToTags(v2)
		p.defaultTag = v1
		p.save(flush: true)
		println "****************" + p.errors
		Environment e = this.environments[env1]
		Overrides o1 = new Overrides(tag: v1)
		o1.propertyValues = toString(["p${num}.prop1": "p${num} ${e.name} override1", "p${num}.prop2": "p${num} ${e.name} override2"])
		Overrides o2 = new Overrides(tag: v2)
		o2.propertyValues = toString(["p${num}.prop4": "p${num} ${e.name} override4", "p${num}.prop5": "p${num} ${e.name} override5"])
		Configuration c1 = new Configuration(environment1: e)
		c1.addToOverrides(o1)
		c1.addToOverrides(o2)
		p.save(flush: true)
		println "****************" + p.errors
		e = this.environments[env2]
		o1 = new Overrides(tag: v1)
		o1.propertyValues = toString(["p${num}.prop1": "p${num} ${e.name} override1", "p${num}.prop2": "p${num} ${e.name} override2"])
		o2 = new Overrides(tag: v2)
		o2.propertyValues = toString(["p${num}.prop4": "p${num} ${e.name} override4", "p${num}.prop5": "p${num} ${e.name} override5"])
		Configuration c2 = new Configuration(environment1: e)
		c2.addToOverrides(o1)
		c2.addToOverrides(o2)
		p.addToConfigurations(c1)
		p.addToConfigurations(c2)
		p.save(flush: true)
		println "****************" + p.errors
		return p
	}
	
	def toString(def map) {
		map.collectEntries {[it.key.toString(), it.value.toString()]}
	}
}
