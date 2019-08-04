package entity;

public class MethodData {

    private String projectName;

    private String name;

    private String type;

    private int sizeInSloc;

    private int ifBlockCount;

    private int forBlockCount;


    public int getSizeInSloc() {
        return sizeInSloc;
    }

    public void setSizeInSloc(int sizeInSloc) {
        this.sizeInSloc = sizeInSloc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
