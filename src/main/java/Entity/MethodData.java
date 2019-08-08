package Entity;

public class MethodData {

    private String methodName;

    private String projectName;

    private String accessType;

    private int sizeInSloc;

    private int ifBlockCount;

    private int forBlockCount;

    private String getterOrSetter;

    public int getSizeInSloc() {
        return sizeInSloc;
    }

    public void setSizeInSloc(int sizeInSloc) {
        this.sizeInSloc = sizeInSloc;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public int getIfBlockCount() {
        return ifBlockCount;
    }

    public void setIfBlockCount(int ifBlockCount) {
        this.ifBlockCount = ifBlockCount;
    }

    public int getForBlockCount() {
        return forBlockCount;
    }

    public void setForBlockCount(int forBlockCount) {
        this.forBlockCount = forBlockCount;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getGetterOrSetter() {
        return getterOrSetter;
    }

    public void setGetterOrSetter(String getterOrSetter) {
        this.getterOrSetter = getterOrSetter;
    }

    public MethodData(String methodName, String projectName, String accessType, int sizeInSloc,
                      int ifBlockCount, int forBlockCount, String getterOrSetter) {
        this.methodName = methodName;
        this.projectName = projectName;
        this.accessType = accessType;
        this.sizeInSloc = sizeInSloc;
        this.ifBlockCount = ifBlockCount;
        this.forBlockCount = forBlockCount;
        this.getterOrSetter = getterOrSetter;
    }

    @Override
    public String toString() {
        return "MethodData{" +
                "methodName='" + methodName + '\'' +
                ", projectName='" + projectName + '\'' +
                ", accessType='" + accessType + '\'' +
                ", sizeInSloc=" + sizeInSloc +
                ", ifBlockCount=" + ifBlockCount +
                ", forBlockCount=" + forBlockCount +
                ", getterOrSetter='" + getterOrSetter + '\'' +
                '}';
    }
}
