package dtos;

public class ProductEditErrorDTO {
    private String titleLengthError;
    private String authorLengthError;
    private String descriptionLengthError;
    private String fileLengthError;
    private String quantityFormatException;
    private String quantityLengthError;
    private String priceLengthError;
    private String priceFormatException;
    private String importDateError;

    public String getImportDateError() {
        return importDateError;
    }

    public void setImportDateError(String importDateError) {
        this.importDateError = importDateError;
    }

    public String getQuantityLengthError() {
        return quantityLengthError;
    }

    public void setQuantityLengthError(String quantityLengthError) {
        this.quantityLengthError = quantityLengthError;
    }

    public String getPriceLengthError() {
        return priceLengthError;
    }

    public void setPriceLengthError(String priceLengthError) {
        this.priceLengthError = priceLengthError;
    }

    public String getPriceFormatException() {
        return priceFormatException;
    }

    public void setPriceFormatException(String priceFormatException) {
        this.priceFormatException = priceFormatException;
    }

    public String getQuantityFormatException() {
        return quantityFormatException;
    }

    public void setQuantityFormatException(String quantityFormatException) {
        this.quantityFormatException = quantityFormatException;
    }

    public String getTitleLengthError() {
        return titleLengthError;
    }

    public void setTitleLengthError(String titleLengthError) {
        this.titleLengthError = titleLengthError;
    }

    public String getAuthorLengthError() {
        return authorLengthError;
    }

    public void setAuthorLengthError(String authorLengthError) {
        this.authorLengthError = authorLengthError;
    }

    public String getDescriptionLengthError() {
        return descriptionLengthError;
    }

    public void setDescriptionLengthError(String descriptionLengthError) {
        this.descriptionLengthError = descriptionLengthError;
    }

    public String getFileLengthError() {
        return fileLengthError;
    }

    public void setFileLengthError(String fileLengthError) {
        this.fileLengthError = fileLengthError;
    }
}
