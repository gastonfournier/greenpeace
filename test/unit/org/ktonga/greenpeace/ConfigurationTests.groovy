package org.ktonga.greenpeace

import grails.test.mixin.Mock
import grails.test.mixin.TestFor

import java.awt.print.Book

@TestFor(Configuration)
@Mock(Project)
class ConfigurationTests {

	void testGetOverrides() {
		Tag v1 = new Tag(matchingPrefix: "1.1")
		Tag v2 = new Tag(matchingPrefix: "1.2")
		Overrides o1 = new Overrides(version1: v1)
		Overrides o2 = new Overrides(version1: v2)
		Configuration conf = new Configuration()
		conf.addToOverrides(o1)
		conf.addToOverrides(o2)
		Project p = new Project(defaultVersion: v2)
		p.addToConfigurations(conf)

		assert o1 == conf.getOverrides("1.1")
		assert o1 == conf.getOverrides("1.1.2")
		assert o1 == conf.getOverrides("1.1-SNAPSHOT")
		assert o2 == conf.getOverrides("1.2")
		assert o2 == conf.getOverrides("1.2.1")
		assert o2 == conf.getOverrides("1.2-SNAPSHOT")
		// default when no version matches
		assert o2 == conf.getOverrides("1")
		assert o2 == conf.getOverrides("1.3")
		assert o2 == conf.getOverrides("1.3-SNAPSHOT")
		assert o2 == conf.getOverrides("")
		assert o2 == conf.getOverrides(null)
	}
}
