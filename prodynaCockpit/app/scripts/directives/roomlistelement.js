/**
 * @ngdoc directive
 * @name tedCockpitApp.directive:roomListElement
 * @description
 * # roomListElement
 */
angular.module('tedCockpitApp')
  .directive('roomListElement', function () {
    'use strict';

    return {
      restrict: 'E',
      scope : {
        /** @type {Room} */
        room : '='
      },
      templateUrl: 'scripts/directives/roomlistelement.html',
      controller: 'RoomListElementCtrl',
      controllerAs: 'roomListElementCtrl',
      bindToController: true
    };
  })
  .controller('RoomListElementCtrl', function($location, roomService) {
    'use strict';

    var self = this;

    this.editRoom = function() {
      console.log('Editing room ' + self.room.name + ' in directive.');
    };

    this.bookRoom = function() {
      console.log('Booking room ' + self.room.name + ' in directive.');
      $location.path('/createReservation/room/' + self.room.nr);
    };

    this.deleteRoom = function() {
      console.log('Deleting room ' + self.room.name + ' in directive.');
      roomService.deleteRoom(self.room);
    };
  });
