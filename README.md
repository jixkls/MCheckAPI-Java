🧰 README.md — versão atualizada para MedCheckAPI-Java
# MedCheckAPI (Java + Spring Boot)

API REST para gestão e busca de médicos, especialidades e cidades de atendimento — com suporte completo a CRUD, buscas com filtros, paginação e persistência via banco de dados.  

Projeto desenvolvido com boas práticas, uso de migrações (Flyway), JPA/Hibernate, camadas separadas e possibilidade de rodar localmente ou via Docker.

---

## ✅ Tecnologias Utilizadas

- Java 21  
- Spring Boot 3 (Web, Data JPA, Validation, Security se aplicável)  
- Flyway (versões / migrações)  
- Banco de dados: PostgreSQL (produção) / H2 (desenvolvimento)  
- Maven  
- (Opcional) Springdoc / Swagger para documentação da API  

---

## 📂 Estrutura do Projeto  



src/
main/
java/com/…/medcheck → código fonte (controllers, dto, models, repository etc.)
resources/
db/migration → scripts Flyway: schema + seed
├── pom.xml
├── .mvn/…
├── Dockerfile
├── docker-compose.yml
└── README.md


---

## 🚀 Como Rodar Localmente (sem Docker)

1. Clone o repositório  
   ```bash
   git clone https://github.com/jixkls/MCheckAPI-Java.git
   cd MCheckAPI-Java


Configure o banco no src/main/resources/application.properties ou application-*.yml

Para ambiente de desenvolvimento (H2 – memória): já configurado por padrão

Para produção (PostgreSQL): configure URL, usuário, senha

Rode a aplicação:

./mvnw spring-boot:run


A API está rodando em http://localhost:8080

🐳 Como Rodar via Docker + Docker Compose

Se preferir rodar containerizado, siga:

Tenha instalado:

Docker

Docker Compose (separado ou integrado)

Na raiz do projeto, crie ou adapte um docker-compose.yml
Ex:

version: "3.9"
services:
  db:
    image: postgres:15-alpine
    environment:
      POSTGRES_DB: medcheck
      POSTGRES_USER: medcheck_user
      POSTGRES_PASSWORD: medcheck_pass
    ports:
      - "5432:5432"
    volumes:
      - db_data:/var/lib/postgresql/data

  app:
    build: .
    depends_on:
      - db
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/medcheck
      SPRING_DATASOURCE_USERNAME: medcheck_user
      SPRING_DATASOURCE_PASSWORD: medcheck_pass
      SPRING_JPA_HIBERNATE_DDL_AUTO: none
    ports:
      - "8080:8080"

volumes:
  db_data:


Construir e subir containers:

docker compose up --build


A API estará acessível em http://localhost:8080. O banco PostgreSQL estará “por trás” do container db.

📄 Endpoints
Rota / Método	Descrição
GET /api/v1/health	Verifica se a API está rodando
GET /api/v1/cities	Lista todas as cidades
POST /api/v1/cities	Cria nova cidade (admin)
GET /api/v1/specialties	Lista todas especialidades
POST /api/v1/specialties	Cria nova especialidade (admin)
GET /api/v1/doctors	Lista paginada de médicos
GET /api/v1/doctors/{id}	Detalhes de médico
POST /api/v1/doctors	Cria médico (admin)
PUT /api/v1/doctors/{id}	Atualiza médico (admin)
DELETE /api/v1/doctors/{id}	Remove médico (admin)
GET /api/v1/search/doctors	Busca por parâmetros (nome, cidade, especialidade)

(Resposta e códigos HTTP conforme especificado no README antigo)

🎯 Observações e Considerações

O usuário ADMIN não foi colocado em uma variável de ambiente, então, recomendo utilizar esse projeto em testes e com essas credenciais somente em desenvolvimento. Em produção essas
credenciais devem ser atualizadas e inseridas em variáveis de ambiente

Os scripts de migração estão em src/main/resources/db/migration:

V1__init_schema.sql — cria schema de tabelas

V2__seed_data.sql — insere dados iniciais (especialidades, cidades)

A estrutura modular (controllers, dto, models, repository) facilita manutenção, testes e futuras expansões.

A versão Docker está disponível, sem necessidade de instalação local de dependências externas.

📬 Contribuições / Uso

Sinta-se livre para usar, adaptar ou expandir este código conforme necessidade. Pull-requests e sugestões são bem-vindas.
