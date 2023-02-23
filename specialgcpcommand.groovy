/* groovylint-disable-next-line CompileStatic */
pipeline {
  agent any

    stages {
        stage('Plan') {
      steps {
        container('gcloud') {
          sh 'gcloud config set project qwiklabs-gcp-03-8348c6b63209'
          sh 'gcloud version'
        }
      }
    }
  }
}
