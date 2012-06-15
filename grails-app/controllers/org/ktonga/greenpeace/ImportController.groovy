package org.ktonga.greenpeace

import java.util.List
import org.ktonga.greenpeace.compatibility.EscapeApplicationsResponse
import org.ktonga.greenpeace.compatibility.EscapeEnvironmentsResponse

class ImportController {

	static allowedMethods = []
	def escapeConnectorService
	def importService
	
	def main() {
		render view: 'main', model: []
	}

	def environments(String server) {
		try{
			EscapeEnvironmentsResponse resp = escapeConnectorService.environments(server);
			render view: 'environments', model: [environments: resp.environments, server: resp.server]
		} catch (Exception e){
			// TODO show an error page
			render view: 'environments', model: [environments: [], server: server]
		}
	}
	
	def applications(String server, String envs){
		EscapeApplicationsResponse resp;
		List<String> envsList = envs.split(",");
		Set<String> applications = new HashSet<String>();
		Map<String, List<String>> environments = [:];
		for (String env : envsList) {
			resp = escapeConnectorService.applications(server, env)
			applications.addAll(resp.applications);
			environments[env] = resp.applications;
		}
		render view: 'applications', model: [server: resp.server, applications: applications.sort(), appsPerEnv: environments]
	}
	
	def importConfiguration(String server, String environments, String applications){
		List<String> envs = environments.split(",");
		List<String> apps = applications.split(",");
		importService.importConfigurations(server, apps, envs);
		render view: 'main', model: []
	}
}
