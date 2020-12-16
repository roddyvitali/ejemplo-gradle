pipeline {
	agent any

	stages {
		stage('Pipeline'){
			steps {
				script {
					stage('Build & Test Gradle') {
						sh "./gradlew clean build"
						// bat "grandlew clean build"
					}
					stage('Sonar') {
						
						def scannerHome = tool 'sonar-scanner';
						
						withSonarQubeEnv('sonar-server') { // If you have configured more than one global server connection, you can specify its name
							sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"
							// bat "${scannerHome}\\bin\\sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"
						}
					}
					stage('Run & Test') {
						sh 'nohup bash gradlew bootRun &'
          				sleep 5
						sh 'curl -X GET http://localhost:8888/rest/mscovid/test?msg=testing'
					}
					stage('Nexus') {
						nexusPublisher nexusInstanceId: 'nexus', nexusRepositoryId: 'test-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar', filePath: './build/libs/DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]]
					}
				}
				
			}
		}
	}
}