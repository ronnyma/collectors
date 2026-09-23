pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
        jdk 'JDK17'
    }

    options {
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '20'))
        disableConcurrentBuilds()
    }

    stages {
        stage('Build, Unit & Integration Test') {
            steps {
                // 'verify' runs compile -> test (surefire, excludes **/*IT.java per pom)
                // -> package -> integration-test/verify (failsafe, **/*IT.java)
                sh 'mvn -B -ntp clean verify'
            }
            post {
                always {
                    junit testResults: 'target/surefire-reports/*.xml,target/failsafe-reports/*.xml', allowEmptyResults: true
                }
            }
        }

        stage('Package Artifacts') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Mutation Testing (PIT)') {
            steps {
                // Non-blocking: mutation coverage regressions mark the build UNSTABLE, not FAILED
                catchError(buildResult: 'UNSTABLE', stageResult: 'UNSTABLE') {
                    sh 'mvn -B -ntp org.pitest:pitest-maven:mutationCoverage'
                }
            }
            post {
                always {
                    archiveArtifacts artifacts: 'target/pit-reports/**', allowEmptyArchive: true
                }
            }
        }

        stage('Deploy to Nexus') {
            when {
                anyOf {
                    branch 'master'
                    expression { return env.GIT_BRANCH == 'origin/master' }
                }
            }
            steps {
                // "nexus-maven-settings" is a Managed Maven settings.xml (Config File Provider plugin)
                // defining <server> credentials for the "internal-releases" / "internal-snapshots" ids
                // referenced in this project's pom.xml distributionManagement.
                configFileProvider([configFile(fileId: 'nexus-maven-settings', variable: 'MAVEN_SETTINGS')]) {
                    sh 'mvn -B -ntp -s $MAVEN_SETTINGS -DskipTests deploy'
                }
            }
        }
    }

    post {
        cleanup {
            cleanWs()
        }
    }
}
