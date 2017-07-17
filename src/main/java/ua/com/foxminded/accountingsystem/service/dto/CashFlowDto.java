package ua.com.foxminded.accountingsystem.service.dto;

import ua.com.foxminded.accountingsystem.model.DocumentType;
import ua.com.foxminded.accountingsystem.model.Money;

import java.time.LocalDate;

public class CashFlowDto {

    private LocalDate documentDate;
//    private DocumentType documentType;
    private Long documentId;
    private Money money;

    public CashFlowDto(LocalDate documentDate, Long documentId, Money money) {
        this.documentDate = documentDate;
//        this.documentType = documentType;
        this.documentId = documentId;
        this.money = money;
    }

    public LocalDate getDocumentDate() {
        return documentDate;
    }

//    public DocumentType getDocumentType() {
//        return documentType;
//    }

    public Long getDocumentId() {
        return documentId;
    }

    public Money getMoney() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        CashFlowDto that = (CashFlowDto) o;

        if (!documentDate.equals(that.documentDate)) return false;
//        if (documentType != that.documentType) return false;
        if (!documentId.equals(that.documentId)) return false;
        return money != null ? money.equals(that.money) : that.money == null;
    }

    @Override
    public int hashCode() {
        int result = documentId.hashCode();
//        result = 31 * result + documentType.hashCode();
        return result;
    }
}
