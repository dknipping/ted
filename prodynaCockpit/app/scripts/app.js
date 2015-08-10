
/**
 * @ngdoc overview
 * @name tedCockpitApp
 * @description
 * # tedCockpitApp
 *
 * Main module of the application.
 */
angular.module('tedCockpitApp', [
    'ngAria',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ui.bootstrap'
  ])
  .config(function ($routeProvider) {
    'use strict';

    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/addDesk', {
        templateUrl: 'views/createdesk.html',
        controller: 'CreateDeskCtrl',
        controllerAs: 'createDeskCtrl'
      })
      .when('/listAllRooms', {
        templateUrl: 'views/listallrooms.html',
        controller: 'ListAllRoomsCtrl',
        controllerAs: 'listAllRoomsCtrl'
      })
      .when('/createReservation/:itemType/:itemId', {
        templateUrl: 'views/createreservation.html',
        controller: 'CreateReservationCtrl',
        controllerAs: 'createReservationCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  })
  .config(function($httpProvider) {
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
    $httpProvider.defaults.useXDomain = true;
  });
