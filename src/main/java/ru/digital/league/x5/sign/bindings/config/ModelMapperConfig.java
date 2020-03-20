package ru.digital.league.x5.sign.bindings.config;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import ru.digital.league.x5.sign.bindings.db.entity.StoreEntity;
import ru.digital.league.x5.sign.bindings.db.key.StoreKey;
import ru.digital.league.x5.sign.bindings.dto.StoreDto;

@Configuration
@RequiredArgsConstructor
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);
        mapper.addConverter(converterStoreDtoToEntity());
        return mapper;
    }

    private Converter<StoreDto, StoreEntity> converterStoreDtoToEntity() {
        return new Converter<StoreDto, StoreEntity>() {
            @Override
            public StoreEntity convert(MappingContext<StoreDto, StoreEntity> context) {
                StoreDto source = context.getSource();
                Assert.notNull(source, "Object of convert is missing");
                StoreKey storeKey = new StoreKey(source.getMdmStoreId(), source.getCfoId());
                StoreEntity storeEntity = new StoreEntity();
                storeEntity.setAddress(source.getAddress());
                storeEntity.setName(source.getName());
                storeEntity.setOpenDate(source.getOpenDate());
                storeEntity.setCloseDate(source.getCloseDate());
                storeEntity.setStoreKey(storeKey);
                return storeEntity;
            }
        };
    }

}
