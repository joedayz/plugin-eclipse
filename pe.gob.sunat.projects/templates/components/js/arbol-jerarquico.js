!(function(mydoc3) {
  'use strict';

  var treeModule = angular.module('sunatApp', ['ngRoute', 'ngAnimate',
      , 'ui.tree', 'ui.bootstrap'
    ])
    .config(['treeConfig',
      function(treeConfig) {

 
        //UI Tree  
        treeConfig.defaultCollapsed = true; // collapse nodes by default

     
      }
    ])
   .controller('ArbolJerarquicoController', ['$scope', '$http', function($scope, $http) {
      var vm = this,
        data = [];
      vm.dragEnabled = false; //disabling drap & drop

      vm.toggle = function(scope) {
        scope.toggle();
      };
      
      vm.collapseAll = function() {
        $scope.$broadcast('angular-ui-tree:collapse-all');
      };
      
      vm.expandAll = function() {
        $scope.$broadcast('angular-ui-tree:expand-all');
      };

      $http.get('js/arbol-jerarquico-data.json').success(function(data) {
        vm.data = data;
      });

    }]);

  angular.element(mydoc3).ready(function() {
    angular.bootstrap(mydoc3, ['sunatApp']);
  });

})(window.document);
