var compania = angular.module('compania',[])


.component('compania',{
    templateUrl: "app/components/compañia/view.html",
    controller:function Home($http,$scope){
    var self = this;
        self.nombre='';
        self.refresh = function() {
            $http({
            method: 'GET',
            url: '/compañias?nombre='+self.nombre
        }).then(
            function Success(response) {

                 console.log(response.data);
                 self.companias = response.data;

            })
        }
        self.refresh();



    },
    controllerAs:'compania'
})