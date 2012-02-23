package org.ktonga.greenpeace

class TagTests {

    void testMatchingPrefix() {
       Tag v = new Tag(name: "v1.2", matchingPrefix: "1.2")
	   assert v.matches("1.2")
	   assert v.matches("1.2.3")
	   assert v.matches("1.2-SNAPSHOT")
	   assert !v.matches("1.1-SNAPSHOT")
	   assert !v.matches("1")
	   assert !v.matches("1.3")
    }
    
    void testMatchingEmptyPrefix() {
    	Tag emptyPrefix = new Tag(name: "v1.2", matchingPrefix: "")
    	Tag nullPrefix = new Tag(name: "v1.2", matchingPrefix: null)
    	assert emptyPrefix.matches("1.2")
    	assert emptyPrefix.matches("1.2-SNAPSHOT")
    	assert nullPrefix.matches("1.2.3")
    	assert nullPrefix.matches("1.1-SNAPSHOT")
    }
}
