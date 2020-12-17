pipeline {
	agent any

	parameters {
        choice(name: 'BUILD_TOOL', choices: ['maven', 'gradle'], description: 'Select a build tool')
    }

	stages {
		stage('Pipeline'){
			steps {
				script {
					stage('Nexus') {
						echo "Select " + params.BUILD_TOOL
						def tool = load "${params.BUILD_TOOL}.groovy"
						tool.call()
					}
				}
			}
		}
	}
	post {
		success {
			slackSend message: "[Roddy Vitali][Nombre Job][${params.BUILD_TOOL}] Ejecución exitosa."
		}
		failure {
			slackSend message: "[Roddy Vitali][Nombre Job][${params.BUILD_TOOL}] Ejecución fallida en stage."
		}
	}
}