package l20n

import spock.lang.Specification;

class LocaleSpec extends Specification {
    
    def "requesting an entity that does not exists returns the id"() {
        given: "a locale with a few entities"
        InputStream is = this.getClass().getResourceAsStream("/l20n/lol/fixtures/simple_values.lol")
        def resource = new Resource(is)
        
        def locale = new Locale()
        locale.linkResource(resource)
        
        when: "requesting an entity that does not exist"
        def value = locale.getValue("thisIdDoesNotExist")
        
        then: "the id is returned"
        value == "thisIdDoesNotExist"
    }

}
