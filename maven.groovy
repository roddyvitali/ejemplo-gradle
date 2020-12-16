/*
	forma de invocación de método call:
	def ejecucion = load 'maven.groovy'
	ejecucion.call()
*/

def call(){
    stage('Build & Test') {
        sh './mvnw clean package -e'
    }
    stage('Sonar') {
        
        def scannerHome = tool 'sonar-scanner';
        
        withSonarQubeEnv('sonar-server') { 
            sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"
        }
    }
    stage('Run & Test') {
        sh 'nohup bash mvnw spring-boot:run &'
        sleep 5
        sh 'curl -X GET http://localhost:8888/rest/mscovid/test?msg=testing'
    }
    stage('Nexus') {
        nexusPublisher nexusInstanceId: 'nexus', nexusRepositoryId: 'test-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar', filePath: './build/DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]]
    }
}

return this;