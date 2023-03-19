package com.wlstjd.searchblog.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wlstjd.searchblog.service.search.openapi.OpenApiCaller;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.web.servlet.MockMvc;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ControllerSearchBlogTest {
    @MockBean
    private OpenApiCaller openApiCaller;
    @Autowired
    private MockMvc mockMvc;
    @Value("${kakao.api.url}")
    private String apiUrl;
    @Value("${kakao.api.token}")
    private String token;
    @BeforeEach
    public void init() throws SocketTimeoutException {
        // given
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", token);
        Mockito.when(openApiCaller.get(headers, "GET", apiUrl + "?size=10&query=abc&sort=accuracy&page=1"))
                .thenReturn("{\"documents\":[{\"blogname\":\"행복한 꿈\",\"contents\":\"2013년 4월 1일 \\u003cb\\u003eABC\\u003c/b\\u003e 4130m-MBC 3700m-시누와 2360m 어제저녁 두통이 심했는데 자고 나니 두통이 사라져 다행이다. 아침에 일어나니 온천지가 하얀 눈으로 뒤덮였다. 밤새 제법 눈이 많이 내렸다. 걷기에는 그리 힘들지 않을 정도로 눈이 쌓였다. 아침 날씨는 매우 좋았다. 새벽 해맞이를 하기 위해 일찍 일어나 로지뒤쪽...\",\"datetime\":\"2023-02-26T17:09:01.000+09:00\",\"thumbnail\":\"https://search3.kakaocdn.net/argon/130x130_85_c/3d2UF9rNyhL\",\"title\":\"네팔 \\u003cb\\u003eABC\\u003c/b\\u003e 트레킹 \\u003cb\\u003eABC\\u003c/b\\u003e-시누와\",\"url\":\"http://jeon4028.tistory.com/149\"},{\"blogname\":\"행복한 꿈\",\"contents\":\"2013년 3월 31일 데우랄리 3200m-MBC 3700m-\\u003cb\\u003eABC\\u003c/b\\u003e 4130m 아침 8:00 출발 930m 고도를 높이는 일정이다. 걱정이 앞선다. 지난번 쿰부트레킹에서는 남체에서 고생을 했는데 오늘 걷는 코스가 딱 그 높이의 코스기 때문이다. 아침에 일어나니 약간의 두통이 있었다. 박은 어제저녁 락시를 큰 잔으로 두 잔이나 먹고 잤는데...\",\"datetime\":\"2023-02-26T16:53:54.000+09:00\",\"thumbnail\":\"https://search4.kakaocdn.net/argon/130x130_85_c/C9Mk0yz0OYw\",\"title\":\"네팔 \\u003cb\\u003eABC\\u003c/b\\u003e 트레킹 데우랄리-\\u003cb\\u003eABC\\u003c/b\\u003e\",\"url\":\"http://jeon4028.tistory.com/148\"},{\"blogname\":\"산타는 해추리\",\"contents\":\"다녀왔습니다. 금요일 하루 휴가를 내고 카트만두에서 출발, 포카라행 첫 비행기를 타고 공항에서 뉴브릿지행 지프차를 갈아탄 후 도반(어퍼도반)에서 1박, \\u003cb\\u003eABC\\u003c/b\\u003e에서 2박을 한 후 다음날 뉴브릿지로 돌아와 다시 지프차를 타고 포카라로 돌아왔습니다. (1일차) 카트만두 - 포카라 - 뉴브릿지 - 지누단다 - 촘롱 - 시누와...\",\"datetime\":\"2023-03-14T13:59:53.000+09:00\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/DMopru4Asix\",\"title\":\"[\\u003cb\\u003eABC\\u003c/b\\u003e] 폭설내린 안나푸르나베이스캠프 3일만에 다녀오기\",\"url\":\"http://haechuri.tistory.com/178\"},{\"blogname\":\"ae.rrang log ,\",\"contents\":\"하고 있어서 내장지방 레벨이 6~7에서 왔다갔다하고 더 이상 안 내려가더라고요 ㅠㅠ \u200B \u200B 그래서 디톡스에 대해서 알아보다가 예전에 먹고 효과를 많이 봤던 \\u003cb\\u003eABC\\u003c/b\\u003e 주스가 생각나서 다시 꾸준히 먹어보기로 했어요 ㅎㅎ \u200B \u200B \\u003cb\\u003eABC\\u003c/b\\u003e 주스는 \u200B Apple 사과 Beet 비트 carrot 당근 \u200B 세 가지 재료의 앞 글자를 딴 거예요 ㅎㅎ 이미 너무...\",\"datetime\":\"2023-03-09T17:18:00.000+09:00\",\"thumbnail\":\"https://search1.kakaocdn.net/argon/130x130_85_c/Ar1ty0Pu5dW\",\"title\":\"[다이어트] 내장지방 뿌셔보자 ‘\\u003cb\\u003eABC\\u003c/b\\u003e 주스 만들기’\",\"url\":\"https://blog.naver.com/aerrang/223039655593\"},{\"blogname\":\"tinker의 일상\",\"contents\":\"건강을 해치면 안되잖아요 \u200B 그래서 건강을 위해 디톡스 주스를 주문해 보았어요 여러가지 디톡스 주스중에 건강에 좋은 주스로 유명한 \\u003cb\\u003eABC\\u003c/b\\u003e 주스를 선택해 보았답니다 \u200B 비움 클렌즈 \\u003cb\\u003eABC\\u003c/b\\u003e 주스 \u200B 드디어 배송이 왔네요 \u200B 과육 그대로 갈아 만들었다는 비움 클렌즈 \\u003cb\\u003eABC\\u003c/b\\u003e 주스에요 A - Apple B - Beet C - Carrot 사과, 비트...\",\"datetime\":\"2023-03-16T22:15:00.000+09:00\",\"thumbnail\":\"https://search4.kakaocdn.net/argon/130x130_85_c/LL7G21nFvww\",\"title\":\"비움클렌즈주스/ \\u003cb\\u003eABC\\u003c/b\\u003e주스/ 클렌즈 주스/ 디톡스 주스\",\"url\":\"https://blog.naver.com/omj0179/223046834789\"},{\"blogname\":\"유일한 나만의 시간\",\"contents\":\"\u200B 안녕하세요, 자유부인이고 싶은 자유부인 입니다:D \u200B \\u003cb\\u003eABC\\u003c/b\\u003e주스 아시는 분 손\uD83D\uDE4B\uD83C\uDFFB\u200D♀️ \\u003cb\\u003eABC\\u003c/b\\u003e주스는 사과•비트•당근 앞글자를 따서 만든 맛과 영양 모두 가진 주스입니다\uD83D\uDE0F \u200B 이미 SNS에서는 핫\uD83D\uDD25 하다고 하는데요 찾아보니 저 같은 사람한테 꼭 필요한주스더라구요?\u200B\u200B \u200B \u200B 오늘 소개해드리는 제품 비움 클렌즈주스 \\u003cb\\u003eABC\\u003c/b\\u003e\u200B\u200B \u200B \u200B 두근두근...\",\"datetime\":\"2023-02-21T07:32:00.000+09:00\",\"thumbnail\":\"https://search1.kakaocdn.net/argon/130x130_85_c/1R2wz13Hami\",\"title\":\"더상점\uD83C\uDFE0 : 비움 클렌즈주스 \\u003cb\\u003eABC\\u003c/b\\u003e주스\",\"url\":\"https://blog.naver.com/hy92jung/223022541644\"},{\"blogname\":\"지애로그\",\"contents\":\"있어 갱년기 여성에게도 좋은 것으로 알려져 있는데 저도 피가 부족한지라 비트가 몸에 너무 좋을 것 같아요. 보기만 해도 혈액이 생성될 것 같은 기분. \u200B \u200B \u200B \u200B \\u003cb\\u003eABC\\u003c/b\\u003e 주스 재료로 알려진 비트이지만 의외로 요리의 재료로도 많이 사용이 되고 있었어요. 생채나 샐러드로도 튀김으로도 장아찌를 담을 때도 예쁜 색감을 위해...\",\"datetime\":\"2023-03-16T20:32:00.000+09:00\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/GDiwLHhWiPp\",\"title\":\"비트손질 하고 \\u003cb\\u003eABC\\u003c/b\\u003e주스 만들기 참 쉽죠\",\"url\":\"https://blog.naver.com/jasarang01/223046747763\"},{\"blogname\":\"쥬니랑핑크랜드\",\"contents\":\"부산 세차장 \\u003cb\\u003eABC\\u003c/b\\u003e스팀세차장 \u200B \u200B 저는 셀프세차보다 스팀세차를 주기적으로 하는편인데요 세차 하기 전날인가 몇일전에 흙비가 왔었어요 황사비인줄 알았는데 흙비라 하더라구요 흙비를 맞은 모든차들이 다 더러워 졌다는 ㅜ 기계세차장에도 줄이 어마어마 하더라구요 그래서 저는 바로 \\u003cb\\u003eABC\\u003c/b\\u003e스팀세차장 본점으로 예약하고...\",\"datetime\":\"2023-02-25T09:00:00.000+09:00\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/BMF0EyO66Ob\",\"title\":\"부산 세차장 \\u003cb\\u003eABC\\u003c/b\\u003e스팀세차장\",\"url\":\"https://blog.naver.com/bebejjuni/223026940296\"},{\"blogname\":\"안젤라코 골프월드\",\"contents\":\"\\u003cb\\u003eABC\\u003c/b\\u003e주스 추천 :: 클렌즈주스 라티브 \\u003cb\\u003eABC\\u003c/b\\u003e주스 안녕하세요 맛있는거 먹으려고 운동하는 여자 안젤라코예요:) 연말과 연초에 약속이 많아서 해비한 음식을 많이 먹고 다녔어요... 저만 그런거아니죠?! 맛있게 먹으려고 운동하는 여자지만, 체중 감량과 안색 개선을 위해 클렌즈 주스로 단기 다이어트 하고있어요. 평생유지...\",\"datetime\":\"2023-02-20T13:42:00.000+09:00\",\"thumbnail\":\"https://search4.kakaocdn.net/argon/130x130_85_c/5yLTVOn5A0m\",\"title\":\"다이어트 필수템 \\u003cb\\u003eABC\\u003c/b\\u003e주스로 클렌즈 관리 시작했어요.\",\"url\":\"https://blog.naver.com/kmj8637/223021647599\"},{\"blogname\":\"#surfing #swimming #review #mukbang\",\"contents\":\"오늘은 미루고 미루던 세차하는 날! 잔기스 없이 깔끔하게 세차한다고 소문난 \\u003cb\\u003eABC\\u003c/b\\u003e스팀세차장 정관점에서 세차를 해보자!\u200B \u200B (내가 적은 포스팅 중 가장 긴 포스팅이 될 것 같다.) Let\\u0026#39;s GoGo!! 위치 부산광역시 기장군 정관읍 구연방곡로 48 \\u003cb\\u003eABC\\u003c/b\\u003e스팀세차장 정관점 예약전화 : 051-728-5111 \\u003cb\\u003eABC\\u003c/b\\u003e스팀세차장 정관점 부산...\",\"datetime\":\"2023-02-27T23:57:00.000+09:00\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/DUqLFw4VETL\",\"title\":\"부산세차장 \\u0026lt;\\u003cb\\u003eABC\\u003c/b\\u003e스팀세차장 정관\\u0026gt; 부산 스팀세차\",\"url\":\"https://blog.naver.com/hajung_zz/223029792071\"}],\"meta\":{\"is_end\":false,\"pageable_count\":790,\"total_count\":780366}}");
        Mockito.when(openApiCaller.get(headers, "GET", apiUrl + "?size=10&query=abc&sort=accuracy&page=2"))
                .thenReturn("{\"documents\":[{\"blogname\":\"달달한 일상 리뷰 with U\",\"contents\":\"껍질까지 통째로 갈아만든 과육이 살아있는 주스 \u200B 안녕하세요. 밀리입니다 :) 요새 Miracle Juice 라고도 불릴만큼 <b>ABC</b> 주스가 건강에 좋다고 하는데요~. 재료 그대로를 갈아서 만든 비움클렌즈 <b>ABC</b> 주스를 소개합니다. 냉동 보관 제품이라서 꽁꽁 얼어서 배송이 됩니다. 꼼꼼하게 포장되어 있어 안심할 수 있어요. 배송...\",\"datetime\":\"2023-03-18T11:28:00.000+09:00\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/4aZEsr0koWi\",\"title\":\"건강한 [비움 클렌즈 <b>ABC</b> 주스]\",\"url\":\"https://blog.naver.com/kmiruk/223048177774\"},{\"blogname\":\"보조부부의 세계여행 Bonnie & Joe\",\"contents\":\"괌 쇼핑리스트 <b>ABC</b> 스토어에서 기념품 선물 사기 괌에서 사야 할 것들 총정리 괌에서는 뭘 사나요~~ 괌 쇼핑리스트 총정리!! 사실 괌은 기념품 선물 사기가 너무 수월하다. 바로 <b>ABC</b> 스토어만 가면 되기 때문!! 필요한 게 거기 다 있기 때문에 다른 데를 갈 필요가 없어서 너무너무 편하다. 괌 곳곳에 있어서 자기 숙소...\",\"datetime\":\"2023-03-04T11:46:00.000+09:00\",\"thumbnail\":\"https://search3.kakaocdn.net/argon/130x130_85_c/HLZmdgbm8BS\",\"title\":\"괌 쇼핑리스트 <b>ABC</b> 스토어에서 기념품 선물 사기\",\"url\":\"https://blog.naver.com/bonniegets/223034374168\"},{\"blogname\":\"이거뭐야\",\"contents\":\"<b>ABC</b>주스 효능 부작용 복용법을 알려드리도록 하겠습니다. 이 문서를 읽어주시면 <b>ABC</b>주스 효능 부작용 복용법을 알아두시는 데에 좋을 것입니다. <b>ABC</b>주스 효능 부작용 복용법의 지식이 필요하신 분들은 모두 읽어주세요. 아래의 포스트에서 정보를 드리겠습니다. <b>ABC</b>주스 효능 부작용 복용법 목 이물감 원인 보기 쉽도록...\",\"datetime\":\"2023-02-25T11:01:29.000+09:00\",\"thumbnail\":\"https://search1.kakaocdn.net/argon/130x130_85_c/oXsBmGfExl\",\"title\":\"<b>ABC</b>쥬스 효능 부작용 복용법 보기쉽도록 정리했어요.\",\"url\":\"http://evebrand.blessthebeasts.net/48\"},{\"blogname\":\"뚜기네\",\"contents\":\"\u200B 안녕하세요. 오늘의 포스팅 주제는 &#34;<b>ABC</b> 쥬스&#34; 입니다. [unsplash] 올해 들어서 제가 &#34;디톡스 쥬스&#34;에 관심이 많아져서, 만들어 먹기도 하고 구매해서 먹기도 하고 있는데요. 우리가 먹는 음식이나 생활 습관, 환경적 요인, 생활 속 유해 물질 등의 영향으로 인해 우리 몸에는 자연히 &#34;독소&#34;가 쌓일 수 밖에 없어요...\",\"datetime\":\"2023-02-20T08:10:00.000+09:00\",\"thumbnail\":\"https://search1.kakaocdn.net/argon/130x130_85_c/211up43d3jn\",\"title\":\"<b>ABC</b>쥬스 해독쥬스 만드는 법\",\"url\":\"https://blog.naver.com/jamie_ji/223019079612\"},{\"blogname\":\"say, hello!\",\"contents\":\"몸무게는 점점 불어나고…^^ 왜 자꾸 찔까? 라는 생각을 하지만, 뭘 자꾸 주워먹었겠지…ㅎ 안움직이는 건 너무 당연해서 할말도 없어요\uD83E\uDD23 그래서 도전한 <b>abc</b>주스! 이렇게 안전하고 튼실하게 포장해서 보내주신답니다. 거기다 아이스팩도 넣어져있고 꽁꽁 얼려져있답니다! 굉장히 꼼꼼하게 신경써주시네요. 만족만족...\",\"datetime\":\"2023-03-18T07:41:00.000+09:00\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/LPKr4JzF6n8\",\"title\":\"[<b>abc</b>주스] 맛과 건강, 그리고 다이어트까지!\",\"url\":\"https://blog.naver.com/kissdmldhkd/223048075915\"},{\"blogname\":\"해피라이프\",\"contents\":\"<b>ABC</b> 주스 효능 그리고 만드는 법 <b>ABC</b> 주스 효능 그리고 만드는 법 우선 <b>ABC</b> 주스가 어떠한 것인지, 왜 이러한 이름이 되었는지 알아볼게요. <b>ABC</b> 주스의 이름이 <b>ABC</b>인 이유는 사과(APPLE), 비트(BEAT), 당근(CARROT)의 앞글자를 따서 이름을 지은 것이라고 합니다. 디톡스 효과로 유명하여 해독주스라고도 불리고 있으며...\",\"datetime\":\"2023-01-02T21:52:44.000+09:00\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/L0bTnbEhZeJ\",\"title\":\"<b>ABC</b> 주스 효능 그리고 만드는 법\",\"url\":\"http://circle.magicpurplewhale.net/7\"},{\"blogname\":\"맛도리판별기_윤쨩\",\"contents\":\"안녕하세요 윤쨩입니다\uD83D\uDE0B <b>abc</b>쥬스효능 다들 알고계신가요!? 해독쥬스 만들어서 마시는거 한창 유행하다가 저도 그렇고 다들 만들기 귀찮으신지(ㅋㅋㅋ) 뜸했던거같아요\uD83D\uDE02 요새는 간편하게 마실수있게 팩으로도 나온다고해서 저도한번 먹어봤습니다\uD83D\uDE0B\uD83D\uDE0B \u200B 비움 클렌즈주스 <b>ABC</b>주스 250mlX10팩 : 대성에프앤지 [대성에프앤지...\",\"datetime\":\"2023-03-01T16:40:00.000+09:00\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/2QIL2RmW8Wp\",\"title\":\"<b>ABC</b>쥬스 효능 후기 부작용 정리해봤습니다\",\"url\":\"https://blog.naver.com/bsyun91/223029510572\"},{\"blogname\":\"anything\",\"contents\":\"안녕하세요 밍꼬입니다~! 오늘은 <b>ABC</b>주스 효능 및 부작용에 대해 알아보는 시간을 가지도록 하겠습니다. <b>abc</b>주스는 언제부터인가 갑자기 뜨기 시작하여 지금은 국민주스가 될 정도로 유명한데요! <b>ABC</b>주스란 Apple, Beat, Carrot에서 앞글자만 따온 3가지 주스로 사과, 비트, 당근을 갈아서 만든 음료예요 이 주스는 해독...\",\"datetime\":\"2023-02-22T22:13:13.000+09:00\",\"thumbnail\":\"https://search1.kakaocdn.net/argon/130x130_85_c/GRG8nKzanY5\",\"title\":\"<b>abc</b>주스 효능 및 부작용에 대해 알아보자!\",\"url\":\"http://dkssudl1.tistory.com/173\"},{\"blogname\":\"remembering\",\"contents\":\"많이 하시더라고요?! 저도 이것저것 사오고 싶었지만 막상 가니 뭘 사야할지 모르겠던... \u200B 그래서 괌의 편의점이라고 할 수 있을 만큼 많은 매장이 있는 <b>Abc</b>스토어에서 파는 물건들과 추천템을 글로 작성해보려고 합니다. \u200B 추천할만한 물건들은 포스팅 마지막에 작성해둘테니 참고해주세요 ㅎㅎ \u200B 그럼 포스팅 시작할게요...\",\"datetime\":\"2023-02-09T00:42:00.000+09:00\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/62M15IQqFJl\",\"title\":\"괌 <b>abc</b>스토어 쇼핑리스트 및 추천템\",\"url\":\"https://blog.naver.com/1011dh/223009745462\"},{\"blogname\":\"몰리고마켓\",\"contents\":\"엄마의 마음으로 가득 담은 건강한 <b>ABC</b>주스!! \u200B \u200B 결제창은 아래링크에서 \u200B \u200B <b>ABC</b>주스 30개입 (한달분) : 몰리고마켓 [몰리고마켓] 몰리언니가 필요해서 사다보니 좋아서 파는 것들 smartstore.naver.com \u200B 공구기간 \u200B 3월 6일 ~ 3월 8일 \u200B (3일간) 배송일정 익일배송 \u200B \u200B \u200B 배송비 / 증정품 \uD83D\uDCCD1박스30개입 무료배송 price \u200B 상품명...\",\"datetime\":\"2023-03-06T10:10:00.000+09:00\",\"thumbnail\":\"https://search3.kakaocdn.net/argon/130x130_85_c/DyGBntAUP86\",\"title\":\"힐링 <b>ABC</b>주스 공동구매오픈 (무료배송!)\",\"url\":\"https://blog.naver.com/gomollygo/223035933841\"}],\"meta\":{\"is_end\":false,\"pageable_count\":790,\"total_count\":780424}}");
        Mockito.when(openApiCaller.get(headers, "GET", apiUrl + "?size=10&query=abc&sort=accuracy&page=3"))
                .thenReturn("{\"documents\":[{\"blogname\":\"보라공쥬의 여행더하기! Travelrising\",\"contents\":\"괌쇼핑 <b>ABC</b>스토어 구경하기~ 쇼핑리스트 추천! 괌은 아이들은 바다에서 놀기 좋고 엄마들은 쇼핑하기 참 좋았던 곳인데 사실 지금은 환율도 그렇고 예전만큼의 메리트가 떨어진 건 사실이에요. 그래도 또 궁금은 해서 괌쇼핑 하기 좋은 <b>ABC</b> 스토어, 괌프리미엄아울렛, 괌DFS T갤러리아까지 ㅋㅋㅋ 가보긴 다 가봤어요...\",\"datetime\":\"2023-03-04T08:00:00.000+09:00\",\"thumbnail\":\"https://search3.kakaocdn.net/argon/130x130_85_c/alINwDQFAc\",\"title\":\"괌쇼핑 <b>ABC</b>스토어 구경하기~ 쇼핑리스트 추천!\",\"url\":\"https://blog.naver.com/ycdy80/223026352993\"},{\"blogname\":\"⁺⊹˚.⋆ may-log ⋆.˚⊹⁺\",\"contents\":\"ㅁ 괌에 가면 들를 수밖에 없는 <b>ABC</b> 스토어와 한국인 사장님이 운영하시는 기프트샵 GIFT GUAM 후기 \u200B \u200B \u200B 먼저 GIFT GUAM 위에서 말한 것처럼 한인 사장님이 운영하시는 기프트샵 ! GIFT GUAM +1 671-646-4438 https://maps.app.goo.gl/Dt6PHxSY9q6SVi2U6?g_st=ic \u200B \u200B \u200B 두짓타니에서 갤러리아 쪽으로 건너 길을 따라 쭉...\",\"datetime\":\"2023-03-07T22:52:00.000+09:00\",\"thumbnail\":\"https://search3.kakaocdn.net/argon/130x130_85_c/7kLtVUi4R97\",\"title\":\"괌 GIFT GUAM &amp; <b>ABC</b> STORE 리뷰, 추천템\",\"url\":\"https://blog.naver.com/may-log/223037950609\"},{\"blogname\":\"JnJ의 공간\",\"contents\":\"숟가락젓가락들고대전 <b>ABC</b>주스 \u200B \u200B \u200B 안녕하세요 J_ae입니다! \u200B \u200B \u200B 옷이 얇아지기 시작하면서 다시 으쌰으쌰 식단을 시작하시는 분들도 많으실테고 새해 새로운 마음으로 건강관리를 시작하신 분들도 많으실 것 같아요! 저도 고런 마음이고~ \u200B 그래서 오늘은 제가 1병씩 꼭 챙겨먹고 있는 직접 갈아 만든 <b>ABC</b>주스를 리뷰해...\",\"datetime\":\"2023-03-17T17:34:00.000+09:00\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/BM1MNBu5kOY\",\"title\":\"아침 과채주스 <b>ABC</b>주스 숟가락젓가락들고대전 녹즙배달\",\"url\":\"https://blog.naver.com/jnj_301/223047589084\"},{\"blogname\":\"호담의 맥시멀라이프\",\"contents\":\"\u200B \u200B 개운하고 상쾌한 아침 \u200B 하루의 시작이 좀 더 산뜻할 수 있도록 짜본 일주일 다이어트 식단 \u200B <b>ABC</b> 주스도 함께 챙겨봤어요. 찌고 갈고 번거로움 없이 맛있게 마실 수 있는 <b>ABC</b> 주스 \u200B 굶지 말고 골고루 마시며 함께 해봐요. \u200B 건강한 삶을 위한 준비 클렌즈주스 <b>ABC</b> 주스 후기 건강식을 챙겨 먹는다 말하곤 했지만 피곤...\",\"datetime\":\"2023-02-15T17:13:00.000+09:00\",\"thumbnail\":\"https://search4.kakaocdn.net/argon/130x130_85_c/6DkOdkrcsYd\",\"title\":\"<b>ABC</b> 주스로 짜본 일주일 다이어트 식단\",\"url\":\"https://blog.naver.com/cheawhi/223016693225\"},{\"blogname\":\"미술, 건축, 세상사는 이야기\",\"contents\":\"사례 #프랑스 #cabin #lodge ©2023.(google.image) Cabane <b>ABC</b> Cabane <b>ABC</b> Use: CABINS &amp; LODGES Location: CERNUSSON, FRANCE Area: 20 m² Architect: Atelier Toboggan Year: 2022 ©2023.(google.image) Cabane <b>ABC</b> L&#39;atelier Toboggan은 낭트에서 건축 학교를 졸업한 세 명의 젊은 공예가로 구성된 회사다. 현재...\",\"datetime\":\"2023-01-26T17:12:00.000+09:00\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/BxXm5w8jlkV\",\"title\":\"Cabane <b>ABC</b>\",\"url\":\"https://blog.naver.com/kei98401/222995260533\"},{\"blogname\":\"니키월드 ♡\",\"contents\":\"\u200B 하와이 편의점이지만 괌과 사이판에도 있는 <b>ABC</b>스토어! 괌 쇼핑리스트 필수 코스로도 불리는 곳입니다. 괌 여행 중 일정 문제로 백화점과 면세점, 아울렛 등 대형 쇼핑몰에 갈 시간이 없는 분들에게는 더욱이 강추하고 싶은 곳이에요. \u200B 제가 소개하는 괌 <b>ABC</b>스토어는 리프 호텔 근처에 있어요. 지금까지 가봤던 <b>ABC</b>...\",\"datetime\":\"2023-03-12T22:10:00.000+09:00\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/3iJkq7LUd9v\",\"title\":\"괌 쇼핑리스트 <b>ABC</b>스토어 바나나보트 선크림 맥주 무스비 여행 기념품\",\"url\":\"https://blog.naver.com/niky8888/223042411888\"},{\"blogname\":\"슈퍼100의 보통날 __\",\"contents\":\"\u200B 요즘 #달심오일만주스 로 공복 아침 맞이하고 있는데 5일먹고 10일 쉬어야하잖아요 쉬는 10일동안 먹을 #<b>ABC</b>주스 를 구매했습니다 \u200B 아침공복은 의지가 아니라 잠을 더 자려고 하는건데 어차피 할 거 뭐라도 먹어보고 다이어트에 도움이 되길 바라면서 구매했어요 \u200B 달심에서도 <b>ABC</b>주스팔던데 가격면에서 제 기준엔 탈락...\",\"datetime\":\"2023-03-07T12:51:00.000+09:00\",\"thumbnail\":\"https://search1.kakaocdn.net/argon/130x130_85_c/Hw0sGJXxy1m\",\"title\":\"클렌즈주스추천 내돈내산 프롬바이오 <b>ABC</b>주스로 다이어트시작 !\",\"url\":\"https://blog.naver.com/hissong__/223037281387\"},{\"blogname\":\"DEAR  LIFE\",\"contents\":\"<b>abc</b>주스 효능과 만드는 법에 대해 알아볼 텐데요. <b>abc</b>주스는 사과(Apple), 비트(Beat), 당근(Carrot)의 첫 글자를 따서 <b>ABC</b>주스라고 불립니다. <b>abc</b>주스는 혈압 강하와 체중 감량, 면역력 증진 등의 효과가 있어 건강 주스로 알려져 있습니다. <b>abc</b>주스 효능 <b>abc</b>주스는 사과, 비트, 당근 세 원료를 사용하여 만든 주스로...\",\"datetime\":\"2023-03-07T14:13:37.000+09:00\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/7xZcNJOqCLC\",\"title\":\"<b>abc</b>주스 효능 만드는 법 추천, 집에서 쉽게 만들었어요\",\"url\":\"http://shiny-dear-life.tistory.com/71\"},{\"blogname\":\"La Vie en Rose\",\"contents\":\"피부미용에 좋은 <b>ABC</b> 주스 만들기 <b>ABC</b> 주스가 유행한지도 \u200B 한창 지났는데요. 그때는 만들어 먹지 않았었는데 \u200B &#39;나 혼자 산다&#39;에 출연자가 \u200B <b>ABC</b> 주스를 만들어 먹는 게 매일 하는 루틴이라며 직접 만들어 먹는 것을 보고서 발동이 걸려서 만들다 보니 \u200B 매일 아침에 먹는 \u200B 내 루틴이 되었습니다. \u200B 이제는 안 먹으면 \u200B 내 몸...\",\"datetime\":\"2023-03-07T21:30:00.000+09:00\",\"thumbnail\":\"https://search3.kakaocdn.net/argon/130x130_85_c/KHZPy95ltpI\",\"title\":\"<b>ABC</b> 주스의 효능과 <b>ABC</b> 주스 만들기(피부에 좋은 음식)\",\"url\":\"https://blog.naver.com/712rosa/223037880038\"},{\"blogname\":\"하얀고래\",\"contents\":\"한국에서 중형마트나 대형 편의점 느낌의 <b>ABC</b> STORES 하와이 후기들을 검색하다 보면 흔히 보는 게 <b>ABC</b> 마트에서 장본 후기! 처음 들어간 마트에서 구경을 잔뜩 했어요 호텔에서 지내다가 마실을 나왔어요 하와이 자유여행 후기들을 보다 보면 많이들 가고 많은 물품을 사는 <b>ABC</b> STORES <b>ABC</b> 마트를 발견하고 기분 좋게...\",\"datetime\":\"2023-01-27T05:56:56.000+09:00\",\"thumbnail\":\"https://search4.kakaocdn.net/argon/130x130_85_c/LQQQ0tAQHld\",\"title\":\"하와이 자유여행 <b>ABC</b> 마트 없는것 빼곤 다 있는 STORES\",\"url\":\"http://jck313.tistory.com/356\"}],\"meta\":{\"is_end\":false,\"pageable_count\":790,\"total_count\":780423}}");
        Mockito.when(openApiCaller.get(headers, "GET", apiUrl + "?size=10&query=abc&sort=accuracy&page=4"))
                .thenReturn("{\"documents\":[{\"blogname\":\"따롱맘\",\"contents\":\"안나푸르나 <b>ABC</b>베이스캠프까지 등반을 드디어 실현했다 안나푸르나는 8000m급 봉우리1개, 7000m급 봉우리 13개, 6,000m급 봉우리16개로 이루어진 대산군이다 히말라야14좌 등반중 최고로 위험한 산이라고한다 우리나라도 지현옥대장 박영석대장 일행등이 여기서 사망했다고한다 \u200B 하지만 일반인 트래킹코스로는 여기가...\",\"datetime\":\"2023-03-12T13:48:00.000+09:00\",\"thumbnail\":\"https://search3.kakaocdn.net/argon/130x130_85_c/3dT9yMtCA2r\",\"title\":\"안나푸르나 <b>ABC</b>베이스캠프 등반\",\"url\":\"https://blog.naver.com/pmkmk1/223042084566\"},{\"blogname\":\"탱굴's 다이어리\",\"contents\":\"\u200B 나이가 들어갈수록 건강을 잘 챙겨야겠다는 생각이 드는 요즘입니다. 그중 한창 유명했던 <b>ABC</b> 주스, 저도 한번 마셔보기로 했어요. \u200B 검색하니 상품이 엄청 많더라고요. 그중에서도 저는 픽은 후기도 좋고 국내산 생착즙액 100%인 마이산 약초마을의 <b>ABC</b> 주스로 선택했어요.\u200B\u200B \u200B \u200B 택배 도착. 택배 박스 안에 또 상품 박스가...\",\"datetime\":\"2023-02-22T16:33:00.000+09:00\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/2U2U6lECexC\",\"title\":\"[마이산 약초마을] 생착즙액 100% <b>ABC</b> 주스!!\",\"url\":\"https://blog.naver.com/baaguu/223024235397\"},{\"blogname\":\"유지어터의 참 쉬운 요리\",\"contents\":\"- 라티브 맛있게 만든 <b>ABC</b> 주스 - \u200B \u200B 내 이모는 몸매 관리에 많은 관심을 가지고 있다. 약 10년 전부터 그녀는 나만 보면 귀가 따갑도록 <b>ABC</b> 주스 추천했다. \u200B 해독주스로 이만한 것이 없다면서 디톡스주스 한 잔으로 하루를 시작하라고 권했다. 아침에 한 잔 마시면 다이어트에도 좋고 기분도 좋아진다며 그녀의 <b>ABC</b> 주스...\",\"datetime\":\"2023-02-17T15:47:00.000+09:00\",\"thumbnail\":\"https://search4.kakaocdn.net/argon/130x130_85_c/5shoF6EoOH0\",\"title\":\"해독주스 라티브 <b>ABC</b> 주스 추천 유지어터의 다이어트 아이템\",\"url\":\"https://blog.naver.com/crispynote/223018969899\"},{\"blogname\":\"소요블리 Beauty tips\",\"contents\":\"내가 이제껏 나쁜 것들을 많이 먹었으니, 내 몸에게 주는 선물로다가 .. 초록창에서 열심히 검색해 보았어요. 내돈내산 후기 시작해요. ^_^ \u200B \u200B \u200B 본리브 <b>ABC</b>주스 픽!!! \u200B 티톡스를 도와주는 주스를 사려고 했는데요. 예전에 눈팅했던 것들이 안보이더라구요. \u200B <b>ABC</b>주스를 사려고 한건 아니였지만, 쾌변의 선물을 안겨준다는...\",\"datetime\":\"2023-03-08T22:21:00.000+09:00\",\"thumbnail\":\"https://search3.kakaocdn.net/argon/130x130_85_c/6TY60HvPuv7\",\"title\":\"프리바이오틱스가 들어간 본리브 <b>ABC</b>주스 내돈내산 후기\",\"url\":\"https://blog.naver.com/dlqlehfl3355/223038957932\"},{\"blogname\":\"만나이츠 공식 블로그\",\"contents\":\"안녕하십니까! 마케터 M입니다. \u200B 오늘도 어김없이 찾아온 식이야기 타임입니다! 준비한 식이야기는 <b>ABC</b>쥬스에 대한 이야기입니다. 바로 시작해 보겠습니다! 오늘 제가 준비한 이야기는 <b>ABC</b> 주스인데요! 다들 드셔보셨나요? 저는 따로 만들어 먹지는 않지만, 식당이나 카페에 갔을 때 있으면 한 번씩? 챙겨 먹습니다...\",\"datetime\":\"2023-02-09T11:25:00.000+09:00\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/EHI1UPREuBF\",\"title\":\"식이야기, <b>ABC</b> 제대로 알고 먹기!\",\"url\":\"https://blog.naver.com/manna-eats/223010103364\"},{\"blogname\":\"머니머니해도 머니우스\",\"contents\":\"겜인재원 다니는 아들. 아침식사 안 하는 스따일이지만 빈속으로 보내기가 안타까워 디톡스 효과와 사과. 비트. 당근의 효능까지 한봉에 해결할 수 있는 <b>ABC</b> 쥬스 주문 \u200B 환절기에 감기 조심하라고 배도라지즙까지 보내주는 울 언니 싸랑해 까치골농원 경상남도 사천시 축동면 탑리길 19 [까치골농원(사천) - 상품 (상단...\",\"datetime\":\"2023-03-17T19:48:00.000+09:00\",\"thumbnail\":\"https://search3.kakaocdn.net/argon/130x130_85_c/BOt6Xi9qzMb\",\"title\":\"<b>ABC</b>주스 추천과 간편 주문방법\",\"url\":\"https://blog.naver.com/moneyus/223047734288\"},{\"blogname\":\"나의 늙은 고양이 콩깍지\",\"contents\":\"오기전에 살을 빼야 옷을 입을텐데 말이죠. 우리 모두 오늘부터 다이어트 1일해요. \u200B 오늘 소개드릴 디톡스주스는 재료 그대로 갈아 만든 비움 클렌저 <b>ABC</b> 주스예요.\u200B\u200B \u200B <b>ABC</b> 주스는 급속냉각방식으로 냉동상태로 배송오기때문에 냉동실에 보관해두시고 마시기 전날 냉장실로 옮겨놓으면 알맞게 녹아서 마시기 편했어요...\",\"datetime\":\"2023-03-01T14:16:00.000+09:00\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/2TSDJMYQZ6s\",\"title\":\"비움 클렌즈 <b>ABC</b>주스로 디톡스다이어트해요\",\"url\":\"https://blog.naver.com/hw_0108/223031494979\"},{\"blogname\":\"뒹굴뒹굴\",\"contents\":\"발렌타인데이를 맞아 초콜릿 만들기를 해봤다! \u200B \uD83C\uDF6B \uD83C\uDF6B \uD83C\uDF6B #발렌타인데이 #초콜릿만들기 #<b>abc</b>초콜릿 #가나초콜릿 초콜릿 만들기 쫜!! \u200B \u200B 작년에도 했긴한데 아주 허접하게 했어가지고 좀 그랬는데 \u200B 이번에는 정성을 아주 듬뿍넣었더니 성공했다~ \u200B 땅땅이도 기대안했다고 했는데 서프라이즈로 받아서 기분좋아했음 ㅎㅎ 이런...\",\"datetime\":\"2023-02-14T18:04:00.000+09:00\",\"thumbnail\":\"https://search1.kakaocdn.net/argon/130x130_85_c/VQO2ES7LtE\",\"title\":\"<b>abc</b>초콜릿으로 초콜릿 만들기\uD83C\uDF69\uD83C\uDF6B\uD83D\uDC9D(23.2.12.)\",\"url\":\"https://blog.naver.com/limju28/223015535092\"},{\"blogname\":\"香\",\"contents\":\"챙겨주면서 몸속에 잔여 효소의 양을 늘려주고 소화력이 좋아지도록 하는거예요. 챙겨 먹으면서 관리할 수 있는 부분이니 다행이죠. 그러다 눈에 들어온 제품 <b>ABC</b> LAB, 이름부터 특이해서 눈길이 갔었는데 많은 효소들을 먹어왔지만 디톡스 <b>ABC</b>를 효소 먹으면서 할 수 있다는 건 큰 장점이라 생각했거든요. 디톡스 <b>ABC</b>란...\",\"datetime\":\"2023-03-09T17:00:00.000+09:00\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/A1Y9X21LoV2\",\"title\":\"디톡스효소 :: 셀핏 <b>ABC</b>효소 효능 후기\",\"url\":\"https://blog.naver.com/hyang_flower/223039634921\"},{\"blogname\":\"그냥 일상 기록\",\"contents\":\"지지난주 비맞고... 저번주 캠핑다녀와서 차가 엉망이더라고요??! \u200B 정관에 세차 잘 하는 곳이 있다고 해서 다녀왔어요! <b>ABC</b> 스팀세차장 정관점 <b>ABC</b>스팀세차장 정관점 부산광역시 기장군 정관읍 구연방곡로 48 \u200B 동양마트에서 쭉 올라오다보면 아이비 어린이집과 소두방공원 맞은편에 위치해있어요 또는 황소인력 맞은편...\",\"datetime\":\"2023-03-09T10:36:00.000+09:00\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/6EaQOfSfLT8\",\"title\":\"부산 세차 잘하는 곳 &#39;<b>ABC</b>스팀 세차장&#39; 정관점 후기\",\"url\":\"https://blog.naver.com/dnk216/223039294983\"}],\"meta\":{\"is_end\":false,\"pageable_count\":790,\"total_count\":780424}}");
        Mockito.when(openApiCaller.get(headers, "GET", apiUrl + "?size=10&query=abc&sort=accuracy&page=5"))
                .thenReturn("{\"documents\":[{\"blogname\":\"쎄씨주부의 소꿉놀이터\",\"contents\":\"15일 - 24일 사이 제품으로 출고해드립니다 단。。。아보카도 제품은 원물 특성으로 인 해 12~20일 입니다 옵션은 총 네가지인데요 \u200B 1번과 2번 옵션은 <b>ABC</b>주스입니다. Apple Beet Carrot \u200B 위 세가지 재료로 만든 <b>ABC</b> 쥬스의 디톡스 기능은 다들 아실거에요~ \u200B 12개와 18개 구성으로 준비했습니다. \u200B 각각의 구성에 랜덤 세...\",\"datetime\":\"2023-02-28T15:20:00.000+09:00\",\"thumbnail\":\"\",\"title\":\"[공구]올가니카 클렌즈 쥬스, <b>ABC</b> 쥬스로 디톡스해요\",\"url\":\"https://blog.naver.com/sessyjubu2/223030426390\"},{\"blogname\":\"LiFES TYLE\",\"contents\":\"라이프스타일 블로거 상익스입니다 리오프닝으로 다시 활발해진 명동에 슈즈 멀티숍들이 다시 격돌 중이라는 소식을 듣고 업무차 방문해 보았어요 먼저 <b>ABC</b>마트 명동 길 점 기존 ST(1.0) 매장의 상위 버전 ST(2.0)을 표방한다고 합니다 건물 통째로 입점한 <b>ABC</b>마트 명동 길 점 기존에 ST, MS, GS 3개의 매장을 명동에...\",\"datetime\":\"2023-02-25T19:48:00.000+09:00\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/7I2ibXioDol\",\"title\":\"명동 <b>ABC</b>마트 명동길점 vs슈마커 플러스 전격 비교\",\"url\":\"https://blog.naver.com/lifes_tyle/223027520172\"},{\"blogname\":\"RECORD\",\"contents\":\"\u200B \u200B 사이판에서 쇼핑하실 분은 티갤러리아 / <b>ABC</b>스토어 / 아이러브사이판 이 세군데만 방문하시면 됩니다 :)\u200B \u200B 티갤러리아는 구찌 프라다 등 명품 쇼핑 + 고디바를 저렴하게 구매할 수 있구요 <b>ABC</b>스토어는 여행 중 필요한 용품이나 마실거, 먹을거 사기 좋구 아기자기한 기념품 쇼핑에는 아이러브사이판만\u200B 들러도 좋을 듯...\",\"datetime\":\"2023-03-08T14:10:00.000+09:00\",\"thumbnail\":\"https://search1.kakaocdn.net/argon/130x130_85_c/BTU1B5AFeuo\",\"title\":\"사이판 기념품 쇼핑 : <b>ABC</b>스토어/아이러브사이판\",\"url\":\"https://blog.naver.com/mylemon12/223038432963\"},{\"blogname\":\"맛있게 놀꺼지롱\",\"contents\":\"서방과 다녀온 하와이 신혼여행 ~ ㅋ 열심히 쓴다고 썼는데 아직도 작성할게 산더미네..^^ \u200B 암튼 오늘 작성해볼것은 하와이 하면 딱 떠오르는 <b>ABC</b> STORE 하와이 편의점 하와이 <b>ABC</b> STORE 에이비씨 스토어 에이비씨 스토어는 하와이의 편의점이라 생각하면 됨! 하와이 곳곳에 자리잡고 있고 과장 조금 보태서 한건물에...\",\"datetime\":\"2023-02-22T16:30:00.000+09:00\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/A5tHDjZSK1w\",\"title\":\"하와이 : 에이비씨스토어 <b>ABC</b> Store / 물, 기념품, 옷, 사은품\",\"url\":\"https://blog.naver.com/mrkimiscrazy/223024224721\"},{\"blogname\":\"변덕쟁이 일상기록\",\"contents\":\"[건강주스, 디톡스, 아침대용 추천, 비움<b>abc</b>주스\uD83C\uDF77] 디톡스를 위한 비움 <b>abc</b> 주스 후기 \u200B 입으로 다이어트를 외치고 있지만 막상 아침 공복에 나약해져서는 탕비실을 탐내는 나 자신.. \u200B 공복으로 아침에 뛰거나 무리하면 어지럽다 어지러워ㅠ \uD83D\uDC8A 가볍게 뭐라도 먹거나 마시는 것이 중요하다 (공복에 무리하면 어지러우신 분...\",\"datetime\":\"2023-02-22T14:39:00.000+09:00\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/1ykGsnQyFVt\",\"title\":\"[디톡스 건강주스] 직접 갈아 만드는 비움 클렌즈 <b>abc</b> 주스 후기\",\"url\":\"https://blog.naver.com/clear_f/223024112616\"},{\"blogname\":\"소녀의 순수함과 요부의 섹시함\",\"contents\":\"\u200B #하와이 #와이키키 #<b>abc</b>마트 정말 자주 갔어요 \u200B 하와이여행 가서 편의점 정말 자주 갔었죠 물 사러 가고 선물 사러도 가고 아이 모자도 사고 과자도 사고 수시로 방문했던곳 \u200B 하와이 <b>abc</b> 스토어 \u200B 우리가 묵었던 호텔 1층에 위치하고 있어서 정말 수시로 방문 했었는데요 \u200B 임페리얼 하와이 리조트 앳 와이키키 1...\",\"datetime\":\"2023-02-02T15:20:00.000+09:00\",\"thumbnail\":\"https://search3.kakaocdn.net/argon/130x130_85_c/8EqGVChbsIC\",\"title\":\"하와이 와이키키 <b>abc</b>마트 정말 자주 갔어요\",\"url\":\"https://blog.naver.com/nuseehjh/222997348797\"},{\"blogname\":\"[Over The TOP - 경험이 곧 지식이다]\",\"contents\":\"\u200B \u200B \u200B \u200B 괌 <b>ABC</b> STORES에서 200달러 이상 깨알 쇼핑하고 알게 된 점들 사실 그대로 공유해 보겠으니 괌 여행하실 때 도움이 되면 좋겠네요 \u200B 괌 96913 Tamuning, Tamuning 1275 Pale San Vitores Road 155 괌 96913 Tamuning, Tamuning 1275 Pale San Vitores Road 155 \u200B \u200B \u200B 1. 첫째 날 여행 필수품 및 관광 기본 상품 구입...\",\"datetime\":\"2023-02-27T03:14:00.000+09:00\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/HlGhCrnNJnH\",\"title\":\"괌 <b>ABC</b> STORES에서 200달러 이상 구매하고 알게 된 것들\",\"url\":\"https://blog.naver.com/jnlss/223028678969\"},{\"blogname\":\"JUNG군의 엘리어트 파동이론 비트코인 매매\",\"contents\":\"위 그림은 하나의 <b>ABC</b>파동이다 어떤 식으로 카운팅을 해야 하나? 가운데 형식의 <b>ABC</b>? 아니면 가장 오른쪽의 <b>ABC</b>? 가장 오른쪽의 파동은 틀린 카운팅이라고 할 수 있다. 가장 큰 조정파동이 아니기 때문이다. 가장큰 조정 파동을 기준으로 삼는다면 가운데 카운팅이 차라리 올바른 카운팅이라고 할 수 있다. 왼쪽부터...\",\"datetime\":\"2023-02-16T09:00:39.000+09:00\",\"thumbnail\":\"https://search3.kakaocdn.net/argon/130x130_85_c/B2NQdgN7oek\",\"title\":\"<b>ABC</b>파동 카운팅 방법\",\"url\":\"http://junggunbabo.tistory.com/60\"},{\"blogname\":\"아토피집밥, 휴식여행일기 by 사랑해김수미\",\"contents\":\"#<b>ABC</b>주스 만들기 #<b>ABC</b>쥬스 먹는법 #<b>ABC</b>쥬스비율 재료 최적의 맛~ 가벼운 #아침주스 \u200B \u200B 지난달부터 다시~ 아침주스 만들어 마시기 시작한 울집 배주스, 토마토주스, 사과케일주스 등 과일과 야채를 활용해서 다양하게 마시고 있는 중인데요~ 얼마전부터 신랑의 최애 아침주스가 되버린 <b>ABC</b>주스는 퇴근길에 비트를 직접 사...\",\"datetime\":\"2023-03-03T01:20:00.000+09:00\",\"thumbnail\":\"https://search3.kakaocdn.net/argon/130x130_85_c/2KWPMdp0l7k\",\"title\":\"<b>ABC</b>주스 만들기 재료 비율 먹는법 아침주스 <b>ABC</b>쥬스 만들기\",\"url\":\"https://blog.naver.com/lovekimsumi/223033120516\"},{\"blogname\":\"열린 결말\",\"contents\":\"\u200B 안녕하세요 :) 이번에는 요즘 많은 분들의 아침식사 대용으로 유명한 <b>abc</b> 주스 추천 및 후기를 들고 왔습니다! A(Apple) + B(Beet) + C(Carrot)로 사과, 비트, 당근 즙이 함유되어 있는 주스이고 일명 해독주스라고도 많이들 부르시더라구요! \u200B 아침에 사과는 금이라고 하죠?? 거기에다가 세포손상 억제, 토마토의 8...\",\"datetime\":\"2023-02-25T10:00:00.000+09:00\",\"thumbnail\":\"https://search4.kakaocdn.net/argon/130x130_85_c/KwY3mETpJW6\",\"title\":\"여에스더 <b>abc</b>주스 추천 내돈내산 후기\",\"url\":\"https://blog.naver.com/kang981023/223026616506\"}],\"meta\":{\"is_end\":true,\"pageable_count\":790,\"total_count\":780429}}");
    }

    @Test
    @DisplayName("POST /search Redirect 테스트")
    public void controller_PostRedirectTest() throws Exception {
        // when
        mockMvc.perform(post("/search")
                .param("query", "abc")
                .param("sorting", "accuracy")
                .param("page", "1")
                .param("size", "10")
                // then
        ).andExpect(redirectedUrl("/search?query=abc&sorting=accuracy&page=1&size=10"));
    }

    @Test
    @DisplayName("GET /search 첫 페이지 조회 테스트")
    public void controllerTest_getFirstPage() throws Exception {
        // when
        byte[] responseBytes = mockMvc.perform(get("/search")
                        .param("query", "abc")
                        .param("sorting", "accuracy")
                        .param("page", "1")
                        .param("size", "10")
        ).andReturn().getResponse().getContentAsByteArray();

        ObjectMapper objectMapper = new ObjectMapper();
        EntityModel response = objectMapper.readValue(responseBytes, EntityModel.class);

        // then
        Assertions.assertNotNull(response);
        HashMap<String, Object> contents = (HashMap<String, Object>)response.getContent();
        HashMap<String, Object> links = (HashMap<String, Object>)contents.get("_links");

        HashMap<String, String> selfLink = (HashMap<String, String>)links.get("self");
        Assertions.assertNotNull(selfLink.get("href"));
        Assertions.assertEquals("http://localhost/search?query=abc&sorting=accuracy&page=1&size=10",
                selfLink.get("href"));

        Assertions.assertNull(links.get("prev"));

        HashMap<String, String> nextLink = (HashMap<String, String>)links.get("next");
        Assertions.assertNotNull(nextLink.get("href"));
        Assertions.assertEquals("http://localhost/search?query=abc&sorting=accuracy&page=2&size=10",
                nextLink.get("href"));

        HashMap<String, String> accuracyLink = (HashMap<String, String>)links.get("accuracy");
        Assertions.assertNotNull(accuracyLink.get("href"));
        Assertions.assertEquals("http://localhost/search?query=abc&sorting=accuracy&page=1&size=10",
                accuracyLink.get("href"));

        HashMap<String, String> recencyLink = (HashMap<String, String>)links.get("recency");
        Assertions.assertNotNull(recencyLink.get("href"));
        Assertions.assertEquals("http://localhost/search?query=abc&sorting=recency&page=1&size=10",
                recencyLink.get("href"));
    }

    @Test
    @DisplayName("GET /search 중간 페이지 조회 테스트")
    public void controllerTest_getMiddlePage() throws Exception {
        // when
        byte[] responseBytes = mockMvc.perform(get("/search")
                .param("query", "abc")
                .param("sorting", "accuracy")
                .param("page", "3")
                .param("size", "10")
        ).andReturn().getResponse().getContentAsByteArray();

        ObjectMapper objectMapper = new ObjectMapper();
        EntityModel response = objectMapper.readValue(responseBytes, EntityModel.class);

        // then
        Assertions.assertNotNull(response);
        HashMap<String, Object> contents = (HashMap<String, Object>)response.getContent();
        HashMap<String, Object> links = (HashMap<String, Object>)contents.get("_links");

        HashMap<String, String> selfLink = (HashMap<String, String>)links.get("self");
        Assertions.assertNotNull(selfLink.get("href"));
        Assertions.assertEquals("http://localhost/search?query=abc&sorting=accuracy&page=3&size=10",
                selfLink.get("href"));

        HashMap<String, String> prevLink = (HashMap<String, String>)links.get("prev");
        Assertions.assertNotNull(prevLink.get("href"));
        Assertions.assertEquals("http://localhost/search?query=abc&sorting=accuracy&page=2&size=10",
                prevLink.get("href"));

        HashMap<String, String> nextLink = (HashMap<String, String>)links.get("next");
        Assertions.assertNotNull(nextLink.get("href"));
        Assertions.assertEquals("http://localhost/search?query=abc&sorting=accuracy&page=4&size=10",
                nextLink.get("href"));

        HashMap<String, String> accuracyLink = (HashMap<String, String>)links.get("accuracy");
        Assertions.assertNotNull(accuracyLink.get("href"));
        Assertions.assertEquals("http://localhost/search?query=abc&sorting=accuracy&page=3&size=10",
                accuracyLink.get("href"));

        HashMap<String, String> recencyLink = (HashMap<String, String>)links.get("recency");
        Assertions.assertNotNull(recencyLink.get("href"));
        Assertions.assertEquals("http://localhost/search?query=abc&sorting=recency&page=3&size=10",
                recencyLink.get("href"));
    }

    @Test
    @DisplayName("GET /search 마지막 페이지 조회 테스트")
    public void controllerTest_getLastPage() throws Exception {
        // when
        byte[] responseBytes = mockMvc.perform(get("/search")
                .param("query", "abc")
                .param("sorting", "accuracy")
                .param("page", "5")
                .param("size", "10")
        ).andReturn().getResponse().getContentAsByteArray();

        ObjectMapper objectMapper = new ObjectMapper();
        EntityModel response = objectMapper.readValue(responseBytes, EntityModel.class);

        // then
        Assertions.assertNotNull(response);
        HashMap<String, Object> contents = (HashMap<String, Object>)response.getContent();
        HashMap<String, Object> links = (HashMap<String, Object>)contents.get("_links");

        HashMap<String, String> selfLink = (HashMap<String, String>)links.get("self");
        Assertions.assertNotNull(selfLink.get("href"));
        Assertions.assertEquals("http://localhost/search?query=abc&sorting=accuracy&page=5&size=10",
                selfLink.get("href"));

        HashMap<String, String> prevLink = (HashMap<String, String>)links.get("prev");
        Assertions.assertNotNull(prevLink.get("href"));
        Assertions.assertEquals("http://localhost/search?query=abc&sorting=accuracy&page=4&size=10",
                prevLink.get("href"));

        Assertions.assertNull(links.get("next"));

        HashMap<String, String> accuracyLink = (HashMap<String, String>)links.get("accuracy");
        Assertions.assertNotNull(accuracyLink.get("href"));
        Assertions.assertEquals("http://localhost/search?query=abc&sorting=accuracy&page=5&size=10",
                accuracyLink.get("href"));

        HashMap<String, String> recencyLink = (HashMap<String, String>)links.get("recency");
        Assertions.assertNotNull(recencyLink.get("href"));
        Assertions.assertEquals("http://localhost/search?query=abc&sorting=recency&page=5&size=10",
                recencyLink.get("href"));
    }
}