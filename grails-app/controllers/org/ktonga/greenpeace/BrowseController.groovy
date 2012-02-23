package org.ktonga.greenpeace

class BrowseController {

	static allowedMethods = [updatePropValue: "POST"]

	def byEnvironment() {
		def byEnv = [:]
		Project.list().each { proj ->
			proj.configurations.each { conf ->
				def env = conf.environment1
				if(!byEnv[(env)]) byEnv[(env)] = []
				byEnv[(env)] << proj
			}
		}
		def sorted = byEnv.keySet().sort({it.name}).collectEntries({[
				it,
				byEnv[it].sort({it.name})
			]})
		render view: 'browse', model: [parents: sorted]
	}

	def byProject() {
		def sorted = Project.list().sort({it.name}).collectEntries({[
				it,
				it.configurations.collect({it.environment1}).sort({it.name})
			]})
		render view: 'browse', model: [parents: sorted]
	}

	def config() {
		def config = this.findConfig(params)
		render template: 'config', model: [config: config]
	}

	def updatePropValue() {
		def overrides = Overrides.get(params.id)
		overrides.propertyValues[params.key] = params.value
		overrides.save()
		render params.value
	}

	private Configuration findConfig(def parameters) {
		Project.findByName(params.project).getConfiguration(params.env)
	}
}
