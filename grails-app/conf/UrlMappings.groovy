class UrlMappings {
	
	//api/projects
	//api/environments
	//api/$project
	//api/$project/$env
	//api/$project/$env/$tag/$filename? [PUT, POST, GET, DELETE]
	
	//browse/byProject/$project?/$env?/$tag?
	//browse/byEnvironment/$project?/$env?/$tag?
	//browse/$project?/$env?/$tag?
	//browse/prop/$overridesId [PUT, POST, DELETE]

	static mappings = {
		"/$controller/environment/$name" {
			action = [GET: "show", PUT: "update", DELETE: "delete", POST: "save"]
		}
		"/$controller/$project/$env/$tag/$filename" {
			action = "effectiveProperties"
		}
		"/$controller/$action?/$name?" {}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
