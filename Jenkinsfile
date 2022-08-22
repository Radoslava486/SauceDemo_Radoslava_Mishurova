pipeline {
    agent any

    triggers {
            cron('H 9,21 * * 1-7')
            pollSCM('H */4 * * 1-7')
        }

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    parameters {
     gitParameter branchFilter: 'origin/(.*)', defaultValue: 'master', name: 'BRANCH', type: 'PT_BRANCH'
    }

  stages {
        stage('Run tests') {
            steps {
                // Get some code from a GitHub repository
                git branch: "${params.BRANCH}", url: 'https://github.com/Radoslava486/SauceDemo_Radoslava_Mishurova.git'


               bat "mvn -Dmaven.test.failure.ignore=true clean package"


            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }
        stage('Generate Allure report') {
            steps {
                 script {
                    allure([
                     includeProperties: false,
                     jdk: '',
                     properties: [],
                     reportBuildPolicy: 'ALWAYS',
                     results: [[path: 'target/allure-results']]
                     ])
                }
            }
        }

    }
}