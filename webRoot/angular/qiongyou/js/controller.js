app.controller('indexController',['$scope','$http','$state',function($scope,$http,$state){
	$scope.data = [];
	$http.get('http://114.215.198.27:8080/wxcard/pics.do').success(function(data,state,headers,config){
		$scope.data = data;
	});
	$state.go('music');
	$scope.flag=true;
	$scope.on_select = function(idx){
		
		if(idx==1){
			$state.go('music');
			$scope.flag=true;
		}else{
			$state.go('home');
		}
	}
}]);