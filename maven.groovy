/*
	forma de invocación de método call:
	def ejecucion = load 'maven.groovy'
	ejecucion.call()
*/

def call(){
  
    stage(){
        sh "./mvwn clean build" 
    }

}

return this;