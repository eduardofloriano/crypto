# crypto


Desafio Crypto Game
Objetivo: 

Decifrar programaticamente uma string de tamanho N.
Neste desafio você precisa construir um programa em Java para decodificar uma string gerada exclusivamente para o seu programa. Para isto seu aplicativo precisa fazer uma chamada para o endpoint:

HTTP GET https://ac-challenge.herokuapp.com/api/challenge

enviando uma string e o retorno desta chamada vai lhe informar se a string está correta ou não. Este serviço possui os seguintes parâmetros:

coder - deve ser o seu e-mail e maior do que 10 caracteres.
challenge - a string 
test - "true" ou "false"
Como já informado, a string que seu programa deve decodificar é exclusiva, para saber o tamanho desta, faça uma chamada sem enviar nenhum valor no parâmetro challenge, a resposta do serviço conterá o tamanho da string que seu aplicativo deverá decifrar. Os caracteres válidos para a string atendem à expressão regular [a-zA-Z0-9_=]

As respostas esperadas do serviço são as seguintes:
Código

Mensagem

Observações

200OKString decodificada
206Partial ContentRequest está correto, a string ainda não foi decifrada, olhar resposta do serviço para maiores informações
400Bad RequestAlgum parâmetro pode estar faltando na chamada do endpoint. O parâmetro coder deve ser maior que 10 caracteres.
409ConflictO tamanho da string challenge enviada é diferente da esperada.
Ao enviar o request corretamente, o serviço irá retornar uma outra string com o mesmo tamanho da string do desafio contendo um criptograma que pode ser lido conforme regra abaixo:
• Uma letra "W" indica que o caracter enviado naquela posição não está presente na string.
• Uma letra "U" indica que o caracter enviado naquela posição está presente na string mas está na posição errada.
• Uma letra "R" indica que o caracter enviado naquela posição está presente na string e está na posição correta.
Exemplo: Seu aplicativo enviou a string "AZDEGDE23", a API sabe que seu objetivo é "ABCDEF123" portanto vai lhe enviar a resposta "RWUUWUURR".

Ao enviar a string correta o serviço retorna resposta 200 e o link para sua inscrição no hackathon.

Mas espere, não é só isto, tenha certeza de fazer isto em um momento propício, pois a partir da primeira chamada ao serviço, você terá um intervalo de 3 horas para conseguir decifrar a string, portanto não faça isso na hora de Game of Thrones.

Não se esqueça de usar o parâmetro test=true durante o desenvolvimento, assim seu programa pode fazer quantas chamadas precisar para decifrar a mensagem. Obviamente para receber o link de inscrição este parâmetro deve ser falso (test=false), quando você fizer isso seu aplicativo poderá fazer somente 25 chamadas para o servidor.

Let the games begin!
