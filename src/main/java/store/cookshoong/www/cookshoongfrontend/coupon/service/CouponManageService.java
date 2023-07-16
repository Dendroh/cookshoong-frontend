package store.cookshoong.www.cookshoongfrontend.coupon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import store.cookshoong.www.cookshoongfrontend.coupon.adapter.CouponManageAdapter;
import store.cookshoong.www.cookshoongfrontend.coupon.model.response.SelectPolicyResponseDto;

/**
 * 쿠폰 관리 서비스.
 *
 * @author eora21(김주호)
 * @since 2023.07.15
 */

@Service
@RequiredArgsConstructor
public class CouponManageService {
    private final CouponManageAdapter couponManageAdapter;

    public Page<SelectPolicyResponseDto> selectStorePolicy(Pageable pageable) {
        // TODO: 매장 id 받아올 수 있을 때 UsageAll 아닌 Store 연결하기
        return couponManageAdapter.fetchUsageAllCouponPolicy(pageable);
    }
}
