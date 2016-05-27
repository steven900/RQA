app.controller('indexController',['$scope','$http','$state',function($scope,$http,$state){
	$scope.data = [];
	$http.get('http://114.215.198.27:8080/wxcard/pics.do').success(function(data,state,headers,config){
		$scope.data = data;
	});
	$state.go('music');
	$scope.home=true;
	
	$scope.on_select = function(idx){
		$scope.home=false;
		$scope.people=false;
		$scope.music=false;
		if(idx==1){
			$state.go('music');
			$scope.home=true;
		}else if(idx==2){
			$state.go('home');
			$scope.music=true;
		}else{
			$state.go('usercenter');
			$scope.people=true;
		}
	}
}]);