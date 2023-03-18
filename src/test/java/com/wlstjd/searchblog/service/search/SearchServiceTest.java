package com.wlstjd.searchblog.service.search;

import com.wlstjd.searchblog.persist.SearchWordRepo;
import com.wlstjd.searchblog.service.search.dto.SearchServiceResponse;
import com.wlstjd.searchblog.service.search.openapi.BlogOpenApi;
import com.wlstjd.searchblog.service.search.openapi.kakao.BlogOpenApiWrapperKakaoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SearchServiceTest {

    @Autowired
    private SearchWordRepo searchWordRepo;
    @Test
    @DisplayName("기본적인 첫 검색에 대한 테스트")
    public void defaultSearchTest() {
        // given
        BlogOpenApi blogOpenApi = Mockito.mock(BlogOpenApi.class);
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "KakaoAK f5aca1c30f55e20e989e8d0475a92956");
        Mockito.when(blogOpenApi.get(headers, "GET", "https://dapi.kakao.com/v2/search/blog?size=10&query=abc&sort=accuracy&page=1"))
                .thenReturn("{\"documents\":[{\"blogname\":\"행복한 꿈\",\"contents\":\"2013년 4월 1일 \\u003cb\\u003eABC\\u003c/b\\u003e 4130m-MBC 3700m-시누와 2360m 어제저녁 두통이 심했는데 자고 나니 두통이 사라져 다행이다. 아침에 일어나니 온천지가 하얀 눈으로 뒤덮였다. 밤새 제법 눈이 많이 내렸다. 걷기에는 그리 힘들지 않을 정도로 눈이 쌓였다. 아침 날씨는 매우 좋았다. 새벽 해맞이를 하기 위해 일찍 일어나 로지뒤쪽...\",\"datetime\":\"2023-02-26T17:09:01.000+09:00\",\"thumbnail\":\"https://search3.kakaocdn.net/argon/130x130_85_c/3d2UF9rNyhL\",\"title\":\"네팔 \\u003cb\\u003eABC\\u003c/b\\u003e 트레킹 \\u003cb\\u003eABC\\u003c/b\\u003e-시누와\",\"url\":\"http://jeon4028.tistory.com/149\"},{\"blogname\":\"행복한 꿈\",\"contents\":\"2013년 3월 31일 데우랄리 3200m-MBC 3700m-\\u003cb\\u003eABC\\u003c/b\\u003e 4130m 아침 8:00 출발 930m 고도를 높이는 일정이다. 걱정이 앞선다. 지난번 쿰부트레킹에서는 남체에서 고생을 했는데 오늘 걷는 코스가 딱 그 높이의 코스기 때문이다. 아침에 일어나니 약간의 두통이 있었다. 박은 어제저녁 락시를 큰 잔으로 두 잔이나 먹고 잤는데...\",\"datetime\":\"2023-02-26T16:53:54.000+09:00\",\"thumbnail\":\"https://search4.kakaocdn.net/argon/130x130_85_c/C9Mk0yz0OYw\",\"title\":\"네팔 \\u003cb\\u003eABC\\u003c/b\\u003e 트레킹 데우랄리-\\u003cb\\u003eABC\\u003c/b\\u003e\",\"url\":\"http://jeon4028.tistory.com/148\"},{\"blogname\":\"산타는 해추리\",\"contents\":\"다녀왔습니다. 금요일 하루 휴가를 내고 카트만두에서 출발, 포카라행 첫 비행기를 타고 공항에서 뉴브릿지행 지프차를 갈아탄 후 도반(어퍼도반)에서 1박, \\u003cb\\u003eABC\\u003c/b\\u003e에서 2박을 한 후 다음날 뉴브릿지로 돌아와 다시 지프차를 타고 포카라로 돌아왔습니다. (1일차) 카트만두 - 포카라 - 뉴브릿지 - 지누단다 - 촘롱 - 시누와...\",\"datetime\":\"2023-03-14T13:59:53.000+09:00\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/DMopru4Asix\",\"title\":\"[\\u003cb\\u003eABC\\u003c/b\\u003e] 폭설내린 안나푸르나베이스캠프 3일만에 다녀오기\",\"url\":\"http://haechuri.tistory.com/178\"},{\"blogname\":\"ae.rrang log ,\",\"contents\":\"하고 있어서 내장지방 레벨이 6~7에서 왔다갔다하고 더 이상 안 내려가더라고요 ㅠㅠ \u200B \u200B 그래서 디톡스에 대해서 알아보다가 예전에 먹고 효과를 많이 봤던 \\u003cb\\u003eABC\\u003c/b\\u003e 주스가 생각나서 다시 꾸준히 먹어보기로 했어요 ㅎㅎ \u200B \u200B \\u003cb\\u003eABC\\u003c/b\\u003e 주스는 \u200B Apple 사과 Beet 비트 carrot 당근 \u200B 세 가지 재료의 앞 글자를 딴 거예요 ㅎㅎ 이미 너무...\",\"datetime\":\"2023-03-09T17:18:00.000+09:00\",\"thumbnail\":\"https://search1.kakaocdn.net/argon/130x130_85_c/Ar1ty0Pu5dW\",\"title\":\"[다이어트] 내장지방 뿌셔보자 ‘\\u003cb\\u003eABC\\u003c/b\\u003e 주스 만들기’\",\"url\":\"https://blog.naver.com/aerrang/223039655593\"},{\"blogname\":\"tinker의 일상\",\"contents\":\"건강을 해치면 안되잖아요 \u200B 그래서 건강을 위해 디톡스 주스를 주문해 보았어요 여러가지 디톡스 주스중에 건강에 좋은 주스로 유명한 \\u003cb\\u003eABC\\u003c/b\\u003e 주스를 선택해 보았답니다 \u200B 비움 클렌즈 \\u003cb\\u003eABC\\u003c/b\\u003e 주스 \u200B 드디어 배송이 왔네요 \u200B 과육 그대로 갈아 만들었다는 비움 클렌즈 \\u003cb\\u003eABC\\u003c/b\\u003e 주스에요 A - Apple B - Beet C - Carrot 사과, 비트...\",\"datetime\":\"2023-03-16T22:15:00.000+09:00\",\"thumbnail\":\"https://search4.kakaocdn.net/argon/130x130_85_c/LL7G21nFvww\",\"title\":\"비움클렌즈주스/ \\u003cb\\u003eABC\\u003c/b\\u003e주스/ 클렌즈 주스/ 디톡스 주스\",\"url\":\"https://blog.naver.com/omj0179/223046834789\"},{\"blogname\":\"유일한 나만의 시간\",\"contents\":\"\u200B 안녕하세요, 자유부인이고 싶은 자유부인 입니다:D \u200B \\u003cb\\u003eABC\\u003c/b\\u003e주스 아시는 분 손\uD83D\uDE4B\uD83C\uDFFB\u200D♀️ \\u003cb\\u003eABC\\u003c/b\\u003e주스는 사과•비트•당근 앞글자를 따서 만든 맛과 영양 모두 가진 주스입니다\uD83D\uDE0F \u200B 이미 SNS에서는 핫\uD83D\uDD25 하다고 하는데요 찾아보니 저 같은 사람한테 꼭 필요한주스더라구요?\u200B\u200B \u200B \u200B 오늘 소개해드리는 제품 비움 클렌즈주스 \\u003cb\\u003eABC\\u003c/b\\u003e\u200B\u200B \u200B \u200B 두근두근...\",\"datetime\":\"2023-02-21T07:32:00.000+09:00\",\"thumbnail\":\"https://search1.kakaocdn.net/argon/130x130_85_c/1R2wz13Hami\",\"title\":\"더상점\uD83C\uDFE0 : 비움 클렌즈주스 \\u003cb\\u003eABC\\u003c/b\\u003e주스\",\"url\":\"https://blog.naver.com/hy92jung/223022541644\"},{\"blogname\":\"지애로그\",\"contents\":\"있어 갱년기 여성에게도 좋은 것으로 알려져 있는데 저도 피가 부족한지라 비트가 몸에 너무 좋을 것 같아요. 보기만 해도 혈액이 생성될 것 같은 기분. \u200B \u200B \u200B \u200B \\u003cb\\u003eABC\\u003c/b\\u003e 주스 재료로 알려진 비트이지만 의외로 요리의 재료로도 많이 사용이 되고 있었어요. 생채나 샐러드로도 튀김으로도 장아찌를 담을 때도 예쁜 색감을 위해...\",\"datetime\":\"2023-03-16T20:32:00.000+09:00\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/GDiwLHhWiPp\",\"title\":\"비트손질 하고 \\u003cb\\u003eABC\\u003c/b\\u003e주스 만들기 참 쉽죠\",\"url\":\"https://blog.naver.com/jasarang01/223046747763\"},{\"blogname\":\"쥬니랑핑크랜드\",\"contents\":\"부산 세차장 \\u003cb\\u003eABC\\u003c/b\\u003e스팀세차장 \u200B \u200B 저는 셀프세차보다 스팀세차를 주기적으로 하는편인데요 세차 하기 전날인가 몇일전에 흙비가 왔었어요 황사비인줄 알았는데 흙비라 하더라구요 흙비를 맞은 모든차들이 다 더러워 졌다는 ㅜ 기계세차장에도 줄이 어마어마 하더라구요 그래서 저는 바로 \\u003cb\\u003eABC\\u003c/b\\u003e스팀세차장 본점으로 예약하고...\",\"datetime\":\"2023-02-25T09:00:00.000+09:00\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/BMF0EyO66Ob\",\"title\":\"부산 세차장 \\u003cb\\u003eABC\\u003c/b\\u003e스팀세차장\",\"url\":\"https://blog.naver.com/bebejjuni/223026940296\"},{\"blogname\":\"안젤라코 골프월드\",\"contents\":\"\\u003cb\\u003eABC\\u003c/b\\u003e주스 추천 :: 클렌즈주스 라티브 \\u003cb\\u003eABC\\u003c/b\\u003e주스 안녕하세요 맛있는거 먹으려고 운동하는 여자 안젤라코예요:) 연말과 연초에 약속이 많아서 해비한 음식을 많이 먹고 다녔어요... 저만 그런거아니죠?! 맛있게 먹으려고 운동하는 여자지만, 체중 감량과 안색 개선을 위해 클렌즈 주스로 단기 다이어트 하고있어요. 평생유지...\",\"datetime\":\"2023-02-20T13:42:00.000+09:00\",\"thumbnail\":\"https://search4.kakaocdn.net/argon/130x130_85_c/5yLTVOn5A0m\",\"title\":\"다이어트 필수템 \\u003cb\\u003eABC\\u003c/b\\u003e주스로 클렌즈 관리 시작했어요.\",\"url\":\"https://blog.naver.com/kmj8637/223021647599\"},{\"blogname\":\"#surfing #swimming #review #mukbang\",\"contents\":\"오늘은 미루고 미루던 세차하는 날! 잔기스 없이 깔끔하게 세차한다고 소문난 \\u003cb\\u003eABC\\u003c/b\\u003e스팀세차장 정관점에서 세차를 해보자!\u200B \u200B (내가 적은 포스팅 중 가장 긴 포스팅이 될 것 같다.) Let\\u0026#39;s GoGo!! 위치 부산광역시 기장군 정관읍 구연방곡로 48 \\u003cb\\u003eABC\\u003c/b\\u003e스팀세차장 정관점 예약전화 : 051-728-5111 \\u003cb\\u003eABC\\u003c/b\\u003e스팀세차장 정관점 부산...\",\"datetime\":\"2023-02-27T23:57:00.000+09:00\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/DUqLFw4VETL\",\"title\":\"부산세차장 \\u0026lt;\\u003cb\\u003eABC\\u003c/b\\u003e스팀세차장 정관\\u0026gt; 부산 스팀세차\",\"url\":\"https://blog.naver.com/hajung_zz/223029792071\"}],\"meta\":{\"is_end\":false,\"pageable_count\":790,\"total_count\":780366}}");
        SearchService searchService = new SearchService(searchWordRepo, new BlogOpenApiWrapperKakaoImpl(blogOpenApi));
        // when
        SearchServiceResponse response = searchService.search("abc", Sorting.ACCURACY, 1, 10, true);

        // then
        Assertions.assertNotNull(response);
        Assertions.assertEquals(10, response.documents().size());
        Assertions.assertEquals(1, response.page());
        Assertions.assertEquals(10, response.size());
        Assertions.assertFalse(response.isEnd());
    }
}