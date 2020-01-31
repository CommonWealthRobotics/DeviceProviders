HashMap<String, HashMap<String, Object>> database = ScriptingEngine.gitScriptRun(
											"https://github.com/CommonWealthRobotics/DeviceProviders.git",
											"devices.json",null)
println 	database										