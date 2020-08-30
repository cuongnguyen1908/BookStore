package dtos;


public class CodeEditErrorDTO {
    private String codeLenghtError;
    private String percentError;
    private String codeExist;

    public String getCodeExist() {
        return codeExist;
    }

    public void setCodeExist(String codeExist) {
        this.codeExist = codeExist;
    }

    public String getPercentError() {
        return percentError;
    }

    public void setPercentError(String percentError) {
        this.percentError = percentError;
    }

    public String getCodeLenghtError() {
        return codeLenghtError;
    }

    public void setCodeLenghtError(String codeLenghtError) {
        this.codeLenghtError = codeLenghtError;
    }
}
