package store.cookshoong.www.cookshoongfrontend.payment.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import store.cookshoong.www.cookshoongfrontend.payment.model.request.CreateTypeRequestDto;
import store.cookshoong.www.cookshoongfrontend.payment.model.request.ModifyTypeRequestDto;
import store.cookshoong.www.cookshoongfrontend.payment.model.response.TypeResponseDto;
import store.cookshoong.www.cookshoongfrontend.payment.service.ChargeTypeService;

/**
 * 결제 타입에 대한 Controller.
 *
 * @author jeongjewan
 * @since 2023.07.08
 */
@Slf4j
@Controller
@RequestMapping("/payments/charges/charge-type")
@RequiredArgsConstructor
public class ChargeTypeController {

    private final ChargeTypeService chargeTypeService;

    @GetMapping("/index")
    public String chargeIndex() {
        return "payment/store-charge-manager";
    }


    /**
     * 결제 타입 등록 화면 메서드.
     *
     * @param createTypeRequestDto  결제 타입에 생성되는 데이터
     * @param model                 HTML 로 보낼 데이터
     * @return                      등록 페이지로 반환
     */
    @GetMapping("/create")
    public String getCreateChargeType(@ModelAttribute("type") CreateTypeRequestDto createTypeRequestDto,
                                       Model model) {

        model.addAttribute("url", "create");
        return "payment/charge-type-form";
    }

    /**
     * 결테 타입 생성 메서드.
     *
     * @param createTypeRequestDto  결제 타입에 생성되는 데이터
     * @param bindingResult         입력에 대한 검증 결과
     * @param model                 HTML 로 보낼 데이터
     * @return                      Home 페이지로 반환
     */
    @PostMapping("/create")
    public String postDoCreateChargeType(@ModelAttribute("type") @Valid CreateTypeRequestDto createTypeRequestDto,
                                       BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("url", "create");
            return "payment/charge-type-form";
        }

        chargeTypeService.createChargeType(createTypeRequestDto);

        return "redirect:/payments/charges/charge-type";
    }

    /**
     * 해당 결제 타입을 조회하는 메서드.
     *
     * @param id        결제 타입 아이디
     * @param model     HTML 로 보낼 데이터
     * @return          해당 결제 타입을 보여주는 페이지로 반환
     */
    @GetMapping("/{id}")
    public String getFindChargeType(@PathVariable Long id, Model model) {

        TypeResponseDto charge = chargeTypeService.selectChargeType(id);

        log.info("chargeType ID NAME:<><>><>: {}", charge);

        model.addAttribute("chargeType", chargeTypeService.selectChargeType(id));

        return "payment/charge-type";
    }

    /**
     * 모든 결제 타입을 조회하는 메서드.
     *
     * @param model     HTML 로 보낼 데이터
     * @return          모든 결제 타입을 보여주는 페이지로 반환
     */
    @GetMapping
    public String getFindChargeType(Model model) {

        List<TypeResponseDto> chargeTypes = chargeTypeService.selectChargeTypeAll();

        model.addAttribute("chargeTypes", chargeTypes);

        return "payment/charge-type-list";
    }

    /**
     * 해당 결제 타입 수정 화면 메서드.
     *
     * @param id        결제 타입 아이디
     * @param model     HTML 로 보낼 데이터
     * @return          해당 결제 타입 수정하는 페이지도 반환
     */
    @GetMapping("/{id}/modify")
    public String getModifyChargeType(@PathVariable Long id,
                                      Model model) {

        model.addAttribute("chargeType", chargeTypeService.selectChargeType(id));
        model.addAttribute("type", chargeTypeService.selectChargeType(id));
        model.addAttribute("url", "modify");

        return "payment/charge-type-form";
    }

    /**
     * 해당 결제 타입 수정하는 메서드.
     *
     * @param id                    결제 타입 아이디
     * @param modifyTypeRequestDto  결제 타입 수정하는 데이터
     * @param bindingResult         수정에 대한 검증 결과
     * @param model                 HTML 로 보낼 데이터
     * @return                      해당 결제 타입에 대한 조회 화면 리턴
     */
    @PostMapping("/{id}/modify")
    public String postDoModifyChargeType(@PathVariable Long id,
                                         @ModelAttribute("type") @Valid ModifyTypeRequestDto modifyTypeRequestDto,
                                         BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("url", "modify");
            return "payment/charge-type-form";
        }

        chargeTypeService.modifyChargeType(id, modifyTypeRequestDto);

        return "redirect:/payments/charges/charge-type/" + id;
    }

    /**
     * 헤당 결제 타입을 삭제하는 메서드.
     *
     * @param id        결제 타입 아이디
     * @return          모든 결제 타입에 대한 목록 화면으로 반환
     */
    @GetMapping("/{id}/delete")
    public String postDeleteChargeType(@PathVariable Long id) {

        chargeTypeService.deleteChargeType(id);

        return "redirect:/payments/charges/charge-type";
    }
}
