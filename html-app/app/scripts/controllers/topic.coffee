'use strict'

angular.module('gratefulplaceApp')
  .controller 'TopicCtrl', ($scope, $routeParams, Topic, Post) ->
    Topic.get id: $routeParams.id, (topic)->
      $scope.topic = topic
      $scope.firstPost = $scope.topic.posts.shift()
            
    $scope.submitReply = ->
      post = new Post($scope.reply)
      post.topic = $scope.topic.id
      post.$save (p)->
        $scope.topic.posts.push(p)
      $scope.reply = {}
      
      