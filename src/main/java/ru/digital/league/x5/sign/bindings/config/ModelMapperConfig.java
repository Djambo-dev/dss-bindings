package ru.digital.league.x5.sign.bindings.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import ru.digital.league.x5.sign.bindings.db.entity.ClusterEmployeeEntity;
import ru.digital.league.x5.sign.bindings.db.entity.EmployeeEntity;
import ru.digital.league.x5.sign.bindings.db.entity.StoreEntity;
import ru.digital.league.x5.sign.bindings.dto.ClusterEmployeeDto;
import ru.digital.league.x5.sign.bindings.dto.ClusterEmployeeListDto;
import ru.digital.league.x5.sign.bindings.dto.EmployeeDto;
import ru.digital.league.x5.sign.bindings.dto.EmployeeListDto;
import ru.digital.league.x5.sign.bindings.dto.StoreDto;
import ru.digital.league.x5.sign.bindings.xml.model.ClusterEmployeeList;
import ru.digital.league.x5.sign.bindings.xml.model.EmployeeList;
import ru.digital.league.x5.sign.bindings.xml.model.Store;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("Convert2Lambda") // если преобразовать, то перестанут корректно работать тесты
@Configuration
@RequiredArgsConstructor
@Slf4j
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);
        mapper.addConverter(converterStoreDtoToEntity());
        mapper.addConverter(converterStoreEntityToDto());
        mapper.addConverter(converterStoreToStoreEntity());
        mapper.addConverter(converterEmployeeDtoToEntity());
        mapper.addConverter(converterEmployeeBindingListToDto());
        mapper.addConverter(converterClusterEmployeeDtoToEntity());
        mapper.addConverter(converterClusterEmployeeBindingListToDto());

        return mapper;
    }

    private Converter<EmployeeList, EmployeeListDto> converterEmployeeBindingListToDto() {
        return new Converter<>() {
            @Override
            public EmployeeListDto convert(MappingContext<EmployeeList, EmployeeListDto> context) {
                try {
                    EmployeeList source = context.getSource();
                    Assert.notNull(source, "Object of convert is missing");
                    List<EmployeeDto> employeeBindingList;
                    if (!CollectionUtils.isEmpty(source.getEmployeeBindings())) {
                        employeeBindingList = source.getEmployeeBindings().stream()
                                .flatMap(item -> {
                                            if (item.getEmployeeBindings() == null) {
                                                return Stream.of(EmployeeDto.builder()
                                                        .cfoId(item.getCfoId()).build());
                                            } else return item.getEmployeeBindings().stream()
                                                    .filter(Objects::nonNull)
                                                    .map(binding -> EmployeeDto.builder()
                                                            .cfoId(item.getCfoId())
                                                            .personalLogin(binding.getPersonalLogin())
                                                            .personalNumber(binding.getPersonalNumber())
                                                            .positionId(binding.getPositionId())
                                                            .positionName(binding.getPositionName())
                                                            .linkedPersonalNumber(binding.getLinkedPersonalNumber())
                                                            .build());
                                        }
                                )
                                .collect(Collectors.toList());
                        return EmployeeListDto.builder()
                                .employeeBindingList(employeeBindingList)
                                .build();
                    }
                } catch (Exception e) {
                    log.error("EmployeeList mapping is failed", e);
                }
                return null;
            }
        };
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
                employeeEntity.setIsDeleted(false);
                return employeeEntity;
            }
        };
    }

    private Converter<ClusterEmployeeList, ClusterEmployeeListDto> converterClusterEmployeeBindingListToDto() {
        return new Converter<>() {
            @Override
            public ClusterEmployeeListDto convert(MappingContext<ClusterEmployeeList, ClusterEmployeeListDto> context) {
                ClusterEmployeeList source = context.getSource();
                Assert.notNull(source, "Object of convert is missing");
                List<ClusterEmployeeDto> employeeBindingList = null;
                if (!CollectionUtils.isEmpty(source.getClusterEmployeeBindingList())) {
                    employeeBindingList = source.getClusterEmployeeBindingList().stream()
                            .flatMap(item -> {
                                        if (item.getClusterEmployeeList() == null) {
                                            return Stream.of(ClusterEmployeeDto.builder()
                                                    .clusterId(item.getClusterId()).build());
                                        } else {
                                            return item.getClusterEmployeeList().stream()
                                                    .map(binding -> ClusterEmployeeDto.builder()
                                                            .clusterId(item.getClusterId())
                                                            .personalLogin(binding.getPersonalLogin())
                                                            .personalNumber(binding.getPersonalNumber())
                                                            .positionId(binding.getPositionId())
                                                            .positionName(binding.getPositionName())
                                                            .fullName(binding.getFullName())
                                                            .role(binding.getRole())
                                                            .email(binding.getEmail())
                                                            .linkedPersonalNumber(binding.getLinkedPersonalNumber())
                                                            .build()
                                                    );
                                        }
                                    }
                            )
                            .collect(Collectors.toList());
                }
                return ClusterEmployeeListDto.builder()
                        .clusterEmployeeBindingList(employeeBindingList)
                        .build();
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
                clusterEmployeeEntity.setIsDeleted(false);
                return clusterEmployeeEntity;
            }
        };
    }

    private Converter<Store, StoreEntity> converterStoreToStoreEntity() {
        return new Converter<>() {
            @Override
            public StoreEntity convert(MappingContext<Store, StoreEntity> context) {
                Store source = context.getSource();
                Assert.notNull(source, "Object of convert is missing");
                StoreEntity storeEntity = new StoreEntity();
                storeEntity.setMdmStoreId(source.getMdmStoreId());
                storeEntity.setCfoId(source.getCfoId());
                storeEntity.setAddress(source.getAddress());
                storeEntity.setName(source.getName());
                storeEntity.setOpenDate(source.getOpenDate());
                storeEntity.setCloseDate(source.getCloseDate());
                storeEntity.setClusterId(source.getClusterId());
                return storeEntity;
            }
        };
    }

    private Converter<StoreDto, StoreEntity> converterStoreDtoToEntity() {
        return new Converter<>() {
            @Override
            public StoreEntity convert(MappingContext<StoreDto, StoreEntity> context) {
                StoreDto source = context.getSource();
                Assert.notNull(source, "Object of convert is missing");
                StoreEntity storeEntity = new StoreEntity();
                storeEntity.setMdmStoreId(source.getMdmStoreId());
                storeEntity.setCfoId(source.getCfoId());
                storeEntity.setAddress(source.getAddress());
                storeEntity.setName(source.getName());
                storeEntity.setOpenDate(source.getOpenDate());
                storeEntity.setCloseDate(source.getCloseDate());
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
                storeDto.setMdmStoreId(source.getMdmStoreId());
                storeDto.setCfoId(source.getCfoId());
                storeDto.setClusterId(source.getClusterId());
                return storeDto;
            }
        };
    }

}
