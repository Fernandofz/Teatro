var leitmotiv = angular.module('leitmotiv',['home','ngRoute'])

.config(function($routeProvider,$httpProvider) {
     $routeProvider.when("/", {
            template: '<home></home>'
        })
})