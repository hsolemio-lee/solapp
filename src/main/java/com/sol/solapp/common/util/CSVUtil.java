package com.sol.solapp.common.util;

import com.sol.solapp.common.exception.ServiceException;
import com.sol.solapp.common.exception.code.ErrorCode;
import com.sol.solapp.user.entity.User;
import lombok.experimental.UtilityClass;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class CSVUtil {

    private final String TYPE = "text/csv";
    private final String[] HEADER = { "id", "firstname", "lastname", "email" };

    public boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public Map<Boolean, List<User>> convertCsvToUser(InputStream is) {
        try {
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            return csvParser.getRecords().stream()
                    .map(csvRecord -> User.builder()
                            .id("".equals(csvRecord.get(HEADER[0])) ? 0L : Long.parseLong(csvRecord.get(HEADER[0])))
                            .firstName(csvRecord.get(HEADER[1]))
                            .lastName(csvRecord.get(HEADER[2]))
                            .email(csvRecord.get(HEADER[3]))
                            .build())
                    .collect(Collectors.partitioningBy(user -> !user.getId().equals(0L)
                            && !ValidateUtil.isEmpty(user.getFirstName())
                            && !ValidateUtil.isEmpty(user.getLastName())
                            && !ValidateUtil.isEmpty(user.getEmail())
                            && ValidateUtil.isValidEmail(user.getEmail())));
        } catch (IOException e) {
            throw new ServiceException(ErrorCode.CSV_PARSE_ERROR);
        }

    }

}
