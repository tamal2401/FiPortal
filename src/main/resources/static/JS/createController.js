
//homeController.js
var app = angular.module('myApp');
 
  app.controller('createController', 
  function($scope, $rootScope, $stateParams, $state, LoginService) {
	  
	  $scope.register = function(user){
		  console.log(user);
		  
	  }
	  
  });