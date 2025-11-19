package com.burgart.lr2.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @NotBlank
    @Size(max = 32)
    private String uid;
    /**
     * Уникальный идентификатор операции
     */
    @NotBlank
    @Size(max = 32)
    private String operationUid;
    /**
     * Наименование системы
     */
    private Systems systemName;
    /**
     * Время создания сообщения
     */
    @NotBlank
    private String systemTime;
    /**
     * Источник сообщения
     */
    private String source;
    /**
     * Должность сотрудника
     */
    private Positions position;
    /**
     * Зарплата сотрудника
     */
    private Double salary;
    /**
     * Коэффициент бонуса
     */
    private Double bonus;
    /**
     * Количество рабочих дней
     */
    private Integer workDays;
    /**
     * Уникальный идентификатор коммуникации
     */
    @Min(1)
    @Max(100000)
    private int communicationId;
    /**
     * Идентификатор шаблона
     */
    private int templateId;
    /**
     * Код продукта
     */
    private int productCode;
    /**
     * Код из смс
     */
    private int smsCode;

    private long requestTime;

    @Override
    public String toString() {
        return "{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName='" + systemName + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", communicationId=" + communicationId +
                ", templateId=" + templateId +
                ", productCode=" + productCode +
                ", smsCode=" + smsCode +
                '}';
    }
}