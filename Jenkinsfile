
pipeline {
    agent any

    parameters {
        choice(
            name: 'ACTION',
            choices: ['BUILD', 'TEST', 'DOCKER'],
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

    stage('Docker Image') {
            when {
                expression { params.ACTION == 'DOCKER' }
            }
            steps {
                withEnv(['DOCKER_HOST=tcp://jenkins-docker:2375']) {
                    sh 'mvn clean package'
                    sh 'docker build -t jenkins-java-demo:latest .'
                    sh 'docker image ls jenkins-java-demo'
                }
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
