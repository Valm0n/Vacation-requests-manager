angular.module("app", ['ngMaterial', 'ngResource', 'ui.router', 'md.data.table'])
.run(['$rootScope', '$state', '$stateParams', 
                function($rootScope, $state, $stateParams){
                    $rootScope.state = $state;
                    $rootScope.stateParams = $stateParams;
                }
            ]
        )
    .config(['$stateProvider', '$urlRouterProvider', '$httpProvider',
                function($stateProvider, $urlRouterProvider, $httpProvider) {
                    
                    $urlRouterProvider.otherwise('/');
                    
                    $stateProvider
                        .state('app', {
                            abstract: true,
                            template: '<ui-view/>'
                    })
                        .state('app.home', {
                            url: "/",
                            templateUrl: 'partials/home.html',
                            controller: 'homeCtrl',
                            controllerAs: 'home'
                    })
                            .state('app.myCalendar', {
                            url: "/myCalendar",
                            templateUrl: 'partials/myCalendar.html',
                            controller: 'MyCalendarController',
                            controllerAs: 'ctrl'
                    });
                        
                        
                    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
                }
            ]
        );