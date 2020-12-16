/*
	forma de invocación de método call:
	def ejecucion = load 'grandle.groovy'
	ejecucion.call()
*/

def call(){
  
    stage(){
        sh "./gradlew clean build" 
    }

}

return this;