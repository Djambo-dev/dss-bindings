package ru.digital.league.x5.sign.bindings.dto.bad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.header.Headers;
import ru.digital.league.x5.sign.bindings.dto.StoreInfoDto;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BadStoreInfoDto extends StoreInfoDto {

    private byte[] failedDecode;

    private Headers headers;
}
