var home = angular.module('home',[])


.component('home',{
    templateUrl: "app/components/home/view.html",
    controller:function Home($http,$scope){
    var self = this;
    $scope.signUp = function(user) {
       $http.post('persona', user).then(function(result) {
           console.log(result.data);
       },function errorCallback(response) {
             $scope.errorR = "Ya existe nombre de usuario";
        });
   }


    },
    controllerAs:'home'
})