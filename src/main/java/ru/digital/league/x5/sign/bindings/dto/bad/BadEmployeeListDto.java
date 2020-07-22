package ru.digital.league.x5.sign.bindings.dto.bad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.header.Headers;
import ru.digital.league.x5.sign.bindings.dto.EmployeeListDto;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BadEmployeeListDto extends EmployeeListDto {

    private byte[] failedDecode;

    private Headers headers;
}
