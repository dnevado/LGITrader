#!/bin/bash

ORIGINAL_WORKING_DIR=$(pwd)

if [ "X${1}" != "X" ]
then
	cd ${1}
fi

if [ ! -f .classpath ]
then
	echo "Error: '.classpath' file not found!!!"
	exit 0
fi

if [ ! -d modules ]
then
	echo "Error: 'modules' directory not found!!!"
	exit 0
fi

GRADLE_DIR=/drives/c/Users/DAVIDNEVADO/.gradle

GRADLE_DIR_WIN =C:\Users\DAVIDNEVADO\.gradle


if [ ! -d ${GRADLE_DIR}/caches/modules-2/files-2.1/com.liferay ]
then
	GRADLE_DIR=~/.gradle

	if [ ! -d ${GRADLE_DIR}/caches/modules-2/files-2.1/com.liferay ]
	then
		echo "Error: '.gradle' directory not found or it doesn't contain JARs!!!"
		echo "Try execute 'ant all' before using this script"
		exit 0
	fi

	echo "Info: '.gradle' directory not found in current folder, using from USER_HOME"
fi

if [ ! -f .classpath_backup ]
then
	mv .classpath .classpath_backup
fi

NUMLINES=$(wc -l .classpath_backup | tr -d " " |cut -d"." -f1 )
let NUMLINES=NUMLINES-1

head -n $NUMLINES .classpath_backup | grep -v "path=\"modules" |grep -v "lib/development/jasper.jar" > .classpath
rm .classpath_aux 2> /dev/null

rm java_list_1 java_list_2 java_list_3 java_list_4 2> /dev/null

find modules -type d -name "java" |grep "src/main/java" | sort -u | grep -v "src/main/resources" | grep -v "samples/src" | grep -v "src/main/java/com/liferay" | grep -v "modules/test/" >> java_list_1 &

find modules -type d -name "resources" |egrep "src/main/resources$" | sort -u | grep -v "src/main/resources/META-INF/resources" | grep -v "src/main/java" | grep -v "samples/src" | grep -v "src/main/java/com/liferay" | grep -v "resources/src/main/resources" | grep -v "modules/test/" >> java_list_2 &

find modules -type d -name "service" |grep docroot/WEB-INF/service |grep -v "com/liferay" >> java_list_3 &

find modules -type d -name "src" |grep "docroot/WEB-INF" >> java_list_4 &

wait

for line in $(cat java_list_1 java_list_2 java_list_3 java_list_4)
do
	echo -e "\t<classpathentry kind=\"src\" path=\"$line\"/>" >> .classpath_aux
done

rm java_list_1 java_list_2 java_list_3 java_list_4 2> /dev/null

cat .classpath_aux | sort -u >> .classpath

rm .classpath_aux

rm jar_list_1 jar_list_2 jar_list_3 jar_list_4 jar_list_5 2> /dev/null

for i in $(ls -1d ${GRADLE_DIR}/caches/modules-2/files-2.1/*/*); do (find $i -type f -name "*.jar" |tail -1) & done |grep -v ".gradle/caches/modules-2/files-2.1/com.liferay/com.liferay." |grep -v ".gradle/caches/modules-2/files-2.1/com.liferay.portal" >> jar_list_1 &

find ${GRADLE_DIR}/wrapper/dists -type f -name "gradle*.jar"  |grep LIFERAY-PATCHED >> jar_list_2 &

find modules/apps/opensocial -type f -name "shindig-*.jar" >> jar_list_3 &

find modules/apps/static -type f -name "*.jar" |grep -v sources >> jar_list_4 &

find modules/private/apps/documentum -type f -name "*.jar" |grep -v "com.liferay" >> jar_list_5 &

wait

for line in $(cat jar_list_1 jar_list_2 jar_list_3 jar_list_4 jar_list_5)
do
	jar=$(basename $line)
	if ! grep -q ${jar%-*}.jar .classpath_backup
	then
		echo -e "\t<classpathentry kind=\"lib\" path=\"$line\"/>" >> .classpath_aux
	fi
done

cat .classpath_aux | sort -u >> .classpath

echo "</classpath>" >> .classpath

rm jar_list_1 jar_list_2 jar_list_3 jar_list_4 jar_list_5 2> /dev/null
rm .classpath_aux

cd ${ORIGINAL_WORKING_DIR}

