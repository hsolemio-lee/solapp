package com.sol.solapp.common.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    PARAMETER_EMPTY("SOL400-001", "Parameter is empty"),
    PARAMETER_INVALID("SOL400-002", "Parameter is invalid"),
    ID_EXIST("SOL400-003", "ID already exists"),
    INVALID_CSV_FILE("SOL400-004", "Invalid CSV file"),
    CSV_PARSE_ERROR("SOL400-005", "CSV Parsing Error"),
    UNKNOWN_ERROR("SOL500-001", "Unknown error"),
    ;

    private String code;
    private String message;
}
