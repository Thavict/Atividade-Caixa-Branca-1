# Atividade-Caixa-Branca-1
## 📌 1. Código analisado

```java
package login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
    public Connection conectarBD() {
    Connection conn = null;
    try{
    Class.forName("conn.mysql.Driver.Manager").newInstance();
    String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
    conn = DriverManager.getConnection(url);
    }catch (Exception e) {}
    return conn;}
    public String nome="";
    public boolean result = false;
    public boolean verifyLatGuardio(String login, String senha){
    String sql = "";
    Connection conn = conectarBD();
    sql += "select nome from usuarios ";
    sql +="where login = " + "'" + login + "'";
    sql += " and senha = " + "'" + senha + "';";
    try{
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery(sql);
    if(rs.next()) {
    result = true;
    nome = rs.getString("nome");
    }catch (Exception e) {}
    return result;}
    }//fim da class
```

---

## 🔷 2. Notação de Grafo de Fluxo

```
(1) Início (método verifyLatGuardio)
     ↓
(2) conectarBD()
     ↓
(3) Montagem da instrução SQL
     ↓
(4) Executar Query
     ↓
(5) Decisão: rs.next() ?
     ├── True  → (6) Usuário encontrado
     └── False → (7) Usuário não encontrado
     ↓
(8) Retorno
```

---

## 🧮 3. Complexidade Ciclomática

### 🔹 Fórmula utilizada:
```
V(G) = P + 1
```
Onde **P = número de decisões**.

### 🔹 Identificação:
- `if(rs.next())` → **1 ponto de decisão**

### 🔹 Cálculo:
```
V(G) = 1 + 1 = 2
```

### ✔ Resultado:
A complexidade ciclomática é **2**.

---

## 🧠 4. Caminhos Básicos

### ✔ Caminho 1 — Usuário encontrado
1. Início
2. conectarBD()
3. Montar SQL
4. Executar query
5. rs.next() = true
6. result = true; nome preenchido
7. return true

### ✔ Caminho 2 — Usuário não encontrado
1. Início
2. conectarBD()
3. Montar SQL
4. Executar query
5. rs.next() = false
6. result permanece false
7. return false

---

## 📝 5. Resumo do Cálculo da Complexidade

```
Para chegar ao resultado final, segui estes passos:

Contei quantas decisões o código tinha → encontrei 1

Apliquei a fórmula V(G) = P + 1 → 1 + 1 = 2

Confirmei que realmente existem 2 caminhos independentes

Esta análise me mostrou que, embora o código cumpra sua função básica, existem oportunidades de melhoria em termos de segurança e tratamento de erros.
```

---
