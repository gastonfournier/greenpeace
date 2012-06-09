package org.ktonga.greenpeace

import java.util.List;

import org.apache.commons.lang.StringUtils
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.ktonga.greenpeace.compatibility.EscapeApplicationsResponse;
import org.ktonga.greenpeace.compatibility.EscapeEnvironmentsResponse

class EscapeConnectorService {
	def downloaderService
	private ObjectMapper mapper = new ObjectMapper();
	
    public EscapeEnvironmentsResponse environments(String server) {
		String fixedServer = fix(server)
        EscapeEnvironmentsResponse ret = new EscapeEnvironmentsResponse(server: fixedServer, environments: [])
		String finalUrl = "${fixedServer}/environments";
		log.info("Querying ${finalUrl}")
		String contents = downloaderService.download(finalUrl)
		if (!StringUtils.isEmpty(contents)){
			ret.environments = mapper.readValue(contents, new TypeReference<List<String>>(){}); 
		}
		return ret;
    }
    
	public EscapeApplicationsResponse applications(String server, String environment){
		String fixedServer = fix(server)
		EscapeApplicationsResponse resp = new EscapeApplicationsResponse(server: fixedServer, environment: environment);
		List<String> applications = []
		String finalUrl = "${fixedServer}/environments/${environment}";
		log.info("Querying ${finalUrl}")
		String contents = downloaderService.download(finalUrl)
		if (!StringUtils.isEmpty(contents)){
			applications = mapper.readValue(contents, new TypeReference<List<String>>(){})
			resp.applications = applications
		}
		return resp
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
