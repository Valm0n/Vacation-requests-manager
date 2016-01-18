(function () {
    'use strict';

    angular.module('app').service('User', UserService);

    UserService.$inject = ['$resource', '$http', '$location'];

    function UserService($resource, $http, $location) {

        var that = this;
        var UserResource = $resource('user');

        this.details = null;
        this.authenticated = false;

        this.getCurrentUser = function () {
            if (this.details === null) {
                return UserResource.get().$promise
                        .then(function (data) {
                            if (data.userId !== null) {
                                var currentUser = data;
                                currentUser.hasAuthority = function (authority) {
                                    //var hasAuthority = false;
                                    return currentUser.role === authority;
//                                    currentUser.roles.some(function(role){
//                                        if(authority === role){
//                                            hasAuthority = true;
//                                            return true;
//                                        }
//                                    });
//                                    return hasAuthority;
                                };
                                that.details = currentUser;
                                that.authenticated = true;
                                return currentUser;
                            } else {
                                that.details = null;
                                that.authenticated = false;
                                return null;
                            }
                        });
            } else {
                return this.details;
            }
        };

        this.logout = function () {
            this.details = null;
            this.authenticated = false;
            $http.get('/signout');
        };
    }
})();