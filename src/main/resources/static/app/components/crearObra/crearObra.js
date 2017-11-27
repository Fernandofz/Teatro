var crearObra = angular.module('crearobra',[])


.component('crearobra',{
    templateUrl: "app/components/crearObra/view.html",
    controller:function crearObra($http,$scope,$location){
        var self = this;
        self.nombre = '';
        self.personas = function() {
            $http({
            method: 'GET',
            url: '/personas?nombre='+self.nombre
        }).then(
            function Success(response) {

                 console.log(response.data);
                 self.autores = response.data;

            })
        }

        $scope.postear = function(obra) {
            if (obra.esTercera == undefined){
                obra.esTercera = false;
            }
            obra.dniAutor = self.dni;
            obra.año=obra.anio;
           $http.post('obrasteatro', obra).then(function(result) {
               console.log(result.data);
               $location.path('/obras');
           },function errorCallback(response) {
            });
            }
            $scope.registrar = function(persona) {

               $http.post('persona', persona).then(function(result) {

                    $scope.msj = "El autor se creo con exito";
                   console.log(result.data);
                   persona.dni = undefined;
                   persona.nombre = undefined;
                   persona.apellido = undefined;
                   persona.direccion = undefined;
                   persona.telefono = undefined;
               },function errorCallback(response) {
                });
            }
        self.personas();
    },
    controllerAs:'crearobra'
})