package com.wlstjd.searchblog.service.search.openapi.naver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wlstjd.searchblog.service.search.Sorting;
import com.wlstjd.searchblog.service.search.openapi.BlogOpenApi;
import com.wlstjd.searchblog.service.search.openapi.BlogOpenApiWrapper;
import com.wlstjd.searchblog.service.search.openapi.dto.Document;
import com.wlstjd.searchblog.service.search.openapi.dto.Meta;
import com.wlstjd.searchblog.service.search.openapi.dto.OpenApiResponse;
import com.wlstjd.searchblog.service.search.openapi.naver.dto.Item;
import com.wlstjd.searchblog.service.search.openapi.naver.dto.Rss;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BlogOpenApiWrapperNaverImpl extends BlogOpenApiWrapper {
    @Value("${naver.api.url}")
    private String apiUrl;
    @Value("${naver.api.id}")
    private String naverId;
    @Value("${naver.api.Secret}")
    private String naverSecret;

    public BlogOpenApiWrapperNaverImpl(BlogOpenApi blogOpenApi) {
        super(blogOpenApi);
    }

    @Override
    protected String getUri() {
        return apiUrl;
    }

    public OpenApiResponse makeResponseInstance(String response) {
        Rss result;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            result = objectMapper.readValue(response, Rss.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Json Object Mapper Failed");
        }
        return openApiResponseAdapter(result);
    }
    private static OpenApiResponse openApiResponseAdapter(Rss rss) {
        Meta meta = metaAdapter(rss);
        List<Document> documents = rss.channel().items().stream()
                .map(BlogOpenApiWrapperNaverImpl::documentAdapter)
                .toList();
        return new OpenApiResponse(meta, documents);
    }
    private static Meta metaAdapter(Rss rss) {
        return new Meta(rss.channel().total(), rss.channel().display(),
                rss.channel().total().equals(rss.channel().start()));
    }
    private static Document documentAdapter(Item item) {
        return new Document(item.title(), item.description(), item.link(),
                item.bloggername(), "", item.postdate());
    }

    public Map<String, String> collectRequestHeader() {
        Map<String, String> header = new HashMap<>();
        header.put("X-Naver-Client-Id", naverId);
        header.put("X-Naver-Client-Secret", naverSecret);
        return header;
    }

    public Map<String, String> collectRequestBody(String keyword, Sorting sorting, Integer page, Integer size) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("query", keyword);
        requestBody.put("sort", sorting.getNaverExpr());
        requestBody.put("start", page.toString());
        requestBody.put("display", size.toString());
        return requestBody;
    }
}
