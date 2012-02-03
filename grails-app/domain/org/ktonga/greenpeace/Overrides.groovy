package org.ktonga.greenpeace

import java.util.List;
import java.util.Map;

class Overrides {

	static belongsTo = [configuration: Configuration]
	
	Version version1
	// Map<propName, overrideValue>
	Map<String, String> propertyValues
	
	List<MergedProperty> effectiveProperties() {
		this.version1.propertyDefs.keySet().sort().collect {
			def usingDefault = !this.propertyValues.containsKey(it)
			def value = usingDefault ? this.version1.propertyDefs[it] : this.propertyValues[it]
			new MergedProperty(key: it, value: value, usingDefault: usingDefault)
		}
	}

	static class MergedProperty {
		String key
		String value
		boolean usingDefault
	}

}
