# 📖 **Knowledge Trail WebApi** 

Bem-vindo ao Knowledge Trail WebApi! 
Aqui, você vai gerenciar sua trilha de conhecimento de forma prática. 
Vamos começar?

---

## 📦 **Dependências**

Antes de rodar o projeto, verifique se as seguintes dependências estão instaladas:

- **PostgreSQL**: É necessário ter o **PostgreSQL** instalado e rodando localmente para que o banco de dados seja configurado corretamente.

---

## 🗄️ **Configuração do Banco de Dados - PostgreSQL**

1. **Crie um banco de dados com o seguinte nome:**

```bash
knowledge-trail-system
```

2. **Configurações do banco de dados:**

- **Usuário (superusuário):** `postgres`
- **Senha:** `postgres`
- **Porta:** 5432 

Obs.: essas são as configurações definidas no arquivo `src/main/resources/application.yml`)

Certifique-se de que o PostgreSQL esteja rodando corretamente em sua máquina local para que o backend se conecte ao banco de dados.

---

## 🚀 **Rodando o Projeto no Terminal**

Com as dependências configuradas, vamos rodar o projeto. Para isso, execute o seguinte comando:

```bash
mvn clean spring-boot:run
```

Obs.: O `clean` é opcional, mas recomendado caso precise limpar o build e começar de novo.

---

## 🔧 Acessando a API - Swagger UI
Agora que o BackEnd está rodando, você pode visualizar e testar a API diretamente no Swagger UI!

Acesse em:
http://localhost:8090/swagger-ui.html

---

## 🔑 Credenciais de Administrador
Quando o sistema for inicializado, serão criadas automaticamente credenciais de administração, se não existirem. Você pode acessar essas funcionalidades realizando login com o perfil de administrador:

- Usuário: `admin`
- Senha: `admin`
