'use strict';

/**
 * @ngdoc function
 * @name tedCockpitApp.controller:ListAllRoomsCtrl
 * @description
 * # ListAllRoomsCtrl
 * Controller of the tedCockpitApp
 */
angular.module('tedCockpitApp')
  .controller('ListAllRoomsCtrl', function (roomService) {

    this.roomsModel = roomService;

    this.addNewRoom = function() {
      console.log('Adding new room.');
      roomService.addNewRoom();
    };

    this.addNewRoom();
    this.addNewRoom();
    this.addNewRoom();
    this.addNewRoom();
  });
