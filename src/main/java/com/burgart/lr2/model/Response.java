package com.burgart.lr2.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {

    /**
     * Уникальный идентификатор сообщения
     */
    private String uid;
    /**
     * Уникальный идентификатор операции
     */
    private String operationUid;
    /**
     * Время создания сообщения
     */
    private String systemTime;
    /**
     * Код выполнения
     */
    private Codes code;
    /**
     * Рассчитанный годовой бонус
     */
    private double annualBonus;
    /**
     * Код ошибки
     */
    private ErrorCodes errorCode;
    /**
     * Сообщение об ошибке
     */
    private ErrorMessages errorMessage;
}