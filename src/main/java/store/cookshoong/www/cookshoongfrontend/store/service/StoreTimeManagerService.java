package store.cookshoong.www.cookshoongfrontend.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import store.cookshoong.www.cookshoongfrontend.common.config.ApiProperties;

/**
 * 매장의 영업시간 관리를 위한 서비스.
 *
 * @author papel
 * @since 2023.07.09
 */
@Service
@RequiredArgsConstructor
public class StoreTimeManagerService {
    private final RestTemplate restTemplate;

    private final ApiProperties apiProperties;
}
