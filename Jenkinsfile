pipeline {
    agent any

    stages {

        stage('Infra details') {
            steps {
                echo '=============Infra details starts ======================='
                bat 'git version'
                bat 'mvn -v'
                bat 'docker -v'
                bat 'kubectl version --short'
               echo '=============Infra details ends ======================='
            }
        }

        stage('Build Artifact -Maven') {
            steps {
               echo '=============Clean package Starts======================='
                bat 'mvn clean package -DskipTests=true'
                echo '=============Clean package Ends======================='
            }
        }


         stage('Unit Tests') {
            steps {
               echo '=============Unit Tests Starts ======================'
                bat 'mvn test'
                echo '============Unit Tests Ends ======================='
            }
         }

    }
}
