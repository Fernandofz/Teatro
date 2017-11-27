var venderentrada = angular.module('venderentrada',[])


.component('venderentrada',{
    templateUrl: "app/components/venderEntrada/view.html",
    controller:function Home($http,$scope,$route){
                var self = this;
                self.nombre='';
                self.nombreindividual='';
                self.refresh = function() {
                    $http({
                    method: 'GET',
                    url: '/entradas/'+$route.current.params.rep+'?nombre='+self.nombre
                }).then(
                    function Success(response) {

                         console.log(response.data);
                         self.entradas = response.data;

                    })
                }
                $scope.test = function(id){
                 alert(id);
                }
                self.refresh();
                self.nombreabonado = '';
                self.personas = function() {
                    $http({
                    method: 'GET',
                    url: '/personasabonadas?nombre='+self.nombreabonado
                }).then(
                    function Success(response) {

                         console.log(response.data);
                         self.abonados = response.data;

                    })
                }

                self.obtenerespect = function() {
                    $http({
                    method: 'GET',
                    url: '/espectadores/'+$route.current.params.rep
                }).then(
                    function Success(response) {

                         console.log(response.data);
                         self.espectadores = response.data;

                    })
                }

                self.abonar = function (dni){
                   abon = {};
                   abon.idRepresentacion = parseInt($route.current.params.rep);
                   abon.butaca = self.butaca;
                   abon.dni = self.personaabonada;
                   abon.numeroAbono = self.nabono;
                    abon.espectador = parseInt(self.tipoespect);

                       console.log(abon);
                    $http.put('abonarentrada', abon).then(function(result) {

                           console.log(result.data);
                            self.refresh();
                   },function errorCallback(response) {
                    });

                }

                 self.vender = function (dni){
                       abon = {};
                       abon.idRepresentacion = parseInt($route.current.params.rep);
                       abon.butaca = self.butaca;
                       abon.dniIndividual = self.personaabonada;
                        abon.espectador = parseInt(self.tipoespect);
                        console.log(abon);
                        $http.put('ventaentrada', abon).then(function(result) {

                               console.log(result.data);
                                self.refresh();
                       },function errorCallback(response) {
                        });

                }

                 self.venderI = function (){
                       abon = {};
                       abon.idRepresentacion = parseInt($route.current.params.rep);
                       abon.butaca = self.butaca;
                        abon.espectador = parseInt(self.tipoespect);
                        console.log(abon);
                        $http.put('ventaentrada', abon).then(function(result) {

                               console.log(result.data);
                                self.refresh();
                       },function errorCallback(response) {
                        });

                }

                self.obtenerComp = function() {
                    $http({
                    method: 'GET',
                    url: '/personas?nombre='+self.nombreindividual
                }).then(
                    function Success(response) {

                         console.log(response.data);
                         self.individuales = response.data;

                    })
                }
                self.personas();
                self.obtenerComp();
                self.obtenerespect();
    },
    controllerAs:'venderentrada'
})