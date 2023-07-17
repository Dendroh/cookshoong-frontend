package store.cookshoong.www.cookshoongfrontend.store.adapter;

import static org.springframework.http.HttpMethod.POST;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import store.cookshoong.www.cookshoongfrontend.common.config.ApiProperties;
import store.cookshoong.www.cookshoongfrontend.store.exception.CreateBusinessHourFailureException;
import store.cookshoong.www.cookshoongfrontend.store.exception.CreateHolidayFailureException;
import store.cookshoong.www.cookshoongfrontend.store.exception.CreateMenuFailureException;
import store.cookshoong.www.cookshoongfrontend.store.model.request.CreateBusinessHourRequestDto;
import store.cookshoong.www.cookshoongfrontend.store.model.request.CreateHolidayRequestDto;
import store.cookshoong.www.cookshoongfrontend.store.model.CreateMenuGroupRequestDto;
import store.cookshoong.www.cookshoongfrontend.store.model.request.CreateMenuRequestDto;

/**
 * 매장 영업시간, 휴업일의 Adapter
 *
 * @author papel
 * @since 2023.07.14
 */
@Component
@RequiredArgsConstructor
public class StoreTimeAdapter {
    private final RestTemplate restTemplate;
    private final ApiProperties apiProperties;

    /**
     * 영업시간을 등록하는 메서드.
     *
     * @param storeId                                   매장아이디
     * @param createBusinessHourRequestDto              사업자가 영업시간을 등록하는 Dto
     */
    public void executeCreateBusinessHour(Long storeId, CreateBusinessHourRequestDto createBusinessHourRequestDto) {

        HttpEntity<CreateBusinessHourRequestDto> httpEntity =
            new HttpEntity<>(createBusinessHourRequestDto);

        ResponseEntity<Void> response =
            restTemplate.exchange(
                apiProperties.getGatewayUrl() +
                    "/api/stores/" +
                    storeId +
                    "/businesshour",
                POST,
                httpEntity,
                new ParameterizedTypeReference<>() {
                });

        if(!response.getStatusCode().is2xxSuccessful()) {
            throw new CreateBusinessHourFailureException(response.getStatusCode());
        }
    }

    /**
     * 휴업일를 등록하는 메서드.
     *
     * @param storeId                              매장아이디
     * @param createHolidayRequestDto              사업자가 휴업일을 등록하는 Dto
     */
    public void executeCreatHoliday(Long storeId, CreateHolidayRequestDto createHolidayRequestDto) {

        HttpEntity<CreateHolidayRequestDto> httpEntity =
            new HttpEntity<>(createHolidayRequestDto);

        ResponseEntity<Void> response =
            restTemplate.exchange(
                apiProperties.getGatewayUrl() +
                    "/api/stores/" +
                    storeId +
                    "/holiday",
                POST,
                httpEntity,
                new ParameterizedTypeReference<>() {
                });

        if(!response.getStatusCode().is2xxSuccessful()) {
            throw new CreateHolidayFailureException(response.getStatusCode());
        }
    }
}