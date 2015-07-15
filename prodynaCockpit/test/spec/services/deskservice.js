'use strict';

describe('Service: deskService', function () {

  // load the service's module
  beforeEach(module('tedCockpitApp'));

  // instantiate service
  var deskService;
  beforeEach(inject(function (_deskService_) {
    deskService = _deskService_;
  }));

  it('should do something', function () {
    expect(!!deskService).toBe(true);
  });

});
