pipeline {
    agent{ label 'bash'}

    stages {
        stage('Test') {
          steps {
          withMaven(maven: 'mvn') {
            sh 'mvn -Dproperties=CI.properties -P drivers test '
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