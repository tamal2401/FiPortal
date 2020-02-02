//loginController-js 
 var app = angular.module('myApp');
 app.controller('LoginController', function($scope,$http, $rootScope, $stateParams, $state, LoginService) {
    $rootScope.title = "AngularJS Login Sample";
    
    $scope.formSubmit = function() {

    	  var data = 
		  {
			    "userId": $scope.username,
			    "pwd": $scope.password
			} 
        $http.post("/login",data).then(
	      function successCallback(output) {
	        console.log("Successfully POST-ed data",output.data.role);
	        if(output.data.role=="admin")
        	{
        	$rootScope.login=false;
        	 $state.transitionTo('admin');
        	}
        else if(output.data.role=="child")
        	{
        	$rootScope.login=false;
        	$state.transitionTo('child');
        	}
	      },
	      function errorCallback(response) {
	        console.log("POST-ing of data failed");
	      }
	    );
  
  
        $rootScope.userName = $scope.username;
        $scope.error = '';
        $scope.username = '';
        $scope.password = '';
        if($rootScope.userName=="admin")
        	{
        	$rootScope.login=false;
        	 $state.transitionTo('admin');
        	}
        else if($rootScope.userName=="child")
        	{
        	$rootScope.login=false;
        	$state.transitionTo('child');
        	}
    
    };    
  });