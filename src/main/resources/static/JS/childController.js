
//homeController.js
var app = angular.module('myApp');
 
  app.controller('childController', 
  function($scope, $rootScope, $stateParams, $state, LoginService) {
	  
	  $scope.edit=false;
	 
    $scope.relatedacounts = [

    	{
    		name:"Child 2",
    	    rtype : "Member",
    	    accesslevel : 1
    	},
    	{
    		name:"Child 3",
    	    rtype : "Member",
    	    accesslevel : 1
    	}
    ]
    
    console.log("In a Childcontroller",$scope.relatedacounts);
    $scope.editr = function(){
    	$scope.edit=true;
    	console.log("editR")
    	
    	
    
    }
    
  });