import org.ktonga.greenpeace.DemoContent

class BootStrap {

    def init = { servletContext ->
		new DemoContent().create()
    }
	
    def destroy = {
    }
}
