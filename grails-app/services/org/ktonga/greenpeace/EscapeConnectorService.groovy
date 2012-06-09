package org.ktonga.greenpeace

import java.util.List;

import org.apache.commons.lang.StringUtils
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

class EscapeConnectorService {
	def downloaderService
	private ObjectMapper mapper = new ObjectMapper();
	
    public List<String> environments(String server) {
		String fixedServer = fix(server)
        List<String> envs = []
		String finalUrl = "${fixedServer}/environments";
		try {
			log.info("Querying ${finalUrl}")
			String contents = downloaderService.download(finalUrl)
			if (!StringUtils.isEmpty(contents)){
				envs = mapper.readValue(contents, new TypeReference<List<String>>(){})
			}
		} catch (Exception e){
			log.error("An error ocurred while downloading ${finalUrl}", e)
		}
		return envs;
    }
    
	public List<String> applications(String server, String environment){
		String fixedServer = fix(server)
		List<String> applications = []
		String finalUrl = "${fixedServer}/environments/${environment}";
		try {
			String contents = downloaderService.download(finalUrl)
			if (!StringUtils.isEmpty(contents)){
				applications = mapper.readValue(contents, new TypeReference<List<String>>(){})
			}
		} catch (Exception e){
			log.error("An error ocurred while downloading ${finalUrl}", e)
		}
		return applications
	}
	
	// fixes server add http:// remove last / if exists
	private String fix(String serverString){
		String server = serverString
		if (! (serverString =~  /https?:\/\// )){
			server = "http://${serverString}"
		}
		
		if (server.endsWith("/")){
			server = server.substring(0, server.length() - 1)
		}
		
		return server;
	} 
}
