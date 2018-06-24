# Intelipost: Teste prático para Backend Developer

Este é o teste usado por nós aqui da [Intelipost](http://www.intelipost.com.br) para avaliar tecnicamente os candidatos a nossas vagas de Backend. Se você estiver participando de um processo seletivo para nossa equipe, certamente em algum momento receberá este link, mas caso você tenha chego aqui "por acaso", sinta-se convidado a desenvolver nosso teste e enviar uma mensagem para nós nos e-mails `stefan.rehm@intelipost.com.br` e `gustavo.hideyuki@intelipost.com.br`.

Aqui na Intelipost nós aplicamos este mesmo teste para as vagas em todos os níveis, ou seja, um candidato a uma vaga de backend júnior fará o mesmo teste de um outro candidato a uma vaga de backend sênior, mudando obviamente o nosso critério de avaliação do resultado do teste. 

Nós fazemos isso esperando que as pessoas mais iniciantes entendam qual o modelo de profissional que temos por aqui e que buscamos para o nosso time. Portanto, se você estiver se candidatando a uma vaga mais iniciante, não se assuste, e faça o melhor que você puder!

## Instruções

Você deverá criar um `fork` deste projeto, e desenvolver em cima do seu fork. Use o *README* principal do seu repositório para nos contar como foi resolver seu teste, as decisões tomadas, como você organizou e separou seu código, e principalmente as instruções de como rodar seu projeto, afinal a primeira pessoa que irá rodar seu projeto será um programador frontend de nossa equipe, e se você conseguir explicar para ele como fazer isso, você já começou bem!

Lembre-se que este é um teste técnico e não um concurso público, portanto, não existe apenas uma resposta correta. Mostre que você é bom e nos impressione, mas não esqueça do objetivo do projeto. 

Nós não definimos um tempo limite para resolução deste teste, o que vale para nós e o resultado final e a evolução da criação do projeto até se atingir este resultado, mas acreditamos que este desafio pode ser resolvido em cerca de 16 horas de codificação.

## Um pouco sobre a Intelipost

A Intelipost é uma startup de tecnologia que está revolucionando a logística no Brasil, um mercado de R$ 300B por ano com muitas ineficiências e desafios. Temos um sistema inovador que gerencia a logística para empresas do e-commerce. Já recebemos R$11 milhões de investimento até o momento, e em pouquissimo tempo já estamos colhendo grandes resultados: Em 2016 fomos selecionados como uma empresa [Promessas Endeavor](https://ecommercenews.com.br/noticias/parcerias-comerciais/intelipost-e-selecionada-pelo-promessas-endeavor/), também [ganhamos a competição IBM Smartcamp](https://www.ibm.com/blogs/robertoa/2016/11/intelipost-e-nazar-vencem-o-ibm-smartcamp-brasil-2016/), com foco de Big Data e data analysis, o que nos rendeu a [realização de um Hackathon sobre Blockchain junto a IBM](https://www.ibm.com/blogs/robertoa/2017/09/intelipost-e-ibm-realizam-o-primeiro-hackathon-de-blockchain-em-startup-do-brasil/), e em 2017 [fomos selecionados pela Oracle para sermos acelerados por eles no programa Oracle Startup Cloud Accelerator](https://www.oracle.com/br/corporate/pressrelease/oracle-anuncia-startups-selecionadas-programa-oracle-startup-cloud-accelerator-sao-paulo-20170804.html).

Tecnicamente, o nosso maior desafio hoje é estar preparado para atender a todos os nossos clientes, que além de muitos, são grandes em número de requisições (Americanas, Submarino, Shoptime, Lojas Renner, Boticário, Livraria Cultura, Magazine Luize, etc), totalizando mais de meio bilhão de requisições por mês.

Para isso, organizamos nosso sistema em micro serviços na AWS com Docker e Kubernetes, utilizando Java 8, Spring 4 (principalmente spring-boot), PostgreSQL, ElasticSearch e Redis. Temos um frontend para acesso dos clientes desenvolvido Vue.JS e mobile apps utilizando o framework Ionic.

## O desafio

Como você pode ver, nosso maior desafio está na manutenção e otimização de aplicações que estejam prontas para atender um altíssimo volume de dados e transações, por este motivo, todos os membros da nossa equipe estão altamente comprometidos com estes objetivos, de robustez, escalabilidade e performance, e é exatamente isso que esperamos de você através da resolução destes dois desafios:

1) Imagine que hoje tenhamos um sistema de login e perfis de usuários. O sistema conta com mais de 10 milhões de usuários, sendo que temos um acesso concorrente de cerca de 5 mil usuários. Hoje a tela inicial do sistema se encontra muito lenta. Nessa tela é feita uma consulta no banco de dados para pegar as informações do usuário e exibi-las de forma personalizada. Quando há um pico de logins simultâneos, o carregamento desta tela fica demasiadamente lento. Na sua visão, como poderíamos iniciar a busca pelo problema, e que tipo de melhoria poderia ser feita?

## Resposta
  R.: Primeiramente existe uma sequencia de abordagens que devem ser seguidas até chegar ao problema real.
A primeira abordagem é verificar como as tabelas estão normalizadas, o ideal é que exista no máximo 3 tabelas, sendo que uma servirá como relacionamento.
As tabelas serão as seguintes: LOGIN onde conterá o usuario e seu id, PROFILE e uma tabela de ligação N para N, pois dependendo do sistema o mesmo usuário poderá ter perfis diferentes.
Precisa verificar também como está o acesso ao banco, ou seja, se a query que faz a busca pelos usuários está com a melhor performance possível, podemos executar uma explain query para verificar o plano de execução da query no banco.
Precisa analisar também qual é a forma em que está sendo acessado o banco de dados, se for através de um dataSource precisa verificar se ele está com o número de conexões adequadas, se houver necessidade, aumentar o pool de conexões para que seja compatível com a quantidade de conexões no horário de pico.
Se a normalização estiver correta, a performance da query estiver em sua forma mais eficiente e o dataSource estiver com a quantidade ideal de conexões, precisamos verificar como Java está tratando o retorno do banco nos resultSets. 
Verificar se não está acontecendo muito parse nas informações retornadas do banco, o ideal seria trazer o resultado do banco e já criar no objeto que irá ser mostrado na tela sem que haja muito processamento.
Analisar o comportamento do controller que está sendo utilizado para fazer o login, verificar se há algum tratamento de entrada de dados que pode ser feito no proprio browser, via javascript, para evitar requisição, verificar a configuração do HTTP Pooler (depende para cada aplication server ou container), pois essa configuração é essencial para uma boa performance, se não estiver corretamente configurado, chegará uma hora que as requisições serão enfileiradas e começaram a ser recusadas podendo provocar um ataque DDoS.
Por ultimo verificar o comportamento da página html, se antes de fazer a requisição ela não esteja fazendo algo de anormal ou se a requisição esteja sendo feita de forma adequada ou se esta sendo utilizado algum framework que esteja com comportamento inadequado.


2) Com base no problema anterior, gostaríamos que você codificasse um novo sistema de login para muitos usuários simultâneos e carregamento da tela inicial. Lembre-se que é um sistema web então teremos conteúdo estático e dinâmico. Leve em consideração também que na empresa existe um outro sistema que também requisitará os dados dos usuários, portanto, este sistema deve expor as informações para este outro sistema de alguma maneira.

### O que nós esperamos do seu teste

* O código deverá ser hospedado em algum repositório público. Diversos quesitos serão avaliados aqui, como organização do código, sequencialidade de commits, nomeação de classes e métodos, etc.
* O código deverá estar pronto para ser executado e testado, portanto, caso exista algum requisito, este deve estar completamente documentado no README do seu projeto.
* Esperamos também alguma explicação sobre a solução, que pode ser em comentários no código, um texto escrito ou até um vídeo narrativo explicando a abordagem utilizada. 
* Você deverá utilizar a nossa stack de tecnologias descritas no início deste documento (Java 8 + Spring boot + Spring MVC).

### O que nós ficaríamos felizes de ver em seu teste

* Testes
* Processo de build e deploy documentado
* Ver o código rodando live (em qualquer serviço por aí)

### O que nós não gostaríamos

* Descobrir que não foi você quem fez seu teste
* Ver commits grandes, sem muita explicação nas mensagens em seu repositório 

## O que avaliaremos de seu teste

* Histórico de commits do git
* As instruções de como rodar o projeto
* Organização, semântica, estrutura, legibilidade, manutenibilidade do seu código
* Alcance dos objetivos propostos
* Escalabilidade da solução adotada 
