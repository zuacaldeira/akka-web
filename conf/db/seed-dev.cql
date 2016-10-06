CREATE
   (zua: User{
        fullname:"Alexandre Zua Caldeira",
        email:"zuacaldeira@gmail.com"}),
   (azua:Account{
        username:"zuacaldeira@gmail.com",
        password:"Unicidade17!/"}),
   (elisa: User{
        fullname:"Elisa Sá",
        email:"elisasa@gmail.com"}),
   (aelisa:Account{
        username:"elisasa@gmail.com",
        password:"Unicidade17!/"}),

   (zua)-[rz:REGISTERED_AS]->(azua),
   (zua)-[lz:LOGIN_AS]->(azua),

   (elisa)-[re:REGISTERED_AS]->(aelisa),
   (elisa)-[le:LOGIN_AS]->(aelisa),


   (p1:Project{
            title:"O meu primeiro projecto",
            description:"Este é o meu primeiro pojecto,  projecto teste."}),
   (p2:Project{
            title:"O meu segundo projecto",
            description:"Este é 1o meu segundo projecto teste."}),

   (zua)-[w1:WORKING_ON]->(p1),
   (elisa)-[w2:WORKING_ON]->(p2),

   (p1)<-[:SUB_PROJECT]-(p2)
;

