/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


angular.module('app')
    .controller("homeCtrl", ['$rootScope', 'User', function ($rootScope, User) {
        var self = this;
        $rootScope.user = User;
        self.user = $rootScope.user;
    }]);        