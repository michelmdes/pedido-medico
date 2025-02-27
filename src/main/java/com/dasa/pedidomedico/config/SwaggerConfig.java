package com.dasa.pedidomedico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Header;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Michel Mendes
 * @Since 11/11/2020
 * Classe de configuracao do Swagger, gerador de documentacao
 * Para acessar a documentacao: http://localhost:8080/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private final ResponseMessage m201 = customMessage1();
	private final ResponseMessage m204put = simpleMessage(204, "Atualização ok");
	private final ResponseMessage m204del = simpleMessage(204, "Deleção ok");
	private final ResponseMessage m403 = simpleMessage(403, "Não autorizado");
	private final ResponseMessage m404 = simpleMessage(404, "Não encontrado");
	private final ResponseMessage m422 = simpleMessage(422, "Erro de validação");
	private final ResponseMessage m500 = simpleMessage(500, "Erro inesperado");
 
	@Bean
	public Docket detalheApi() {
 
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
 
		docket.useDefaultResponseMessages(false)
		.globalResponseMessage(RequestMethod.GET, Arrays.asList(m403, m404, m500))
		.globalResponseMessage(RequestMethod.POST, Arrays.asList(m201, m403, m422, m500))
		.globalResponseMessage(RequestMethod.PUT, Arrays.asList(m204put, m403, m404, m422, m500))
		.globalResponseMessage(RequestMethod.DELETE, Arrays.asList(m204del, m403, m404, m500))
		
		.select()
		.apis(RequestHandlerSelectors.basePackage("com.dasa.pedidomedico"))
		.paths(PathSelectors.any())
		.build()
		.apiInfo(this.informacoesApi().build());
 
		return docket;
	}
 
	private ApiInfoBuilder informacoesApi() {
 
		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
 
		apiInfoBuilder.title("API REST - Pedido Médico");
		apiInfoBuilder.description("Desafio Dasa - API REST com as funcionalidades de cadastro de paciente, médico, exame e pedido de exame");
		apiInfoBuilder.version("1.0.0");
		// apiInfoBuilder.termsOfServiceUrl("Termo de uso: .");
		apiInfoBuilder.license("Licença - @michelmdes");
		apiInfoBuilder.licenseUrl("https://www.linkedin.com/in/michel-mendes-893a62a6/");
		apiInfoBuilder.contact(this.contato());
 
		return apiInfoBuilder;
	}
	
	private Contact contato() {
 
		return new Contact(
				"Michel Mendes",
				"https://www.linkedin.com/in/michel-mendes-893a62a6/", 
				"michelmdes@gmail.com");
	}
	
	private ResponseMessage simpleMessage(int code, String msg) {
		return new ResponseMessageBuilder().code(code).message(msg).build();
	}
	
	private ResponseMessage customMessage1() {
		
		Map<String, Header> map = new HashMap<>();
		map.put("location", new Header("location", "URI do novo recurso", new ModelRef("string")));
		
		return new ResponseMessageBuilder()
		.code(201)
		.message("Recurso criado")
		.headersWithDescription(map)
		.build();
	}
}
