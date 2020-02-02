
//homeController.js
var app = angular.module('myApp');
 
  app.controller('HomeController', 
  function($scope, $rootScope, $stateParams, $state, LoginService) {
	  
	  $scope.edit=false;
$rootScope.login=true;
	 
    $scope.relatedacounts = [
    	{
    		name:"Child 1",
    	    rtype : "Member",
    	    accesslevel : 1
    	},
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
    
    console.log("In a homecontroller",$scope.relatedacounts);
    $scope.editr = function(){
    	$scope.edit=true;
    	console.log("editR")
    	
    	
    
    }
    
    $scope.saver = function(){
    	$scope.edit=false;
    	console.log("saver")
    	alert("data saved successfully");

    }
    
    $scope.send = function(email){
    	console.log("email");
    	$('#myModal').modal('hide');
    	alert("Registration form  send to an email successfully");

    }
    
    
  });