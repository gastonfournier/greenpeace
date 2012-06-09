package org.ktonga.greenpeace

import java.util.List
import org.ktonga.greenpeace.compatibility.EscapeApplicationsResponse
import org.ktonga.greenpeace.compatibility.EscapeEnvironmentsResponse

class ManageController {

	static allowedMethods = []
	def escapeConnectorService
	
	def serverSelection() {
		render view: 'serverSelection', model: []
	}

	def showServerConfiguration(String server) {
		try{
			EscapeEnvironmentsResponse resp = escapeConnectorService.environments(server);
			render view: 'importConfiguration', model: [environments: resp.environments, server: resp.server]
		} catch (Exception e){
			// TODO show an error page
			render view: 'importConfiguration', model: [environments: [], server: server]
		}
	}
	
	def showApplications(String server, String envs){
		EscapeApplicationsResponse resp;
		List<String> envsList = envs.split(",")
		for (String env : envsList) {
			resp = escapeConnectorService.applications(server, env)
		}
		render view: 'viewApplications', model: [server: resp.server, applications: resp.applications]
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
