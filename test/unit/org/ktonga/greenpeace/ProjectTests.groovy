package org.ktonga.greenpeace

class ProjectTests {

    void testGetConfiguration() {
       Environment e1 = new Environment(name: "E1")
       Environment e2 = new Environment(name: "E2")
	   Configuration c1 = new Configuration(environment1: e1)
       Configuration c2 = new Configuration(environment1: e2)
	   Project p = new Project()
	   p.addToConfigurations(c1)
	   p.addToConfigurations(c2)
	   
	   assert c1 == p.getConfiguration("E1")
	   assert c2 == p.getConfiguration("E2")
	   assert null == p.getConfiguration("E3")
    }
}
