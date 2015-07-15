'use strict';

/**
 * @ngdoc function
 * @name tedCockpitApp.controller:TestCtrl
 * @description
 * # TestCtrl
 * Controller of the tedCockpitApp
 */
angular.module('tedCockpitApp')
  .controller('CreateDeskCtrl', function ($log, $window, deskService) {

    this.desksModel = deskService;
    this.desk = {
    };

    this.createDesk = function() {
      $log.debug('Creating desk, sending it to backend via $http: ', this.desk);
      deskService.createDeskViaHttp(this.desk);
    };
  });
