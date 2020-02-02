
//loginService.js
var app = angular.module('myApp');
  
  app.factory('LoginService', function($http) {
    var admin = 'admin';
    var pass = 'password';
    var isAuthenticated = false;
    
    return {
      login : function(username, password) {
    	  var data = 
    			  {
    				    "userId": username,
    				    "pwd": password
    				} 
    	  debugger;
    	  $http.post("/login",data).then(
    		      function successCallback(response) {
    		        console.log("Successfully POST-ed data",response);
    		        return true;
    		      },
    		      function errorCallback(response) {
    		        console.log("POST-ing of data failed");
    		      }
    		    );
    	/*if(username===admin)
    		{
    		   isAuthenticated = username === admin && password === pass;
    	        return isAuthenticated;
    		}
     
        if(username==='child')
        	{
        	  isAuthenticated = username === 'child' && password === pass;
              return isAuthenticated;
        	}*/
      
        
      },
      isAuthenticated : function() {
        return isAuthenticated;
      }
    };
    
  });