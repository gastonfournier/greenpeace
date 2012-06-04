package org.ktonga.greenpeace

import org.apache.commons.lang.StringUtils
import org.codehaus.jackson.map.ObjectMapper
import org.codehaus.jackson.type.TypeReference

class ManageController {

	static allowedMethods = []
	def escapeConnectorService
	
	def serverSelection() {
		render view: 'serverSelection', model: []
	}

	def showServerConfiguration() {
		def server = fix(params.server);
		def envs = escapeConnectorService.environments(params.server)
		render view: 'importConfiguration', model: [parents: envs, server: server]
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
