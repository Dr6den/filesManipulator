 var App2 = angular.module('main', ['ngTable']);
    

    // Thank you PeteBD
    //     http://stackoverflow.com/a/12513509/667301
    // Set up a controller to get json tasks...
    App2.factory('getFiles', function($http) {
        return {
            getJson: function() {
                var url = 'http://localhost:8080/filesManipulator/rest/textFilesController';
                return $http.get(url);
            }
        }
    });
    App2.controller('DemoCtrl', function($scope, getFiles, $filter, 
        ngTableParams) {

        var data = [];
        getFiles.getJson().then(function(data) {
            $scope.data = data.data;
            data = $scope.data;  
        // Set up task table parameters
        $scope.tableParams = new ngTableParams({
                page: 1,            // show first page
                count: 10,          // count per page
                sorting: {
                    name: 'asc'     // initial sorting
                }
            }, {
                total: data.length, // length of data
                getData: function($defer, params) {
                    // use build-in angular filter
                    var orderedData = params.sorting() ?
                                        $filter('orderBy')(data, params.orderBy()) :
                                        data;

                    $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
                }
            });
        });
        
    });
    