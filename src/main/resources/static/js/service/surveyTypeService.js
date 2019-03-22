app.service('surveyTypeService', function($http) {
    this.findAll = function() {
        return $http.get('http://localhost:8080/survey_type/getSurveyTypeList');
    }
    this.findById = function (id) {
        return $http.post('http://localhost:8080/survey_type/getSurveyTypeById?id='+id);
    }
    
    this.add = function (entity) {
        return $http.post('http://localhost:8080/survey_content/addSurveyContent',entity);
    }
});