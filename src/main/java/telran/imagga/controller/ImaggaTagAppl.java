package telran.imagga.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import telran.imagga.dto.ResponseDto;

public class ImaggaTagAppl {
	final static String TOKEN = "Basic YWNjX2ZlMTk5MjY0YmEyNTRkNzo0NDVjYjUyM2NiZjg5MGRiYzAxYTU3YjVhYTFlNzM3Yw==";
	static RestTemplate restTemplate = new RestTemplate();
	
	public static void main(String[] args) {
		String httpUrl = "https://api.imagga.com/v2/tags";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
				.queryParam("image_url", "https://imagga.com/static/images/tagging/wind-farm-538576_640.jpg")
	//			.queryParam("language", "ru")
				.queryParam("limit", 3);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", TOKEN);
		RequestEntity<String> requestEntity = 
				new RequestEntity<String>(headers , HttpMethod.GET, builder.build().toUri());
		ResponseEntity<ResponseDto> responseEntity =
				restTemplate.exchange(requestEntity, ResponseDto.class);
		responseEntity.getBody().getResult().getTags().forEach(System.out::println);
	}

}
