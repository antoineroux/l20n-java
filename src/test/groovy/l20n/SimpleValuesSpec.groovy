package l20n

import spock.lang.Specification;



class SimpleValuesSpec extends Specification {
    
    L20nContext l20n
    
    def setup() {
        l20n = new L20nContext()
        l20n.loadResource("/l20n/lol/fixtures/simple_values.lol")
    }
    
    def "reads an entity with a string value"() {
        given: "a lol resource containing an entity with identifier brandName"
        l20n.requestLocales()
        
        expect: "the corresponding value is 'Firefox'"
        l20n.get("brandName") == "Firefox"
    }
    
    def "an entity with a hash value and no default returns the id"() {
        given: "a lol resource containing an entity brandName21 with a basic hash value with no default"
        l20n.requestLocales()
        
        expect: "the entity returns only its identifier"
        l20n.get("brandName21") == "brandName21"
    }
    
    
    def "an entity with a hash that has a default value returns the default value"() {
        given: "a lol resource containing an entity with identifiern brandName22 that contains a hash with a default value"
        l20n.requestLocales()
        
        expect: "the entity returns the default (the feminine)"
        l20n.get("brandName22") == "Aurora"
    }
    
    def "an entity with a hash an index returns the element specified in the index"() {
        given: "an entity brandName23 containing a hash and an index specifying the value of this hash to be returned"
        l20n.requestLocales()
        
        expect: "the value of the hash is returned"
        l20n.get("brandName23") == "Aurora"
    }
    
    def "an entity with an index referencing no hash key returns the entity id"() {
        given: "an entity brandName24 with an index 'neutral' that does exist in the hash"
        l20n.requestLocales()
        
        expect: "brandName24 is returned"
        l20n.get("brandName24") == "brandName24"
    }
    
    def "an entity with an index that has two expressions while the hash has only one level returns the entity id"() {
        given: "an entity with an index that has two expressions and a hash with only one level"
        l20n.requestLocales()
        
        expect: "the entity id is returned"
        l20n.get("brandName25") == "brandName25"    
    }
}