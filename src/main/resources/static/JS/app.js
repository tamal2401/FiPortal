(function() {
  var app = angular.module('myApp', ['ui.router']);
  
   app.run(function($rootScope, $location, $state, LoginService) {
     console.clear();
     $rootScope.login = true; 
    if(!LoginService.isAuthenticated()) {
    	
        $state.transitionTo('login');
        $rootScope.login=false;
      }
  });
  
  app.config(['$stateProvider', '$urlRouterProvider', 
  function($stateProvider, $urlRouterProvider) {
    $stateProvider
      .state('login', {
        url : '/login',
        templateUrl : 'pages/login.html',
        controller : 'LoginController'
      })
      .state('admin', {
        url : '/admin',
        templateUrl : 'pages/adminPage.html',
        controller : 'HomeController'
      })
    
      .state('child', {
        url : '/child',
        templateUrl : 'pages/childPage.html',
        controller : 'childController'
      })
      .state('create', {
        url : '/create',
        templateUrl : 'pages/createPage.html',
        controller : 'HomeController'
      });
      
       //$urlRouterProvider.otherwise('/login');
 
  }]);
 
})();