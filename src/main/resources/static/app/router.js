var leitmotiv = angular.module('leitmotiv',['home','persona','obras','navbarmenu','nosotros','compania','ngRoute','venderentrada','crearobra','organismo','estadistica'])

.config(function($routeProvider,$httpProvider) {
     $routeProvider.when("/", {
            template: '<home></home>'
        })
        .when("/obras", {template: '<obras></obras>'})
        .when("/crearobra", {template: '<crearobra></crearobra>'})
        .when("/personas", {template: '<persona></persona>'})
        .when("/entradas/:rep", {template:'<venderentrada></venderentrada>'})
        .when("/nosotros", {template:'<nosotros></nosotros>'})
        .when("/compañia", {template:'<compania></compania>'})
        .when("/organismo", {template:'<organismo></organismo>'})
        .when("/estadistica", {template:'<estadistica></estadistica>'})
        .otherwise("/");

})