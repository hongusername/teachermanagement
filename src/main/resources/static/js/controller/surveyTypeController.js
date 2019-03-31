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
        surveyTypeService.findById($scope.surveyType).success(function (response) {
            $scope.entity = [];
            $scope.options = [];

            var surveyType = response;
            $scope.options = JSON.parse(surveyType.options);
            $scope.addTableRow();
        });
    }

    /* 保存问卷 */
    $scope.save = function () {
        surveyTypeService.add($scope.entity).success(function (response) {
            if (response.success) {
                layer.msg(response.message);
                location.reload();
            } else {
                layer.msg("保存失败");
            }
        });
    }

    $scope.options = [];
    $scope.surveyType = 1;
    $scope.entity = [
        {
            content: '',
            surveyType: 1,
            options: []
        }
    ]
    // 增加一行
    $scope.addTableRow = function () {
        $scope.entity.push({
            content: '',
            surveyType: $scope.surveyType,
            options: JSON.parse(JSON.stringify($scope.options))
        })
    }
    // 增加一行
    $scope.addTableRowWithData = function (content, surveyType, options) {
        $scope.entity.push({
            content: content,
            surveyType: surveyType,
            options: JSON.parse(JSON.stringify(options))
        })
    }
    // 删除规格选项行
    $scope.delTableRow = function (index) {
        $scope.entity.splice(index, 1);
    }
});