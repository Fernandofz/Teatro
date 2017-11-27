var estadistica = angular.module('estadistica',[])


.component('estadistica',{
    templateUrl: "app/components/estadistica/view.html",
    controller:function Home($http,$scope,$route){
                var self = this;
                self.nombre='';
                self.refresh = function() {
                    $http({
                    method: 'GET',
                    url: '/obrasteatro?nombre='+self.nombre
                }).then(
                    function Success(response) {

                         console.log(response.data);
                         self.obras = response.data;
                    })
                }



                self.obtenertotalganancias = function() {
                    $http({
                    method: 'GET',
                    url: '/totalingresos'
                }).then(
                    function Success(response) {

                         console.log(response.data);
                         self.totalingresos = response.data;

                    })
                }
                self.obtenertotalegresos = function() {
                    $http({
                    method: 'GET',
                    url: '/totalegresos'
                }).then(
                    function Success(response) {

                         console.log(response.data);
                         self.totalegresos = response.data;

                    })
                }

                self.ingresosxventa = function() {
                    $http({
                    method: 'GET',
                    url: '/ingresosporventa'
                }).then(
                    function Success(response) {

                         console.log(response.data);
                         self.ingresosporventas = response.data;

                    })
                }
                self.ingresoxsubvencion = function() {
                    $http({
                    method: 'GET',
                    url: '/ingresosporsubvenciones'
                }).then(
                    function Success(response) {

                         console.log(response.data);
                         self.ingresosporsubvencion = response.data;

                    })
                }
                self.ingresosxentradas = function() {
                    $http({
                    method: 'GET',
                    url: '/ingresosporentrada'
                }).then(
                    function Success(response) {

                         console.log(response.data);
                         self.ingresosporentradas = response.data;

                    })
                }

                self.ingresosporobras = function() {
                    $http({
                    method: 'GET',
                    url: '/totalingresos/'+self.obraid
                }).then(
                    function Success(response) {

                         console.log(response.data);
                         self.totalingresosporobra = response.data;

                    })
                }

                self.egresosporobras = function() {
                    $http({
                    method: 'GET',
                    url: '/totalegresos/'+self.obraid
                }).then(
                    function Success(response) {

                         console.log(response.data);
                         self.totalegresosporobra = response.data;

                    })
                }

                self.ingresosxventa();
                self.ingresoxsubvencion();
                self.ingresosxentradas();
                self.obtenertotalegresos();
                self.refresh();
                self.obtenertotalganancias();

    },
    controllerAs:'estadistica'
})