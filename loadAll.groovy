import com.neuronrobotics.bowlerstudio.scripting.ScriptingEngine
import com.neuronrobotics.sdk.addons.kinematics.INewLinkProvider
import com.neuronrobotics.sdk.addons.kinematics.LinkFactory

HashMap<String, HashMap<String, Object>> database = ScriptingEngine.gitScriptRun(
											"https://github.com/CommonWealthRobotics/DeviceProviders.git",
											"devices.json",null)
for(String key:database.keySet()) {
	HashMap<String, Object> data = database.get(key)
	try{
		ScriptingEngine.pull(data.scriptGit);
		def provider = ScriptingEngine.gitScriptRun(
												data.scriptGit,
												data.scriptFile,[key])
		if(INewLinkProvider.class.isInstance(provider)) {
			println "Adding a link provider "+key+": "+data.description
			LinkFactory.addLinkProvider(key, provider)
		}
	}catch (Throwable t){
		t.printStackTrace()
		//BowlerStudio.printStackTrace(t)
	}
}										