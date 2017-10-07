# QDA-Core

TCC - Webservice aplicado ao consumo de dados.

## Ambiente
- JDK 8 (Obrigatório)
- Tomcat 8.5 (Obrigatório)

## Rota de teste
- [POST] http://localhost/qdacore/api/config
- [GET] http://localhost/qdacore/api/result
 - Recebe um Authorization Header com um token de autenticação.
 - Recebe queries que substituirão as variáveis entre {{ }}

- Exemplo de body no POST:
```javascript
{
	"serie": {
		"url": "http://api.tvmaze.com/singlesearch/shows?q={{title}}"
	},
	"seasons": {
		"url": "http://api.tvmaze.com/shows/{{serieId}}/seasons"
	},
	"episodeByNumber": {
		"url": "http://api.tvmaze.com/shows/{{serieId}}/episodebynumber?season={{seasonNumber}}&number={{episodeNumber}}"
	},
	"crew": {
		"url": "http://api.tvmaze.com/shows/{{serieId}}/crew"
	},
	"cast": {
		"url": "http://api.tvmaze.com/shows/{{serieId}}/cast"
	},
	"episodeByDate": {
		"url": "http://api.tvmaze.com/shows/{{serieId}}/episodesbydate?date={{searchDate}}"
	}
}
```

```javascript
{
  token: "DFJqqIBMqFF0x44KNqBEIsCClA6JMGAD2CHnIq5LDBnyQTVQ6eO3GvSuJJ59iClQUJwLEe8fDgJcOMOOJgUzFx4F"
}
```

- Exemplo de retorno do GET:
```javascript
{
  "<data>": [Object],
  "requestErrors": [
      "episodeByDate"
  ],
  "bulkResponse": false,
  "providedBy": "QDA Core Service",
  "creators": "Guilherme Vasconcellos and Vitor Garcia"
}
```
