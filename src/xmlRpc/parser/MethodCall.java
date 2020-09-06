package xmlRpc.parser;

public class MethodCall {
	
	private String methodName;
	Params params;

	public MethodCall(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodName() {
		//System.out.println(methodName);
		return methodName;
	}

	public Params getParams() {
		return params;
	}

}
