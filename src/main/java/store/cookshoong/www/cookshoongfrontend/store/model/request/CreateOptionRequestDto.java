package store.cookshoong.www.cookshoongfrontend.store.model.request;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 사업자 : 옵션 등록 Dto.
 *
 * @author papel
 * @since 2023.07.11
 */
@Getter
@AllArgsConstructor
public class CreateOptionRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String price;
}