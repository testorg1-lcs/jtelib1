void call(){
  println "Task from libsimple"

  println scm.dump()
  
  echo "ON NODE"
  node() {
    /*
    //scm.extensions << [$class: 'RelativeTargetDirectory',  relativeTargetDir: 'cd1']
    extensions = [[$class: 'RelativeTargetDirectory',  relativeTargetDir: 'cd1']]
    
    def dl = new hudson.util.DescribableList(hudson.model.Saveable.NOOP,extensions);
    println "DL ${dl.dump()}"
    scm.extensions.addAll(dl)
    println "After add"
    println "SCM.extensions : ${scm.extensions}"
    
    println scm.extensions.getClass()
    println scm.extensions.dump()
    echo "----"
    //scm.relativeTargetDir="cd2"
    println scm.dump()
    
    dl = null
    */
    
    
    def exts = []
    for ( i in scm.extensions ) exts << i
    exts << [$class: 'RelativeTargetDirectory',  relativeTargetDir: 'cd1']
    //println "EXTS ${exts.dump()}"
    
    //checkout scm  
    checkout([
         $class: 'GitSCM',
         branches: scm.branches,
         doGenerateSubmoduleConfigurations: scm.doGenerateSubmoduleConfigurations,
         extensions: [scm.extensions[0],scm.extensions[1],[$class: 'RelativeTargetDirectory',  relativeTargetDir: 'cd1']],
         userRemoteConfigs: scm.userRemoteConfigs
    ])
    exts = null
    
    bat "dir"
  }
  
  binding.variables.each {k,v -> println "$k = $v"}
  
  echo "keyword version = ${version}"
  
  //echo "${VAR_1}"
  //echo "${VAR_2}"
  
}
