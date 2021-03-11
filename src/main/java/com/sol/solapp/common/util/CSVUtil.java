package com.sol.solapp.common.util;

import com.sol.solapp.common.exception.ServiceException;
import com.sol.solapp.common.exception.code.ErrorCode;
import lombok.experimental.UtilityClass;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@UtilityClass
public class CSVUtil {

    private final String[] TYPE = {"text/csv",
            "text/plain",
            "application/vnd.ms-excel",
            "text/x-csv",
            "application/x-csv",
            "text/comma-separated-values",
            "text/x-comma-separated-values",
            "text/tab-separated-values"};

    public boolean hasCSVFormat(MultipartFile file) {
        return Arrays.stream(TYPE).anyMatch(s -> s.equals(file.getContentType()));
    }

    public <T> Map<Boolean, List<T>> convertCsvToEntity(InputStream is
            , Function<CSVRecord,T> convertFunction
            , Predicate<T> validationRule) {
        try {
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            return csvParser.getRecords().stream()
                    .map(convertFunction)
                    .collect(Collectors.partitioningBy(validationRule));
        } catch (IOException e) {
            throw new ServiceException(ErrorCode.CSV_PARSE_ERROR);
        }

    }

}
