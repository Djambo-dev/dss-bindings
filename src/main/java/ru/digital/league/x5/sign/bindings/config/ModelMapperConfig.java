package ru.digital.league.x5.sign.bindings.config;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import ru.digital.league.x5.sign.bindings.db.entity.ClusterEmployeeEntity;
import ru.digital.league.x5.sign.bindings.db.entity.EmployeeEntity;
import ru.digital.league.x5.sign.bindings.db.entity.StoreEntity;
import ru.digital.league.x5.sign.bindings.db.key.StoreKey;
import ru.digital.league.x5.sign.bindings.dto.ClusterEmployeeDto;
import ru.digital.league.x5.sign.bindings.dto.EmployeeDto;
import ru.digital.league.x5.sign.bindings.dto.StoreDto;

@SuppressWarnings("Convert2Lambda") // если преобразовать, то перестанут корректно работать тесты
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
        mapper.addConverter(converterStoreEntityToDto());
        mapper.addConverter(converterEmployeeDtoToEntity());
        mapper.addConverter(converterClusterEmployeeDtoToEntity());

        return mapper;
    }

    private Converter<EmployeeDto, EmployeeEntity> converterEmployeeDtoToEntity() {
        return new Converter<>() {
            @Override
            public EmployeeEntity convert(MappingContext<EmployeeDto, EmployeeEntity> context) {
                EmployeeDto source = context.getSource();
                Assert.notNull(source, "Object of convert is missing");
                EmployeeEntity employeeEntity = new EmployeeEntity();
                employeeEntity.setCfoId(source.getCfoId());
                employeeEntity.setPositionId(source.getPositionId());
                employeeEntity.setPositionName(source.getPositionName());
                employeeEntity.setPersonalLogin(source.getPersonalLogin());
                String linkedPn = source.getLinkedPersonalNumber();
                boolean isNotExistsLinkedPn = (linkedPn == null || linkedPn.isBlank());
                String personalNumber = (isNotExistsLinkedPn) ? source.getPersonalNumber() : linkedPn;
                employeeEntity.setPersonalNumber(personalNumber);
                employeeEntity.setPartTimePersonalNumber((isNotExistsLinkedPn) ? null : source.getPersonalNumber());
                return employeeEntity;
            }
        };
    }

    private Converter<ClusterEmployeeDto, ClusterEmployeeEntity> converterClusterEmployeeDtoToEntity() {
        return new Converter<>() {
            @Override
            public ClusterEmployeeEntity convert(MappingContext<ClusterEmployeeDto, ClusterEmployeeEntity> context) {
                ClusterEmployeeDto source = context.getSource();
                Assert.notNull(source, "Object of convert is missing");
                ClusterEmployeeEntity clusterEmployeeEntity = new ClusterEmployeeEntity();
                clusterEmployeeEntity.setClusterId(source.getClusterId());
                clusterEmployeeEntity.setPositionId(source.getPositionId());
                clusterEmployeeEntity.setPositionName(source.getPositionName());
                clusterEmployeeEntity.setPersonalLogin(source.getPersonalLogin());
                String linkedPn = source.getLinkedPersonalNumber();
                boolean isNotExistsLinkedPn = (linkedPn == null || linkedPn.isBlank());
                String personalNumber = (isNotExistsLinkedPn) ? source.getPersonalNumber() : linkedPn;
                clusterEmployeeEntity.setPersonalNumber(personalNumber);
                clusterEmployeeEntity.setPartTimePersonalNumber((isNotExistsLinkedPn) ? null : source.getPersonalNumber());
                clusterEmployeeEntity.setFullName(source.getFullName());
                clusterEmployeeEntity.setRole(source.getRole());
                clusterEmployeeEntity.setEmail(source.getEmail());
                return clusterEmployeeEntity;
            }
        };
    }

    private Converter<StoreDto, StoreEntity> converterStoreDtoToEntity() {
        return new Converter<>() {
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
                storeEntity.setClusterId(source.getClusterId());
                return storeEntity;
            }
        };
    }

    private Converter<StoreEntity, StoreDto> converterStoreEntityToDto() {
        return new Converter<>() {
            @Override
            public StoreDto convert(MappingContext<StoreEntity, StoreDto> context) {
                StoreEntity source = context.getSource();
                Assert.notNull(source, "Object of convert is missing");
                StoreDto storeDto = new StoreDto();
                storeDto.setAddress(source.getAddress());
                storeDto.setName(source.getName());
                storeDto.setOpenDate(source.getOpenDate());
                storeDto.setCloseDate(source.getCloseDate());
                storeDto.setMdmStoreId(source.getStoreKey().getMdmStoreId());
                storeDto.setCfoId(source.getStoreKey().getCfoId());
                storeDto.setClusterId(source.getClusterId());
                return storeDto;
            }
        };
    }

}
