angular.module('sunatApp', ['ja.qr']).

controller('CodigoQrController', function ($scope) {

  $scope.qrcodeString = 'YOUR TEXT TO ENCODE';
  $scope.size = 250;
  $scope.correctionLevel = '';
  $scope.typeNumber = 0;
  $scope.inputMode = '';
  $scope.image = true;

});
