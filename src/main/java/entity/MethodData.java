package entity;

public class MethodData {

    private String projectName;

    private String fileName;

    private String accessType;

    private int sizeInSloc;

    private int ifBlockCount;

    private int forBlockCount;

    public int getSizeInSloc() {
        return sizeInSloc;
    }

    public void setSizeInSloc(int sizeInSloc) {
        this.sizeInSloc = sizeInSloc;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String type) {
        this.accessType = accessType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
}
