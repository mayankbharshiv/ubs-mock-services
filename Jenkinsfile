ciNode = "ppp-slave2"
repoName = "jenkinslibrary"
branchName = "feature/ubs_ci"

node {
    ciNode = env.UBS_SLAVE ?: "master"
}

node(ciNode) {
    checkout scm
        library (identifier: "customlibrary@$branchName",
                changelog: true,
                retriever: modernSCM([$class: 'GitSCMSource',
                                        remote: "https://rahulsahu24556@bitbucket.org/vuclipengineering/" + "$repoName" + ".git",
                                        extensions: [[$class: 'CleanBeforeCheckout']],
                                        branches: [[name: "$branchName"]],
                                        credentialsId: 'BITBUCKET_SINGH']))
        customPipeline.execute(ciNode)
}