package org.ktonga.greenpeace

import java.util.List
import org.ktonga.greenpeace.compatibility.EscapeApplicationsResponse
import org.ktonga.greenpeace.compatibility.EscapeEnvironmentsResponse

class ManageController {

	static allowedMethods = []

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
