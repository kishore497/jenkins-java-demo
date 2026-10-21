
pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Source code is checked out from GitHub.'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Unit Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }
        
        
	stage('Archive Artifacts') {
	    steps {
	        archiveArtifacts artifacts: 'target/*.jar',
                         fingerprint: true
	    }
	}

    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }

        success {
            echo 'Java build and tests completed successfully!'
        }

        failure {
            echo 'Build failed. Check the Console Output.'
        }
    }
}
