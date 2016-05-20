!(function(mydoc4) {
  'use strict';

  var treeRouterModule = angular.module('sunatApp', ['ngRoute', 'ngAnimate',
       'ui.tree', 'ui.bootstrap'
    ])
    .config(['treeConfig', '$routeProvider',
      function( treeConfig, $routeProvider) {

        //UI Tree  
        treeConfig.defaultCollapsed = true; // collapse nodes by default

        //Provider
        $routeProvider.
        when('/1', {
          templateUrl: 'arbol-accesible-1.html'
        }).
        when('/11', {
          templateUrl: 'arbol-accesible-11.html'
        }).
        when('/111', {
          templateUrl: 'arbol-accesible-111.html'
        }).
        when('/2', {
          templateUrl: 'arbol-accesible-2.html'
        }).when('/21', {
          templateUrl: 'arbol-accesible-21.html'
        }).when('/3', {
          templateUrl: 'arbol-accesible-3.html'
        });
      }
    ])
    .controller('ArbolAccesibleController', ['$scope', '$http', function($scope, $http) {
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

      $http.get('js/arbol-accesible-data.json').success(function(data) {
        vm.data = data;
      });

    }]);

  angular.element(mydoc4).ready(function() {
    angular.bootstrap(mydoc4, ['sunatApp']);
  });

})(window.document);
