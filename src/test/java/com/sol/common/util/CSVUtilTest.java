package com.sol.common.util;

import com.sol.solapp.common.util.CSVUtil;
import com.sol.solapp.common.util.ValidateUtil;
import com.sol.solapp.user.entity.User;
import com.sol.solapp.user.service.helper.UserServiceImplTestHelper;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
public class CSVUtilTest {
    private final String[] HEADER = { "id", "firstname", "lastname", "email" };

    @Test
    public void givenValidFile_callHasCSVFormatThenReturnTrue() {
        MockMultipartFile mockMultipartFile = UserServiceImplTestHelper.getMockMultipartFile("text/csv"
                , "id,firstname,lastname,email" +
                        "\n1,sol,lee,test1@test.com" +
                        "\n2,sol,lee,test2@test.com" +
                        "\n3,sol,lee,test3@test.com" +
                        "\n4,sol,lee,test4@test.com");

        assertThat(CSVUtil.hasCSVFormat(mockMultipartFile)).isEqualTo(true);
    }

    @Test
    public void givenInValidFile_callHasCSVFormat_thenReturnFalse() {
        MockMultipartFile mockMultipartFile = UserServiceImplTestHelper.getMockMultipartFile("test"
                , "id,firstname,lastname,email" +
                        "\n1,sol,lee,test1@test.com" +
                        "\n2,sol,lee,test2@test.com" +
                        "\n3,sol,lee,test3@test.com" +
                        "\n4,sol,lee,test4@test.com");

        assertThat(CSVUtil.hasCSVFormat(mockMultipartFile)).isEqualTo(false);
    }

    public void callConvertCsvToUserEntity() throws IOException {
        MockMultipartFile mockMultipartFile = UserServiceImplTestHelper.getMockMultipartFile("test"
                , "id,firstname,lastname,email" +
                        "\n1,sol,lee,test1@test.com" +
                        "\n2,sol,lee,test2@test.com" +
                        "\n3,sol,lee,test3@test.com" +
                        "\n,sol,lee,test4@test.com");

        Map<Boolean, List<User>> usersPartitioned = CSVUtil.convertCsvToEntity(mockMultipartFile.getInputStream(), convertUser, validationRule);

        assertThat(usersPartitioned.get(true).size()).isEqualTo(3);
        assertThat(usersPartitioned.get(false).size()).isEqualTo(1);
    }

    private final Function<CSVRecord, User> convertUser = record -> User.builder()
            .id("".equals(record.get(HEADER[0])) ? 0L : Long.parseLong(record.get(HEADER[0])))
            .firstName(Optional.ofNullable(record.get(HEADER[1])).orElse(""))
            .lastName(Optional.ofNullable(record.get(HEADER[2])).orElse(""))
            .email(Optional.ofNullable(record.get(HEADER[3])).orElse(""))
            .build();

    private final Predicate<User> validationRule = user -> !user.getId().equals(0L)
            && !ValidateUtil.isEmpty(user.getFirstName())
            && !ValidateUtil.isEmpty(user.getLastName())
            && !ValidateUtil.isEmpty(user.getEmail())
            && ValidateUtil.isValidEmail(user.getEmail());

}
