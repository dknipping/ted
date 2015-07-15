'use strict';

/**
 * @ngdoc service
 * @name tedCockpitApp.roomResource
 * @description
 * # roomResource
 * Service in the tedCockpitApp.
 */
angular.module('tedCockpitApp')
  .service('roomService', function () {

    var model;

    var nrCounter = 1;


    var addNewRoom = function() {
      model.rooms.push(new Room(nrCounter, 4, 'Büro ' + nrCounter));
      nrCounter++;
    };

    var addNewRoomViaHttp = function() {
      var newRoom = new Room(nrCounter, 4, 'Büro ' + nrCounter);
      nrCounter++;

      $http({
        method: 'POST',
        url: '',
        data: newRoom
      }).success(function(persistedRoom){
        model.rooms.push(persistedRoom);
      }).error(function(errorMessage){
        console.log('Error adding room: ', errorMessage);
      });
    };

    var deleteRoom = function(deleteRoom) {
      model.rooms = model.rooms.filter(function(room) {
        return room.nr !== deleteRoom.nr;
      });
    };

    model = {
      /** @type {Array.<Room>} */
      rooms : [
        new Room(1000, 20, "Konferenzraum")
      ],

      addNewRoom : addNewRoom,
      deleteRoom : deleteRoom
    };

    return model;
  });
