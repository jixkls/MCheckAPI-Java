# 🩺 MedCheckAPI  
API REST para busca e gestão de médicos, especialidades e cidades de atendimento.

Este projeto foi desenvolvido como estudo prático de **Java + Spring Boot**, é uma remodelagem do MedCheckAPI feito em Node.js. Sendo um projeto adicional, seguindo boas práticas de arquitetura, versionamento e modelagem de APIs REST.  
O objetivo é permitir que usuários encontrem médicos por nome, cidade ou especialidade, além de permitir gestão completa via endpoints administrativos.

---

## 🚀 Tecnologias Utilizadas
```
- **Java 21**
- **Spring Boot 3**
  - Web
  - Data JPA
  - Security
  - Validation
- **Flyway** para versionamento de banco
- **PostgreSQL** (produção)  
- **H2 Database** (desenvolvimento)
- **Lombok**
- **Maven**
- **Swagger (Springdoc)** – *opcional, será adicionado depois*
```
---

## 📦 Arquitetura do Projeto

```

src/main/java/com.mcapijava.medcheck
├── config/              → Configurações (Security, etc.)
├── controllers/         → Endpoints REST
├── dto/                 → Objetos de transporte (Request/Response)
├── models/              → Entidades JPA
├── repository/          → Interfaces de persistência (JPA)
└── MedcheckApplication  → Aplicação principal

```

### 📁 Migrations (Flyway)

```

src/main/resources/db/migration
├── V1__init_schema.sql  → Criação das tabelas
└── V2__seed_data.sql    → Seed inicial (especialidades e cidades)

```

---

## 🧠 Visão Geral da Modelagem

### 🩺 **Doctor**
- id (UUID)
- name
- crm (único)
- specialties (ManyToMany)
- cities (ManyToMany)

### 🏥 **Specialty**
- id (UUID)
- name (único)

### 🌆 **City**
- id (UUID)
- name
- state (ex.: "PR")

---

## 🔌 Endpoints

### 🏥 **Health**
| Método | Rota | Descrição |
|--------|-------|-----------|
| GET | `/api/v1/health` | Status da API |

---

### 🌆 **Cities**
Endpoints abertos:

| Método | Rota | Descrição |
|--------|-------|-----------|
| GET | `/api/v1/cities` | Lista todas as cidades |
| POST | `/api/v1/cities` | Cadastra uma nova cidade *(admin)* |

---

### 🧬 **Specialties**
Endpoints abertos:

| Método | Rota | Descrição |
|--------|-------|-----------|
| GET | `/api/v1/specialties` | Lista todas as especialidades |
| POST | `/api/v1/specialties` | Cadastra uma nova especialidade *(admin)* |

---

### 👨‍⚕️ **Doctors**

#### Endpoints públicos
| Método | Rota | Descrição |
|--------|-------|-----------|
| GET | `/api/v1/doctors` | Lista paginada de médicos |
| GET | `/api/v1/doctors/{id}` | Detalhes de um médico |

#### Endpoints administrativos
| Método | Rota | Descrição |
|--------|-------|-----------|
| POST | `/api/v1/doctors` | Cadastra médico |
| PUT | `/api/v1/doctors/{id}` | Atualiza médico |
| DELETE | `/api/v1/doctors/{id}` | Remove médico |

---

## 🔍 Busca avançada

| Método | Rota | Query Params |
|--------|-------|--------------|
| GET | `/api/v1/search/doctors` | `name`, `specialty`, `city` |

Exemplo:

```

GET /api/v1/search/doctors?name=ana&city=apucarana&specialty=cardiologia

````

---

## 🧪 Códigos de resposta

| Código | Significado |
|--------|-------------|
| 200 | Sucesso |
| 201 | Criado |
| 204 | Sem conteúdo |
| 400 | Requisição inválida |
| 401 | Não autorizado |
| 404 | Não encontrado |
| 422 | Entidade inválida |
| 500 | Erro interno |

---

## 🗂️ Migrations – Estrutura

### **V1 — Schema Inicial**
Cria:
- specialties
- cities
- doctors
- doctor_specialties
- doctor_cities
- índices

### **V2 — Seed**
Insere:
- Especialidades:
  - Cardiologia
  - Dermatologia
  - Pediatria
  - Clínica Geral
- Cidades:
  - São Paulo/SP
  - Apucarana/PR
  - Rio de Janeiro/RJ

---

## 🧰 Como rodar o projeto

### **1. Clone o repositório**
```bash
git clone https://github.com/.../MedCheckAPI.git
cd MedCheckAPI
````

### **2. Configure o banco**

No `application.properties`:

#### H2 (dev)

```properties
spring.datasource.url=jdbc:h2:mem:medcheck
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
```

#### PostgreSQL (prod)

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/medcheck
spring.datasource.username=medcheck_user
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=none
```

### **3. Rodar a aplicação**

```bash
./mvnw spring-boot:run
```

---

## 📚 Próximos passos planejados

* [ ] Implementar Swagger/OpenAPI
* [ ] Implementar POST/PUT/DELETE de Doctor
* [ ] Implementar validação com Bean Validation
* [ ] Criar camada de Service
* [ ] Autenticação com JWT
* [ ] Perfis DEV/PROD
* [ ] Testes automatizados

---
