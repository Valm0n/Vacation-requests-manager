/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function () {
    angular.module('app').controller("MyCalendarController", MyCalendarController);

    MyCalendarController.$inject = ['$rootScope', 'Calendar', '$mdDialog'];

    function MyCalendarController($rootScope, Calendar, $mdDialog) {
        var self = this;
        
        self.calendar = Calendar;
        
        self.entries = [];
        
        self.showAdvanced = function(ev, userId) {
            $mdDialog.show({
                controller: DialogController,
                templateUrl: 'partials/modal/newRequest.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                clickOutsideToClose:false,
                fullscreen: false,
                locals: {userId : userId}
                
            })
            .then(function(newEntry) {
                newEntry.approved = false;
                newEntry.startDate = processDate(newEntry.startDate);
                newEntry.endDate = processDate(newEntry.endDate);
                self.calendar.create(newEntry, 
                function(){ self.fetchEntries(); }, 
                function(){ alert('An error occured while saving the new entry. Please try again.\nIf this happens on a regular basis, please contact support.'); });
            }, function() {
                
            });
        };
        
        function processDate(date){
            var output = [date.getFullYear(), date.getMonth()+1, date.getDate()];
            return output;
        }
        
        function DialogController($scope, $mdDialog, userId) {
            $scope.newEntry = {};
            
            $scope.cancel = function() {
              $mdDialog.cancel();
            };
            $scope.saveRequest = function() {
              $mdDialog.hide($scope.newEntry);
            };
            
            $scope.initModal = function(userId){
                $scope.newEntry.userId = userId ? userId : $rootScope.user.details.userId;
            }
            $scope.initModal(userId);
        }
        
        
        
        self.fetchEntries = function(){
            self.calendar.listForUser({id: $rootScope.user.details.userId}, function(data){
                self.entries = data;
            });
        }
        
        self.fetchEntries();
        
        
    };
})();