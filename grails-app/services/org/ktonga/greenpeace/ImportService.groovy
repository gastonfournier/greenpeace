package org.ktonga.greenpeace

import java.util.List;

import org.apache.commons.lang.StringUtils
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.ktonga.greenpeace.compatibility.EscapeApplicationsResponse;
import org.ktonga.greenpeace.compatibility.EscapeEnvironmentsResponse


class ImportService {
	def escapeConnectorService
	
	def importConfigurations(String server, List<String> apps, List<String> envs){
		for (String env : envs) {
			EscapeApplicationsResponse resp = escapeConnectorService.applications(server, env)
			for (String envApp : resp.applications) {
				if (apps.contains(envApp)){
					log.info("Importing ${envApp} into ${env}");
					Environment importedEnv = importEnvironment(env);
					Project importedProject = importProject(envApp);

					Map<String, String> configurations = escapeConnectorService.configurations(server, env, envApp)
					log.info(configurations)
					// TODO import the app and it's configuration as a new version for the app
					// add a tag and the configurations
				}
			}
		}
	}
	
	def importProject(String name){
		Project project = Project.findByName(name);
		if (project == null){
			project = new Project();
			project.name = name;
			project.description = "Project '${name}' descriptions";
			project.save();
		}
		return project;
	}
	
	def importEnvironment(String env){
		// TODO get the configuration and add it as a new version
		Environment greenpeaceEnv = Environment.findByName(env);
		if (greenpeaceEnv == null){
			// create a new environment
			greenpeaceEnv = new Environment();
			greenpeaceEnv.name = env;
			greenpeaceEnv.description = "${env} imported from Escape";
			greenpeaceEnv.save();
		}
		return greenpeaceEnv;
	}
}
