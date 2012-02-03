class UrlMappings {

	static mappings = {
		"/api/environment/$name" {
			controller = "api"
			action = [GET: "show", PUT: "update", DELETE: "delete", POST: "save"]
		}
		"/api/$project/$env/$ver/$filename" {
			controller = "api"
			action = "effectiveProperties"
		}
		"/api/$action" {
			controller = "api"
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
