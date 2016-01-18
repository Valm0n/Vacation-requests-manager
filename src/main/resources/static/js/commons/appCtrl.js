/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function () {
    angular.module('app').controller("appCtrl", appCtrl);

    appCtrl.$inject = ['$mdSidenav', 'User'];

    function appCtrl($mdSidenav, User) {
        var self = this;
        
        this.toggleSidenav = function(menuId) {
            $mdSidenav(menuId).toggle();
        };

        self.getUserData = function () {
            User.getCurrentUser().then(function (data) {
                self.user = data;
                self.authenticated = User.authenticated;
            }, function () {
                self.user = {};
                self.authenticated = false;
            });
        };

        self.logout = function () {
            self.authenticated = false;
            self.user = {};
            User.logout();
        };

        self.getUserData();
    };
})();