package fatec.rest.services;

import fatec.rest.interfaces.QDAParameterizable;

public abstract class HttpService {

	public static void getInfo(QDAParameterizable params, String... endpoints) {
		//Chama Reader e pega rotas
		//Utiliza o metodo .toString de endpoints para achar os valores no Map
		//Substitui os parametros na url de requisicao
		//Faz a requisicao e pega os Jsons em um array
		//Trata os arrays
	}
	
}
