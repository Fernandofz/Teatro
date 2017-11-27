var persona = angular.module('persona',[])


.component('persona',{
    templateUrl: "app/components/persona/view.html",
    controller:function Home($http,$scope){
                var self = this;
                self.nombre = '';
                self.refresh = function() {
                    $http({
                    method: 'GET',
                    url: '/personas?nombre='+self.nombre
                }).then(
                    function Success(response) {

                         console.log(response.data);
                         self.personas = response.data;

                    })
                }

                self.getAbonos = function() {
                    $http({
                    method: 'GET',
                    url: '/tipoabono'
                }).then(
                    function Success(response) {

                         console.log(response.data);
                         self.abonos = response.data;

                    })
                }

                $scope.test = function(){
                 alert();
                }

                self.refresh();
                self.getAbonos();

                $scope.registrar = function(persona) {

                   $http.post('persona', persona).then(function(result) {
                        $scope.msj = "La persona se creo con exito";
                       console.log(result.data);
                       persona.dni = undefined;
                       persona.nombre = undefined;
                       persona.apellido = undefined;
                       persona.direccion = undefined;
                       persona.telefono = undefined;
                       self.refresh();
                   },function errorCallback(response) {
                    });
                }

                    self.registrarAbono = function(tipo,cantidad) {
                        abono = {};
                        abono.dni = self.abonar;
                        abono.fechaCompra = new Date();
                        abono.fechaVencimiento = new Date();
                        abono.fechaVencimiento.setDate(abono.fechaVencimiento.getDate() + $scope.dias);
                        abono.cantidadUsos = cantidad;
                        abono.idTipoAbono = tipo;

                        $http.post('abono', abono).then(function(result) {

                               console.log(result.data);
                               $scope.dias = undefined;

                               self.refresh();
                       },function errorCallback(response) {
                        });

                    }

    },
    controllerAs:'persona'
})