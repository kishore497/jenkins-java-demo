
pipeline {
    agent any

    parameters {
        choice(
            name: 'ACTION',
            choices: ['BUILD', 'TEST'],
            description: 'Choose what Jenkins should run'
        )
    }

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
            when {
                expression { params.ACTION == 'BUILD' }
            }
            steps {
                echo "Build number: ${env.BUILD_NUMBER}"
                sh 'mvn clean compile'
            }
        }

        stage('Unit Tests') {
            when {
                expression { params.ACTION == 'TEST' }
            }
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            when {
                expression { params.ACTION == 'BUILD' }
            }
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
