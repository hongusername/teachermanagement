app.service('surveyTypeService', function($http) {
    this.findAll = function() {
        return $http.get('/survey_type/getSurveyTypeList');
    }
    this.findById = function (id) {
        return $http.post('/survey_type/getSurveyTypeById?id='+id);
    }
    
    this.add = function (entity) {
        return $http.post('/survey_content/addSurveyContent',entity);
    }
    this.importExcel = function (surveyType) {
        return $http.post('/survey_type/getSurveyTypeById?id='+id);
    }
});