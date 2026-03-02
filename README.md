# VITAL (Vital Information Tracking & Analytics Layer)

![VITAL Icon](./vital_icon.png)

This is a simple distributed implementation of a Microservice Architecture. It has five initial services.

- `read-service: Responsible for any reading operation.`
- `write-service: Responsible for any write operation.`
- `service-registry: Responsible for discovering the services.`
- `service-gateway: Responsible for Routing & Load Balancing.`
- `backend-admin: Responsible for monitoring the health of the services.`

## Features

- **Encryption of data at rest:** Utilizes PostgreSQL's `pgcrypto` for symmetric key encryption of PHI data.
- **Encryption of data in-transit:** APIs are exposed over SSL. Inter-service connections are secured similarly.

## Running

The application is containerized utilizing multi-stage docker builds. Ensure port mappings are available, then run:

```bash
$ docker compose build
$ docker compose up -d
```

## Services & Ports

- read-service: `7003`
- write-service: `6003`
- discovery: `8761`
- gateway_service: `8443`
- admin_service: `1001`
- pgadmin: `5050`
- postgres_db: `5432`

## API

- **Create Single Data Point:**

```bash
$ curl --location --request POST 'https://localhost:8443/api/writeservice/v0.1/write/' \
--header 'Content-Type: application/json' \
--data-raw ' {
    "data": {
      "currentBpm": 74,
      ...
    },
    "id": 3752809
  }'
```

- **Load entire data.json file:**

```bash
$ curl --location --request POST 'https://localhost:8443/api/writeservice/v0.1/write/json'
```

- **Get VitalSigns for all patients:**

```bash
$ curl --location --request GET 'https://localhost:8443/api/readservice/v0.1/read/'
```
