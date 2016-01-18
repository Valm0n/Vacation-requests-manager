/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function () {
    angular.module('app').controller("MyCalendarController", MyCalendarController);

    MyCalendarController.$inject = ['$rootScope', 'Calendar'];

    function MyCalendarController($rootScope, Calendar) {
        var self = this;
        
        self.calendar = Calendar;
        
        self.entries = [];
        
        self.fetchEntries = function(){
            self.calendar.listForUser({id: $rootScope.user.details.userId}, function(data){
                self.entries = data;
            });
        }
    };
})();