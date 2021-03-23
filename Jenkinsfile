pipeline {
    agent{ label 'bash'}

    stages {
        stage('Test') {
          steps {
          withMaven(maven: 'mvn') {
            sh 'mvn -Dproperties=CI_GRID.properties test '
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