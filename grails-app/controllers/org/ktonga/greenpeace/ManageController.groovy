package org.ktonga.greenpeace

import org.apache.commons.lang.StringUtils
import org.codehaus.jackson.map.ObjectMapper
import org.codehaus.jackson.type.TypeReference

class ManageController {

	static allowedMethods = []
	private ObjectMapper mapper = new ObjectMapper();
	def downloaderService
	
	def serverSelection() {
		render view: 'serverSelection', model: []
	}

	def showServerConfiguration() {
		def server = fix(params.server);
		List<String> envs = []
		String finalUrl = "${server}/environments";
		try {
			String contents = downloaderService.download(finalUrl)
			if (!StringUtils.isEmpty(contents)){
				envs = mapper.readValue(contents, new TypeReference<List<String>>(){})
			}
		} catch (Exception e){
			log.error("An error ocurred while downloading ${finalUrl}", e)
		}
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

	// fixes server add http:// remove last / if exists
	private String fix(String serverString){
		String server = serverString
		if (! (/https?:\/\// =~ serverString)){
			server = "http://${serverString}"
		}
		
		if (server.endsWith("/")){
			server = server.substring(0, server.length() - 1)
		}
	}
}
