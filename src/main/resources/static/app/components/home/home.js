var home = angular.module('home',[])


.component('home',{
    templateUrl: "app/components/home/view.html",
    controller:function Home($http,$scope){
    var self = this;
        self.refresh = function() {
            $http({
            method: 'GET',
            url: '/espectaculos'
        }).then(
            function Success(response) {

                 console.log(response.data);
                 self.espectaculos = response.data;
                 for(x=0;x < self.espectaculos.length;x++){
                    self.espectaculos[x].fecha_obra=(new Date(self.espectaculos[x].fecha_obra)).toString();
                 }

            })
        }
        self.refresh();



    },
    controllerAs:'home'
})