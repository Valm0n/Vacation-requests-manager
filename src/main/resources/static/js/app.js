angular.module("app", ['ngMaterial', 'ngResource', 'ui.router', 'md.data.table'])
.run(['$rootScope', '$state', '$stateParams', 
                function($rootScope, $state, $stateParams){
                    $rootScope.state = $state;
                    $rootScope.stateParams = $stateParams;
                }
            ]
        )
    .config(['$stateProvider', '$urlRouterProvider', '$httpProvider', '$rootScope',
                function($stateProvider, $urlRouterProvider, $httpProvider, $rootScope) {
                    
                    $urlRouterProvider.otherwise('/');
                    
                    function isAllowed(expectedAccess){
                        if($rootScope.user.authenticated){
                            if($rootScope.user.hasAuthority(expectedAccess)){
                                return true;
                            }
                        }
                        return false;
                    }
                    
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
                            controllerAs: 'ctrl',
                            onEnter: function($state){
                                if(!isAllowed('User')){
                                    $state.go('app.home');
                                }
                            }
                    });
                        
                        
                    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
                }
            ]
        );