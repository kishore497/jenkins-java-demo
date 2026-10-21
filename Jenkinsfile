
pipeline {
    agent any

    stages {
        stage('Welcome') {
            steps {
                echo 'Hello from GitHub!'
                echo 'Jenkins is reading my Jenkinsfile.'
            }
        }

        stage('Build Simulation') {
            steps {
                echo 'Starting build...'
                echo 'Build completed successfully!'
            }
        }

        stage('Finish') {
            steps {
                echo 'Jenkins Lesson 4 completed!'
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }

        failure {
            echo 'Pipeline failed. Check the console output.'
        }
    }
}
