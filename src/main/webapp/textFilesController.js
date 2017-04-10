var app = angular.module('main', ['ngTable']).
        controller('DemoCtrl', function($scope, $filter, ngTableParams) {
            var data = [{name: "Moroni", path: "a", numberOfLines: 50, textLength: 50},
                        {name: "Tiancum", path: "a", numberOfLines: 43, textLength: 50},
                        {name: "Jacob", path: "a", numberOfLines: 27, textLength: 50},
                        {name: "Nephi", path: "a", numberOfLines: 29, textLength: 50},
                        {name: "Enos", path: "a", numberOfLines: 34, textLength: 50},
                        {name: "Tiancum", path: "a", numberOfLines: 43, textLength: 50},
                        {name: "Jacob", path: "a", numberOfLines: 27, textLength: 50},
                        {name: "Nephi", path: "a", numberOfLines: 29, textLength: 50},
                        {name: "Enos", path: "a", numberOfLines: 34, textLength: 50},
                        {name: "Tiancum", path: "a", numberOfLines: 43, textLength: 50},
                        {name: "Jacob", path: "a", numberOfLines: 27, textLength: 50},
                        {name: "Nephi", path: "a", numberOfLines: 29, textLength: 50},
                        {name: "Enos", path: "a", numberOfLines: 34, textLength: 50},
                        {name: "Tiancum", path: "a", numberOfLines: 43, textLength: 50},
                        {name: "Jacob", path: "a", numberOfLines: 27, textLength: 50},
                        {name: "Nephi", path: "a", numberOfLines: 29, textLength: 50},
                        {name: "Enos", path: "a", numberOfLines: 34, textLength: 50}];

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