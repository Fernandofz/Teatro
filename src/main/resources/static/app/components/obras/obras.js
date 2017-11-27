var obras = angular.module('obras',[])


.component('obras',{
    templateUrl: "app/components/obras/view.html",
    controller:function Home($http,$scope){
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

        self.obtenerValidaciones = function() {
            $http({
            method: 'GET',
            url: '/verificarobrasteatro'
        }).then(
            function Success(response) {
                 console.log(response.data);
                 self.validaciones = response.data;
            })
        }
        self.check = function(id){
            for (var i = 0; i < self.validaciones.length; i++) {
                    if (self.validaciones[i].id_obra_teatro === id) {
                        return true;
                    }
            }
            return false;
        }

        self.representar = function() {
                obrarep = {};
                obrarep.idObraTeatro = self.idselected;
                obrarep.fechaAnuncio = new Date();
                obrarep.fechaObra = self.fecharep;
                obrarep.precioCompra = 0;
                obrarep.idCompañia = 1;
               $http.post('representar', obrarep).then(function(result) {
                   console.log(result.data);
                   self.idrepre = result.data.idRepresentacion;
                   esp1 = {};
                   esp1.tipo = self.esp1;
                   esp1.idRepresentacion =  self.idrepre;
                   esp1.precio = self.esp1p;
                   esp2 = {};
                   esp2.tipo = self.esp2;
                   esp2.idRepresentacion =  self.idrepre;
                   esp2.precio = self.esp2p;
                   esp3 = {};
                   esp3.tipo = self.esp3;
                   esp3.idRepresentacion =  self.idrepre;
                   esp3.precio = self.esp3p;

                 $http.post('espectador', esp1).then(function(result) {
                     console.log(result.data);

                 },function errorCallback(response) {
                  });
                $http.post('espectador', esp2).then(function(result) {
                    console.log(result.data);

                },function errorCallback(response) {
                 });
               $http.post('espectador', esp3).then(function(result) {
                   console.log(result.data);

               },function errorCallback(response) {
                });
               $http.post('/butacas/'+self.idrepre+'/'+self.cantb).then(function(result) {
                   console.log(result.data);

               },function errorCallback(response) {
                });

           },function errorCallback(response) {
            });


        }

        self.obtenerValidaciones();
        self.refresh();




    },
    controllerAs:'obras'
})