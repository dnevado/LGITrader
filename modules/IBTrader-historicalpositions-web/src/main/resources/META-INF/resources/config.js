define('jquery', function () {

	return window.jQuery;

});


Liferay.Loader.addModule(
             {
                     dependencies: [],
                     name: 'Chart',
                     anonymous: true,
                     path: MODULE_PATH + '/js/Chart.bundle.min.js'
             }
     );

