#  Calculadora de TMB (Taxa Metabólica Basal)

Este projeto é uma **aplicação web full stack simples** que permite calcular a **Taxa Metabólica Basal (TMB)** de um usuário.
O **frontend** é feito em **HTML, CSS e JavaScript puro**, que consome a **API REST desenvolvida em Spring Boot** no **backend**.
Os cálculos são armazenados em banco de dados, permitindo consulta ao histórico.

---

##  Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot** (REST Controller, Service, Repository)
* **Spring Data JPA** (persistência no banco de dados)
* **HTML5, CSS3, JavaScript** (frontend)
* **Banco de Dados** (H2, MySQL ou outro configurado)

---

##  Como funciona

1. O usuário acessa a **página inicial (`index.html`)**.
2. Preenche os dados:

   * Nome
   * Idade
   * Sexo (Masculino/Feminino)
   * Peso (kg)
   * Altura (cm)
   * Nível de Atividade (sedentário → muito ativo)
3. O frontend envia os dados via **requisição AJAX**:

   ```http
   POST /tmb/api/calcular
   ```
4. O **backend (Spring Boot)** recebe os dados, calcula:

   * **TMB** → Fórmula de Mifflin-St Jeor
   * **Calorias para manter peso** → `TMB * fator de atividade`
   * **Calorias para perder peso** → `manterPeso - 500`
5. O backend retorna os resultados em **JSON**.
6. O frontend mostra o resultado em um **modal estilizado**.

---

##  Fórmulas Utilizadas

### Fórmula de Mifflin-St Jeor

* **Homens**:
  `TMB = (9.99 × peso) + (6.25 × altura) - (4.92 × idade) + 5`
* **Mulheres**:
  `TMB = (9.99 × peso) + (6.25 × altura) - (4.92 × idade) - 161`

### Fatores de Atividade

* Sedentário: `TMB × 1.2`
* Leve: `TMB × 1.375`
* Moderado: `TMB × 1.55`
* Ativo: `TMB × 1.725`
* Muito Ativo: `TMB × 1.9`

### Perda de Peso

`Calorias para perder peso = caloriasManter - 500`

---

##  Estrutura do Projeto

```
src/
 └── main/java/com/example/calcTmb
      ├── controller/   # Controladores REST
      │    └── TmbController.java
      ├── dto/          # Objetos de transferência de dados
      │    └── CalculoTmbRequest.java
      ├── model/entity/ # Entidade Usuarios
      │    └── Usuarios.java
      ├── repositories/ # Repositório Spring Data JPA
      │    └── UsuariosRepository.java
      └── service/      # Regras de negócio
           ├── TmbService.java
           └── TmbServiceImpl.java
resources/
 ├── static/   # Arquivos estáticos (index.html, CSS, JS)
 └── application.properties # Configuração do banco
```

---

##  Endpoints da API

### Calcular TMB

```http
POST /tmb/api/calcular
```

**Body (JSON):**

```json
{
  "nome": "José",
  "idade": 29,
  "sexo": "M",
  "peso": 80,
  "altura": 175,
  "nivelAtividade": "moderado"
}
```

**Resposta (JSON):**

```json
{
  "nome": "José",
  "idade": 29,
  "sexo": "M",
  "peso": 80,
  "altura": 175,
  "nivelAtividade": "moderado",
  "tmb": 1750.5,
  "manterPeso": 2713.28,
  "perderPeso": 2213.28
}
```

---

### Histórico de cálculos

```http
GET /tmb/api/historico
```

Retorna todos os registros de usuários e cálculos em ordem decrescente de data.

---

##  Como rodar o projeto localmente

1. Clone o repositório:

   ```bash
   git clone https://github.com/matheussjsilva/calculadoraTMB.git
   cd calculadoraTMB
   ```
2. Configure o banco de dados no `application.properties` (ex.: H2 para testes ou MySQL em produção).
3. Compile e execute:

   ```bash
   mvn spring-boot:run
   ```
4. Acesse no navegador:

   ```
   http://localhost:8080/index.html
   ```

---

##  Autor

Desenvolvido por **Matheus Silva** 
Projeto para estudo de **Spring Boot, APIs REST e frontend responsivo sem frameworks**.
