package ua.com.foxminded.accountingsystem.service.dto;

import ua.com.foxminded.accountingsystem.model.DocumentType;
import ua.com.foxminded.accountingsystem.model.Money;

import java.time.LocalDate;

public class CashFlowDto {

    private Long documentId;
    private DocumentType documentType;
    private LocalDate documentDate;
    private Money inflow;
    private Money outflow;

    public CashFlowDto(Long documentId, DocumentType documentType, LocalDate documentDate, Money inflow, Money outflow) {
        this.documentId = documentId;
        this.documentType = documentType;
        this.documentDate = documentDate;
        this.inflow = inflow;
        this.outflow = outflow;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public LocalDate getDocumentDate() {
        return documentDate;
    }

    public Money getInflow() {
        return inflow;
    }

    public Money getOutflow() {
        return outflow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        CashFlowDto that = (CashFlowDto) o;

        if (!documentId.equals(that.documentId)) return false;
        if (documentType != that.documentType) return false;
        if (!documentDate.equals(that.documentDate)) return false;
        if (inflow != null ? !inflow.equals(that.inflow) : that.inflow != null) return false;
        return outflow != null ? outflow.equals(that.outflow) : that.outflow == null;
    }

    @Override
    public int hashCode() {
        int result = documentId.hashCode();
        result = 31 * result + documentType.hashCode();
        return result;
    }
}
