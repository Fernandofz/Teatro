var organismo = angular.module('organismo',[])


.component('organismo',{
    templateUrl: "app/components/organismo/view.html",
    controller:function Home($http,$scope){
        var self = this;
        self.nombre='';
        self.idorganismo='';
        self.refresh = function() {
            $http({
            method: 'GET',
            url: '/organismos?nombre='+self.nombre
        }).then(
            function Success(response) {

                 console.log(response.data);
                 self.organismos = response.data;

            })
        }


        self.refresh();



    },
    controllerAs:'organismo'
})