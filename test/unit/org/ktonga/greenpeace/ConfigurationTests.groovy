package org.ktonga.greenpeace

class ConfigurationTests {

	void testGetOverrides() {
		Version v1 = new Version(matchingPrefix: "1.1")
		Version v2 = new Version(matchingPrefix: "1.2")
		Overrides o1 = new Overrides(version1: v1)
		Overrides o2 = new Overrides(version1: v2)
		Configuration conf = new Configuration()
		conf.addToOverrides(o1)
		conf.addToOverrides(o2)

		assert o1 == conf.getOverrides("1.1")
		assert o1 == conf.getOverrides("1.1.2")
		assert o1 == conf.getOverrides("1.1-SNAPSHOT")
		assert o2 == conf.getOverrides("1.2")
		assert o2 == conf.getOverrides("1.2.1")
		assert o2 == conf.getOverrides("1.2-SNAPSHOT")
		assert null == conf.getOverrides("1")
		assert null == conf.getOverrides("1.3")
		assert null == conf.getOverrides("1.3-SNAPSHOT")
	}
}
