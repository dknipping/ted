angular.module('tedCockpitApp')
  .controller('CreateReservationCtrl', function ($routeParams, $window, $log) {

    this.dateOptions = {
      formatYear: 'yy',
      startingDay: 1
    };
    this.dateFormat = 'dd.MM.yyyy';
    this.openPicker = function ($event) {
      $event.preventDefault();
      $event.stopPropagation();
      this.pickerOpened = true;
    };

    this.itemType = $routeParams.itemType;
    this.itemId = $routeParams.itemId;

    this.createReservation = function() {
      $log.debug('Create triggered...');
      $window.alert('You picked the date: ' + this.fromDate);
    };
  });

