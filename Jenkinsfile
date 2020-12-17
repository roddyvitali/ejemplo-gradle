def USER_NAME
pipeline {
	agent any

	parameters {
        choice(name: 'BUILD_TOOL', choices: ['maven', 'gradle'], description: 'Select a build tool')
    }

	stages {
		stage('Pipeline'){
			steps {
				script {
					USER_NAME = 'Roddy Vitali'
					stage('Load Build Tool') {
						env.FAILED_STAGE=env.STAGE_NAME
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
			slackSend message: "[${USER_NAME}][${JOB_NAME}][${params.BUILD_TOOL}] Ejecución exitosa."
		}
		failure {
			slackSend message: "[${USER_NAME}][${JOB_NAME}][${params.BUILD_TOOL}] Ejecución fallida en stage. [${FAILED_STAGE}]"
		}
	}
}