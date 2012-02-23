package org.ktonga.greenpeace

import org.ktonga.greenpeace.Overrides.MergedProperty

class OverridesTests {

	void testEffectiveProperties() {
		Tag v = new Tag()
		v.propertyDefs = [prop1: "def1", prop2: "def2", prop3: "def3", prop4: "def4"]
		Overrides o = new Overrides(version1: v)
		o.propertyValues = [prop1: "val1", prop3: "val3"]
		def mergedProps = o.effectiveProperties()
		def expected = [
			["prop1", "val1", false],
			["prop2", "def2", true],
			["prop3", "val3", false],
			["prop4", "def4", true],
		]
		assert expected == mergedProps.collect {[it.key, it.value, it.usingDefault]}
	}
}
