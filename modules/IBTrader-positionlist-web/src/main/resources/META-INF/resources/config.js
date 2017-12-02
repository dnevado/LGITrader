define('jquery', function () {

	return window.jQuery;

});


Liferay.Loader.addModule(
        {
                dependencies: [],
                name: 'dataTables-ES',
                anonymous: true,
                path: MODULE_PATH + '/js/datatables.min.js.es'
        }
);


Liferay.Loader.addModule(
             {
                     dependencies: [],
                     name: 'dataTables',
                     anonymous: true,
                     path: MODULE_PATH + '/js/datatables.min.js'
             }
     );

