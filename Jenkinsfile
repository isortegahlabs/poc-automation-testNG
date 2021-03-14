pipeline {
    agent{ label 'bash'}

    stages {
        stage('Test') {
          steps {
          withMaven(maven: 'mvn') {
            sh 'mvn -P drivers test'
            }
          }
        }
        stage('Clean up') {
          steps {
            deleteDir()
          }
        }
      }
}