pipeline {
	agent any

	parameters {
        choice(name: 'BUILD_TOOL', choices: ['Maven', 'Gradle'], description: 'Select a build tool')
    }

	stages {
		stage('Pipeline'){
			steps {
				script {
					stage('Nexus') {
						echo "Select " + params.BUILD_TOOL
					}
					if (params.BUILD_TOOL == 'Gradle' ) {
						def tool = load 'gradle.groovy'
						tool.call()
					}
					else if (params.BUILD_TOOL == 'Maven' ) {
						def tool = load 'maven.groovy'
						tool.call()
					}
				}
				
			}
		}
	}
}