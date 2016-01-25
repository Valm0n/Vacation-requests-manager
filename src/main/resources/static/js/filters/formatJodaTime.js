/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function(){
    angular.module('app').filter('formatJodaTime', formatJodaTime);
    
    function formatJodaTime(){
        return function(input){
            if(Array.isArray(input) && input.length === 3){
                return input[2] + "/" + input[1] + "/" + input[0];
            }
        };
    }
})();