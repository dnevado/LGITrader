SI DESPPLIEGAS TODO EL IBTRADER, LOS CRON PARECEN QUE NO SE VUELVEN A ACTIVAR. ES NECESARIO REINICIAR EL TOMCAT, BORRAR LOS JAR DE LOS SERVICES Y API, 
STATE DE OSGI, TEMP Y WORK. 

1. BORRAR
2. ARRANCAR TWS 
3. DESPLEGAR

/drives/d/Eclipse_Workspace/TraderWorkspace/bundles/tomcat-8.0.32/logs
rm -rf  ../../osgi/state/*
rm -rf  ../work/*
rm -rf  ../temp/*
