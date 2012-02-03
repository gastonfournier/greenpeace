package org.ktonga.greenpeace



import org.junit.*
import grails.test.mixin.*

@TestFor(ApiController)
@Mock(Environment)
class ApiControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/environment/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.environmentInstanceList.size() == 0
        assert model.environmentInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.environmentInstance != null
    }

    void testSave() {
        controller.save()

        assert model.environmentInstance != null
        assert view == '/environment/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/environment/show/1'
        assert controller.flash.message != null
        assert Environment.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/environment/list'


        populateValidParams(params)
        def environment = new Environment(params)

        assert environment.save() != null

        params.id = environment.id

        def model = controller.show()

        assert model.environmentInstance == environment
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/environment/list'


        populateValidParams(params)
        def environment = new Environment(params)

        assert environment.save() != null

        params.id = environment.id

        def model = controller.edit()

        assert model.environmentInstance == environment
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/environment/list'

        response.reset()


        populateValidParams(params)
        def environment = new Environment(params)

        assert environment.save() != null

        // test invalid parameters in update
        params.id = environment.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/environment/edit"
        assert model.environmentInstance != null

        environment.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/environment/show/$environment.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        environment.clearErrors()

        populateValidParams(params)
        params.id = environment.id
        params.version = -1
        controller.update()

        assert view == "/environment/edit"
        assert model.environmentInstance != null
        assert model.environmentInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/environment/list'

        response.reset()

        populateValidParams(params)
        def environment = new Environment(params)

        assert environment.save() != null
        assert Environment.count() == 1

        params.id = environment.id

        controller.delete()

        assert Environment.count() == 0
        assert Environment.get(environment.id) == null
        assert response.redirectedUrl == '/environment/list'
    }
}
