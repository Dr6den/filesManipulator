
    // Defining angularjs application.
    var postApp = angular.module('postApp', []);
    // Controller function and passing $http service and $scope var.
    postApp.controller('postController', function($scope, $http) {
      // create a blank object to handle form data.
        $scope.text = {};
      // calling our submit function.
        $scope.submitForm = function() {
        // Posting data to php file
        $http({
          method  : 'POST',
          url     : 'http://localhost:8080/filesManipulator/rest/textFilesController',
          data    : angular.toJson($scope.text),//'{"text":"' + $scope.text + '"}', //forms user object
          headers : {'Content-Type': 'application/json'} 
         })
          .success(function(data) {
            if (data.errors) {
              // Showing errors.
              $scope.errorName = data.errors.name;
              $scope.errorUserName = data.errors.username;
              $scope.errorEmail = data.errors.email;
            } else {
              $scope.message = data.message;
            }
          });
        };
    });



