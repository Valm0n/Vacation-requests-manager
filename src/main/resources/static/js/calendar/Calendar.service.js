(function () {
    'use strict';

    angular.module('app').service('Calendar', Calendar);

    Calendar.$inject = ['$resource', '$http', '$location'];

    function Calendar($resource) {

        //var that = this;
        var Calendar = $resource('calendar', {}, {
            getEntry:    {method: 'GET',    url:'calendar/:id',                  params:{id:'@id'} },
            listForUser: {method: 'GET',    url:'calendar/user/:id',             params:{id:'@id'}, isArray: true },
            validate:    {method: 'GET',    url:'calendar/:id/validate/:accept', params:{id:'@id',accept:'@accept'} },
            reject:      {method: 'DELETE', url:'calendar/:id/reject',           params: {id: '@id'} }
        });
        
        return Calendar;

        
    }
})();