'use strict';

describe('Directive: roomListElement', function () {

  // load the directive's module
  beforeEach(module('tedCockpitApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<room-list-element></room-list-element>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the roomListElement directive');
  }));
});
