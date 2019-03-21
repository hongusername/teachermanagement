app.controller('surveyTypeController', function ($scope, $controller, surveyTypeService) {

    $controller('baseController', {
        $scope: $scope
    });

    /* 问卷类型列表 */
    $scope.findAll = function () {
        surveyTypeService.findAll().success(function (response) {
            $scope.surveyContentList = response;
        });
    }

    /* 获取问卷类型 */
    $scope.findById = function () {
        surveyTypeService.findById($scope.entity.surveyType).success(function (response) {
            var surveyType = response;
            $scope.options = JSON.parse(surveyType.options);
        });
    }
    /* 保存问卷 */
    $scope.save = function () {
        surveyTypeService.add($scope.entity).success(function (response) {
            if (response.success) {
                alert(response.message);
            } else {
                alert("添加失败！");
            }
        });
    }

    $scope.options=[];
    $scope.entity = {surveyType: 1, surveyContentList: [{content:'',options: JSON.parse(JSON.stringify($scope.options))}]}
    // 增加一行
    $scope.addTableRow = function () {
        $scope.entity.surveyContentList.push({content:'',options: JSON.parse(JSON.stringify($scope.options))})
    }
    // 删除规格选项行
    $scope.delTableRow = function (index) {
        $scope.entity.surveyContentList.splice(index, 1);
    }
});