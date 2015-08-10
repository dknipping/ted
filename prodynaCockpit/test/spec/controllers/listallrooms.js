'use strict';

describe('Controller: ListAllRoomsCtrl', function () {

  // load the controller's module
  beforeEach(module('tedCockpitApp'));

  var ListAllRoomsCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ListAllRoomsCtrl = $controller('ListAllRoomsCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
