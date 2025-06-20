public abstract class Payment implements Payable {
    protected String methodName;

    public String getMethodName() {
        return methodName;
    }
}