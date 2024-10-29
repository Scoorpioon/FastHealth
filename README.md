# Aplicativo de gerenciamento de filas

Esse é um projeto full-stack desenvolvido em ReactJS, Sass, Java Spring, Springsecurity e JWT.

O código foi desenvolvido inicialmente como um trabalho universitário da Universidade Cidade de São Paulo.

## Conteúdos

- [Instalação](#instalação)
- [Uso](#uso)
- [Endpoints da API](#endpoints)
- [URLs](#urls)
- [Autenticação](#autenticação)
- [Banco de dados](#bd)
- [Contribuição](#contribuicao)

## Instalação

1. Clone o repositório
2. 
```bash
git clone https://github.com/Scoorpioon/analiseeprojetodesistemas-bluefenix.git
```

2. Instale as dependências do Java com Maven, e as dependências do React com NPM ou yarn.

3. Instale [SQL Server](https://www.microsoft.com/pt-br/sql-server/sql-server-downloads)

## Uso

1. Para a API, inicie-a com o Maven. Ela estará disponível na porta localhost:8080
2. Para o React, inicie-o com o comando npm run dev. Ele estará disponível na porta localhost:5173


## Endpoints
A API dispõe dos seguintes endpoints:

```markdown
POST /pacientes/criar - Registra um paciente

POST /consultas/criar - Registra uma consulta em uma determinada data

POST /auth/atendente/cadastro - Registra um atendente

GET /consultas/buscarConsultas/{data} - Busca consultas específicas por data, no formato YYYY-MM-DD

GET /api/fila/encontrar/{data} - Busca a fila específica da data informada, no formato YYYY-MM-DD
```

## URLs
O Front-end possui os seguintes caminhos:

```markdown
/ - Página inicial 

/registro/paciente - Página de cadastro do paciente

/registro/atendente - Página de cadastro do atendente

/login/paciente - Página de login do paciente

/login/atendente - Página de login do atendente

/painelDoAtendente - Tela de gerenciamento da fila do dia atual, para os atendentes.

/fila/{data} - Tela da fila do dia atual, no qual pode ser acessada pelos usuários que possuem cadastro na fila (em desenvolvimento)

/consulta/criar - Página do atendente para criação de consultas
```


## Authentication
A API utiliza autenticação para controlar permissões. No entanto, atualmente está em desenvolvimento e não funcionando devidamente.

```
USER -> Cargo atual para paciente
ADMIN -> Cargo atual para atendente
```
No momento, qualquer cargo pode fazer requisições totais na API.

## Database
Esse projeto utiliza [SQL Server](https://www.microsoft.com/pt-br/sql-server/sql-server-downloads) para fazer o armazenamento dos dados do projeto.

## Contribuição
Contribuições são mais do que bem-vindas neste projeto! Você pode postar suas sugestões nos issues ou solicitar um pull request no projeto.
Por favor, crie uma nova branch para subir alterações.
