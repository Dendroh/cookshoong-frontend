package store.cookshoong.www.cookshoongfrontend.account.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import store.cookshoong.www.cookshoongfrontend.account.model.request.UpdateAccountInfoRequestDto;
import store.cookshoong.www.cookshoongfrontend.account.service.AccountIdAware;
import store.cookshoong.www.cookshoongfrontend.account.service.AccountService;
import store.cookshoong.www.cookshoongfrontend.address.model.request.CreateAccountAddressRequestDto;
import store.cookshoong.www.cookshoongfrontend.address.model.response.AccountAddressResponseDto;
import store.cookshoong.www.cookshoongfrontend.address.model.response.AddressResponseDto;
import store.cookshoong.www.cookshoongfrontend.address.service.AccountAddressService;

/**
 * 마이페이지 컨트롤러.
 *
 * @author papel (윤동현), koesnam (추만석)
 * @since 2023.08.06
 *
 */
@Controller
@RequiredArgsConstructor
public class MyPageController {
    private final AccountService accountService;
    private final AccountAddressService accountAddressService;
    private final AccountIdAware accountIdAware;

    /**
     * 회원정보 페이지 진입.
     *
     * @param updateAccountInfoRequestDto the update account info request dto
     * @param model                       the model
     * @return the my-page
     */
    @GetMapping("/my-page")
    public String getMyPage(UpdateAccountInfoRequestDto updateAccountInfoRequestDto, Model model) {
        model.addAttribute("updateAccountInfoRequestDto", updateAccountInfoRequestDto);
        model.addAttribute("accountInfo", accountService.selectAccountMypageInfo(accountIdAware.getAccountId()));
        return "account/my-page";
    }

    /**
     * 회원 정보 수정 요청.
     *
     * @param updateAccountInfoRequestDto the update account info request dto
     * @param bindingResult               the binding result
     * @param model                       the model
     * @return the string
     */
    @PostMapping("/my-page")
    public String postMyPage(@Valid UpdateAccountInfoRequestDto updateAccountInfoRequestDto, BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(updateAccountInfoRequestDto);
            return "/my-page";
        }
        accountService.updateAccountMyPageInfo(accountIdAware.getAccountId(), updateAccountInfoRequestDto);
        return "redirect:/my-page";
    }

    /**
     * 회원이 주소를 등록하는 페이지와 가지고 있는 모든 주소를 보여주는 메서드.
     *
     * @param createAccountAddressRequestDto    회원이 주소를 등록하는 Dto
     * @param model                             HTML 로 보낼 데이터
     * @return                                  회원이 주소 등록과 모든 주소를 보여주는 페이지로 반환
     */
    @GetMapping("/my-address")
    public String getMyAddress(
        @ModelAttribute("createAccountAddressRequestDto") CreateAccountAddressRequestDto createAccountAddressRequestDto,
        Model model) {

        AddressResponseDto address =
            accountAddressService.selectAccountAddressRenewalAt(accountIdAware.getAccountId());

        List<AccountAddressResponseDto> accountAddresses =
            accountAddressService.selectAccountAddressAll(accountIdAware.getAccountId());

        model.addAttribute("latitude", address.getLatitude());
        model.addAttribute("longitude", address.getLongitude());
        model.addAttribute("accountAddresses", accountAddresses);

        return "account/my-address";
    }

    /**
     * 회원이 주소를 등록하는 메서드.
     *
     * @param createAccountAddressRequestDto    회원이 주소를 등록하는 Dto
     * @param bindingResult                     입력에 대한 검증 결과
     * @param model                             HTML 로 보낼 데이터
     * @return                                  현제 주소 등록 페이지로 반환
     */
    @PostMapping("/my-address")
    public String postCreateAccountAddress(
        @ModelAttribute("createAccountAddressRequestDto") CreateAccountAddressRequestDto createAccountAddressRequestDto,
        BindingResult bindingResult, Model model) {

        accountAddressService.createAccountAddress(accountIdAware.getAccountId(), createAccountAddressRequestDto);

        return "redirect:/my-address";
    }

    /**
     * 회원이 선택한 주소에 대해 갱신 날짜를 업데이트.
     *
     * @param addressId                 주소 아이디
     * @return                          현재 주소 등록 페이지로 반환
     */
    @PatchMapping("my-address/{addressId}")
    public String patchSelectAccountAddress(@PathVariable Long addressId) {

        accountAddressService.updateSelectAccountAddressRenewalAt(accountIdAware.getAccountId(), addressId);

        return "redirect:/my-address";
    }

    /**
     * 회원이 등록한 주소를 삭제하는 메서드.
     *
     * @param addressId        주소 아이디
     * @return          회원이 주소 등록과 모든 주소를 보여주는 페이지로 반환
     */
    @DeleteMapping("/my-address/{addressId}")
    public String deleteDeleteAccountAddress(@PathVariable Long addressId) {

        accountAddressService.removeAccountAddress(accountIdAware.getAccountId(), addressId);

        return "redirect:/my-address";
    }
}