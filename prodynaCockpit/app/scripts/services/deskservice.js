'use strict';

/**
 * @ngdoc service
 * @name tedCockpitApp.deskService
 * @description
 * # deskService
 * Service in the tedCockpitApp.
 */
angular.module('tedCockpitApp')
  .service('deskService', function ($http, $window) {

    var model;

    var createDeskViaHttp = function (desk) {

      return $http({
        method: 'POST',
        url: 'http://pd-wg000400:9030/zuul/desk/desks',
        data: desk
      })
        .success(function (persistedDesk) {
          model.loadedDesk = persistedDesk;
          model.desks.push(persistedDesk);
        })
        .error(function (errorMessage) {
          console.log('Error adding desk: ', errorMessage);
          $window.alert('Error sending desk to backend, see console for further information.');
        });
    };

    model = {
      loadedDesk : null,
      desks: [],

      createDeskViaHttp: createDeskViaHttp
    };

    return model;
  });
