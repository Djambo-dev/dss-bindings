### Параметры микросервиса
| Параметр | Пример | Значение по-умолчанию | Описание |
| ---      | ------ | ------                | ---      |
|PG_HOST|http://192.168.1.0/| - |  Хост для подключения к СУБД Postgres |
|PG_PORT| 5432 | - | Порт для подключения к СУБД Postgres  |
|PG_DATABASE| doc-sign | - | Название БД |
|PG_USER| doc-sign | - | Логин для БД Postgres|
|PG_PASSWORD| msa123 | - | Пароль для БД Postgres |
|KAFKA_URL|http://localhost:9092| - | Базовый URL для взаимодействия с Apache Kafka |
|KAFKA_KEY_STORE_PATH|/projects/store/keys| - | Хранилище ключей клиента (актуально при KAFKA_SSL_ENABLE=true)|
|KAFKA_KEY_STORE_PASSWORD|pass0word| - | Пароль от хранилища ключей (актуально при KAFKA_SSL_ENABLE=true)|
|KAFKA_TRUST_STORE_PATH|/projects/store/trust| - | Хранилище доверенных сертификатов (актуально при KAFKA_SSL_ENABLE=true)|
|KAFKA_TRUST_STORE_PASSWORD|pass0word| - | Пароль от хранилища доверенных сертификатов (актуально при KAFKA_SSL_ENABLE=true)|
|KAFKA_CONSUMER_GROUP_ID|1| - | Идентификатор группы топиков в Apache Kafka |
|KAFKA_INBOX_BINDINGS_TOPIC|t5| - | Очередь для обновлений привязок сотрудников к цфо (из SAP PI) |
|KAFKA_INBOX_STORES_TOPIC|t4| - | Очередь для обновлений привязок к магазинам (из SAP PI)|
|KAFKA_CLUSTER_EMPLOYEE|t13| - | Очередь для обновлений привязок сотрудников к кластерам (из SAP PI)|
|KAFKA_RECONNECT_BACKOFF| - | 1000 | Таймаут между попытками подключения при потере связи с брокером (миллисекунды) |
|KAFKA_RECONNECT_BACKOFF_MAX| - | 60000| Максимальный таймаут при экспоненциальном увеличении времени между попытками соединения (миллисекунды)|
|KAFKA_SSL_ENABLE| - | false | Позволяет включить обмен данными с Apache Kafka через ssl |
|ENABLE_SECURITY| - | true | Позволяет включать или выключать OAuth2 авторизацию для сервиса|
|LOG_LEVEL| - | INFO | Уровень логирования |
