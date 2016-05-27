var app =   angular.module('myApp', ['ionic']);
		
app.config(function($stateProvider,$urlRouterProvider) {
	$stateProvider
	.state("music", {
		templateUrl: "music.html",
		controller:'indexController'
	})
	 .state("home", {
        templateUrl: "home.html"
    })
     .state("usercenter",{
    	templateUrl:"usercenter.html"
    })
    .state("setting",{
    	templateUrl:"setting.html"
    })
})