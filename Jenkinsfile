pipeline {
    agent any
   
    stages {
        stage('Build') {
            steps {
                echo 'Build the project'
            }
			}
			
			stage('Run Uts') {
            steps {
                echo 'run utnit test'
            }
			}
			stage('Deploy to dev') {
            steps {
                echo 'deploying to dev env'
            }
			}
			stage('Deploy to qa') {
            steps {
                echo 'deploying to qa env'
            }
			}
			stage('Run Regression automation test') {
            steps {
                echo 'running automation regression suite'
            }
			}
			
			stage('Deploy to stage') {
            steps {
                echo 'deploying to stage env'
            }
			}
			stage('run sanity automation test cases') {
            steps {
                echo 'running sanity automation test cases'
            }
			}
			stage('Deploy to prod') {
            steps {
                echo 'deploying too prod env'
            }
			}
        }
    }
