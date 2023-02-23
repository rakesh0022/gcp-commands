pipeline {
  agent any

    stages {
        stage('Plan') {
      steps {
        container('gcloud') {
          sh "gcloud config set project ${GCP_PROJECT}"
          sh "gcloud storage rm -r gs://${BUCKET_NAME} "
        }
      }
        }
        stage('Confirm') {
      steps {
        script {
          def userInput = input(
                        message: 'Do you want to proceed with the GCP command execution?',
                        parameters: [booleanParam(defaultValue: false, description: 'Proceed with execution?')]
                    )
          if (!userInput) {
            error('GCP command execution has been cancelled')
          }
        }
      }
        }
        stage('Apply') {
      steps {
        container('gcloud') {
          sh "gcloud config set project ${GCP_PROJECT}"
          sh "gcloud storage rm -r gs://${BUCKET_NAME}"
        }
      }
        }
    }
    post {
        always {
      cleanWs()
        }
    }
}

